package BUSINESS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import DATA.IVendaDAO;
import DATA.VendaDAO;
import ENTITY.ProdutoFinalReal;
import ENTITY.Venda;
import UTIL.BusinessRuleException;

public final class VendaService implements IVendaService {

	protected IVendaDAO vendaDAO;
	protected IEstoqueService estoqueService;
	protected IProdutoFinalService produtoFinalService;
	protected IProdutoFinalRealService produtoFinalRealService;
	protected IMateriaPrimaService materiaPrimaService;
	protected IMateriaPrimaRealService materiaPrimaRealService;
	private static IVendaService instance;
	
	private VendaService() {
		this.vendaDAO = new VendaDAO();
		this.estoqueService =  EstoqueService.getInstance();
		this.produtoFinalService = ProdutoFinalService.getInstance();
		this.produtoFinalRealService = ProdutoFinalRealService.getInstance();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
		this.materiaPrimaRealService = MateriaPrimaRealService.getInstance();
	};
	
	public static IVendaService getInstance() {
		if(instance == null) {
			instance = new VendaService();
		}
		return instance;
	}

	@Override
	public int remover (int id) throws BusinessRuleException {
		if(this.vendaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir um venda inexistente");
		}
		return this.vendaDAO.remover(id);
	}
	
	@Override
	public ArrayList<Venda> procuraTodos(){
		return vendaDAO.procuraTodos();
	}
	
	@Override
	public Venda procuraPeloId(int id) {
		return this.vendaDAO.procuraPeloId(id);
	}
	
	@Override
	public boolean realizarVenda(HashMap<Integer, Integer> listaProdutos, int idCliente) throws BusinessRuleException {
		Date data = new Date();
		boolean temMateriaPrimaSuficiente = true;
		HashMap <Integer, Integer> listaDeProdutosReais = new HashMap<Integer, Integer>();
		float valorDaVenda = 0;
		for (int idProduto : listaProdutos.keySet()) {
			int quantidade = listaProdutos.get(idProduto);
			if(estoqueService.verificaDisponibilidadeProduto(idProduto, quantidade) < 0) {
				temMateriaPrimaSuficiente = false;
				return false;
			}
			valorDaVenda += produtoFinalService.procuraPeloId(idProduto).getPreco() * quantidade;
		}
		if (temMateriaPrimaSuficiente) {
			// Fazer essa parte para forçar o estoque a pegar o mais antigo
			for (int idProduto : listaProdutos.keySet()){
				int quantidade = listaProdutos.get(idProduto);
				ArrayList<ProdutoFinalReal> produtosPossiveis = produtoFinalRealService.procuraPeloIdExterno(idProduto);
				Collections.sort(produtosPossiveis);
				for(ProdutoFinalReal p : produtosPossiveis) {
					// Tirando do estoque os produtos de validade menor
					int quantidadeMax = p.getQuantidade();
					if (quantidadeMax >= quantidade) {
						estoqueService.baixaProdutoFinal(p.getId(), quantidade);
						listaDeProdutosReais.put(p.getId(), quantidade);
						break;
					} else {
						estoqueService.baixaProdutoFinal(p.getId(), quantidadeMax);
						listaDeProdutosReais.put(p.getId(), quantidadeMax);
						quantidade -= quantidadeMax;
					}
				}
			}
			// Gerando um ID automaticamente para a nova venda
			Venda vendaASerInserida = new Venda(valorDaVenda, idCliente, listaProdutos, data);
			vendaASerInserida.setListaProdutosReais(listaDeProdutosReais);
			
			validarCadastro(vendaASerInserida);
			this.vendaDAO.inserir(vendaASerInserida);
			return true;
		}

		else {
			return false;
		}

	}
	
	@Override
	public boolean cancelarVenda(int id) throws BusinessRuleException {
		Venda vendaASerCancelada = this.vendaDAO.procuraPeloId(id);
		ProdutoFinalReal produtoFinalRealASerReposto = new ProdutoFinalReal();
		
		if(vendaASerCancelada == null) {
			return false;
		}
		
		this.remover(id);
		HashMap<Integer, Integer> listaDeProdutosFinaisReais = vendaASerCancelada.getListaProdutosReais();
		
		for(Integer key : listaDeProdutosFinaisReais.keySet()) {
			
			this.estoqueService.reporProdutoFinal(key, listaDeProdutosFinaisReais.get(key));
			
		}
		return true;
	}
	
	@Override
	public void validarCadastro(Venda venda) throws BusinessRuleException{
		ArrayList<String> erros = new ArrayList<String>();
		if(venda == null) {
			erros.add("Tentou inserir um Venda nula");
		}
		if(venda.getValor() <= 0) {
			erros.add("Tentou inserir um valor de venda nulo ou negativo");
		}
		if(venda.getListaProdutos().isEmpty()) {
			erros.add("Tentou vender uma lista de produtos vazia");
		} else {
			for(int i : venda.getListaProdutos().keySet()) {
				if(this.materiaPrimaService.procuraPeloId(i) == null) {
					erros.add("MatériaPrima de ID " + i + "não cadastrada");
				}
			}
		}
		if(venda.getListaProdutosReais().isEmpty()) {
			erros.add("Tentou vender uma lista de produtos reais vazia");
		} else {
			for(int i : venda.getListaProdutosReais().keySet()) {
				if(this.materiaPrimaRealService.procuraPeloId(i) == null) {
					erros.add("MatériaPrimaReal de ID " + i + "não cadastrada");
				}
			}
		}
		if (erros.size()>0) {
			throw new BusinessRuleException(erros);
		}
	}

}
