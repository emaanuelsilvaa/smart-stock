package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;
import ENTITY.MateriaPrima;

public final class RelatorioService {
	protected IEncomendaService encomendaService;
	protected IEstoqueService estoqueService;
	protected IProdutoFinalService produtoFinalService;
	protected IMateriaPrimaService materiaPrimaService;
	private static RelatorioService instance;

	private RelatorioService() {
		this.encomendaService = EncomendaService.getInstance();
		this.estoqueService = EstoqueService.getInstance();
		this.produtoFinalService = ProdutoFinalService.getInstance();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
	}

	public static RelatorioService getInstence() {
		if (instance == null) {
			instance = new RelatorioService();
		}
		return instance;
	}

	public HashMap<Integer, Integer> listarReposicaoProduto(Date dataInicio, Date dataFim) {
		// Função que retorna a lista de produtos necessário para atender as encomendas
		// a partir de um intervalo de tempo
		HashMap<Integer, Integer> qntProduto = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> qntProdutoFaltante = new HashMap<Integer, Integer>();
		for (Encomenda e : encomendaService.procuraTodos()) {
			if (e.getDataEntrega().after(dataInicio) && e.getDataEntrega().before(dataFim)) {
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
			int disponibilidade = estoqueService.verificaDisponibilidadeProduto(id, qntProduto.get(id));
			if (disponibilidade < 0) {
				qntProdutoFaltante.put(id, disponibilidade);
			}
		}
		return qntProdutoFaltante;
	}
	
	public HashMap <Integer, Float> litarReposicaoMateriaPrima(Date dataInicio, Date dataFim){
		HashMap <Integer, Integer> listaDeProdutosFaltantes = this.listarReposicaoProduto(dataInicio, dataFim);
		HashMap <Integer, Float> listaDeMateriaPrimaFaltante = new HashMap <Integer, Float> ();
		//MateriaPrima materiaPrimaASerReposta = new MateriaPrima ();
		ProdutoFinal produtoFinalASerReposto = new ProdutoFinal ();
		HashMap<Integer, Float> receita = new HashMap <Integer, Float> (); 
		float valor_residual, valor_de_reposição, valor_final = 0;
		int quantidade_de_produtos = 0;
		
		if(!listaDeProdutosFaltantes.isEmpty()) {
			for(int ide_prod : listaDeProdutosFaltantes.keySet()) {
				
				produtoFinalASerReposto = produtoFinalService.procuraPeloId(ide_prod);
				receita = produtoFinalASerReposto.getReceita();
				quantidade_de_produtos= listaDeProdutosFaltantes.get(ide_prod);
				
				for(int ide_receita : receita.keySet()) {
					valor_residual = listaDeMateriaPrimaFaltante.get(ide_receita);
					valor_de_reposição = quantidade_de_produtos * receita.get(ide_receita);
					valor_final = valor_residual + valor_de_reposição;
							
					listaDeMateriaPrimaFaltante.put(ide_receita, valor_final);
					
				}
			}
		}
		
		
		return listaDeMateriaPrimaFaltante;
	}

}
