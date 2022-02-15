package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;
import ENTITY.MateriaPrima;

public final class RelatorioService implements IRelatorioService {
	protected /*@ spec_public non_null @*/ IEncomendaService encomendaService;
	protected /*@ spec_public non_null @*/ IEstoqueService estoqueService;
	protected /*@ spec_public non_null @*/ IProdutoFinalService produtoFinalService;
	protected /*@ spec_public non_null @*/ IMateriaPrimaService materiaPrimaService;
	private static /*@ spec_public  @*/ IRelatorioService instance;
	
	/*@ 
	  @assignable encomendaService,estoqueService,produtoFinalService,materiaPrimaService; 
	  @ ensures encomendaService != null; 
	  @ ensures estoqueService != null; 
	  @ ensures produtoFinalService != null; 
	  @ ensures materiaPrimaService != null; 
	  @*/
	private RelatorioService() {
		this.encomendaService = EncomendaService.getInstance();
		this.estoqueService = EstoqueService.getInstance();
		this.produtoFinalService = ProdutoFinalService.getInstance();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
	}
	/*@ 
	  @assignable instance; 
	  @ ensures instance != null; 
	  @*/
	public static IRelatorioService getInstance() {
		if (instance == null) {
			instance = new RelatorioService();
		}
		return instance;
	}
	/*@ 
	  @ assignable qntProduto, qntProdutoFaltante, aux,qtdMinima,disponibilidade;
	  @ ensures qntProduto !=null;
	  @ ensures qntProdutoFaltante !=null;
	  @ ensures qntProduto !=null;
	  @ ensures  ( \forall int i; i < this.encomendaService.procuraTodos().size() ;	
	  @				this.encomendaService.procuraTodos().get(i).getListaProdutos().keySet() != null );			
	  @*/
	@Override
	public HashMap<Integer, Integer> listarReposicaoProduto(Date dataInicio, Date dataFim) throws BusinessRuleException {
		// Função que retorna a lista de produtos necessário para atender as encomendas
		// a partir de um intervalo de tempo
		if (dataInicio.after(dataFim)){
			throw new BusinessRuleException("Data final é menor que a data inicial");
		}
		HashMap<Integer, Integer> qntProduto = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> qntProdutoFaltante = new HashMap<Integer, Integer>();
		for (Encomenda e : encomendaService.procuraTodos()) {
			if ((e.getDataEntrega().after(dataInicio) && e.getDataEntrega().before(dataFim)) || e.getDataEntrega().equals(dataInicio) || e.getDataEntrega().equals(dataFim)) {
				for (int id : e.getListaProdutos().keySet()) {
					if (!qntProduto.containsKey(id)) {
						qntProduto.put(id, e.getListaProdutos().get(id));
					} else {
						int aux = qntProduto.get(id) + e.getListaProdutos().get(id);
						qntProduto.replace(id, aux); // incrementando a lista de produtos necessários
					}
				}
			}
		}
		for (int id : qntProduto.keySet()) {
			int qntMinima = this.produtoFinalService.procuraPeloId(id).getQntMinima();
			int disponibilidade = estoqueService.verificaDisponibilidadeProduto(id, qntProduto.get(id) + qntMinima);
			if (disponibilidade < 0) {
				qntProdutoFaltante.put(id, -1 * disponibilidade);
			}
		}
		return qntProdutoFaltante;
	}
	
	@Override
	public HashMap <Integer, Float> listarReposicaoMateriaPrima(Date dataInicio, Date dataFim) throws BusinessRuleException{
		HashMap <Integer, Integer> listaDeProdutosFaltantes = this.listarReposicaoProduto(dataInicio, dataFim);
		HashMap <Integer, Float> listaDeMateriaPrimaFaltanteTotal = new HashMap <Integer, Float> ();
		HashMap <Integer, Float> listaDeMateriaPrimaFaltante = new HashMap <Integer, Float> ();
		
		//MateriaPrima materiaPrimaASerReposta = new MateriaPrima ();
		ProdutoFinal produtoFinalASerReposto = new ProdutoFinal ();
		HashMap<Integer, Float> receita = new HashMap <Integer, Float> (); 
		float valor_residual, valor_de_reposicao, valor_final, diferenca_de_estoque = 0;
		int quantidade_de_produtos = 0;
		
		if(!listaDeProdutosFaltantes.isEmpty()) {
			for(int ide_prod : listaDeProdutosFaltantes.keySet()) {
				
				produtoFinalASerReposto = produtoFinalService.procuraPeloId(ide_prod);
				receita = produtoFinalASerReposto.getReceita();
				quantidade_de_produtos= listaDeProdutosFaltantes.get(ide_prod);
				
				for(int ide_receita : receita.keySet()) {		
					
					if(listaDeMateriaPrimaFaltanteTotal.containsKey(ide_receita)) {
						
						valor_residual = listaDeMateriaPrimaFaltanteTotal.get(ide_receita);
						valor_de_reposicao = quantidade_de_produtos * receita.get(ide_receita);
						valor_final = valor_residual + valor_de_reposicao;
						listaDeMateriaPrimaFaltanteTotal.put(ide_receita, valor_final);
					}
					
					else {					
						valor_final = quantidade_de_produtos * receita.get(ide_receita);
						listaDeMateriaPrimaFaltanteTotal.put(ide_receita, valor_final + materiaPrimaService.procuraPeloId(ide_receita).getQntMinima());
					}
					
				}
			}
			
			for (int id_materia : listaDeMateriaPrimaFaltanteTotal.keySet()) {
				diferenca_de_estoque = estoqueService.verificaDisponibilidadeMateriaPrima(id_materia, listaDeMateriaPrimaFaltanteTotal.get(id_materia));
				if(diferenca_de_estoque < 0) {
					listaDeMateriaPrimaFaltante.put(id_materia, -1 * diferenca_de_estoque);
				}
			}
		}
		
		
		return listaDeMateriaPrimaFaltante;
	}

}
