package BUSINESS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import DATA.IVendaDAO;
import DATA.VendaDAO;
import ENTITY.Cliente;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;
import ENTITY.Venda;

public final class VendaService implements IVendaService {

	protected IVendaDAO vendaDAO;
	protected IEstoqueService estoqueService;
	protected IProdutoFinalService produtoFinalService;
	protected IProdutoFinalRealService produtoFinalRealService;
	private static IVendaService instance;
	
	private VendaService() {
		this.vendaDAO = new VendaDAO();
		this.estoqueService =  EstoqueService.getInstance();
		this.produtoFinalService = ProdutoFinalService.getInstance();
		this.produtoFinalRealService = ProdutoFinalRealService.getInstance();
	};
	
	public static IVendaService getInstance() {
		if(instance == null) {
			instance = new VendaService();
		}
		return instance;
	}

	@Override
	public ArrayList<Venda> procuraTodos(){
		return vendaDAO.procuraTodos();
	}
	
	@Override
	public Venda procuraPeloId(int id) {
		return this.vendaDAO.procuraPeloID(id);
	}
	
	@Override
	public boolean realizarVenda(HashMap<Integer, Integer> listaProdutos, int idCliente) {
		Date data = new Date();
		boolean temMateriaPrimaSuficiente = true;
		HashMap <Integer, Integer> listaDeProdutosReais = listaProdutos;
		listaDeProdutosReais.clear();
		float valorDaVenda = 0;
		for (int idProduto : listaProdutos.keySet()) {
			int quantidade = listaProdutos.get(idProduto);
			if(!estoqueService.verificaDisponibilidadeProduto(idProduto, quantidade)) {
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
			int newID = vendaDAO.getNextID();
			Venda vendaASerInserida = new Venda(newID, valorDaVenda, idCliente, listaProdutos, data);
			vendaASerInserida.setListaProdutosReais(listaDeProdutosReais);
			
			this.vendaDAO.inserir(vendaASerInserida);
			return true;
		}

		else {
			return false;
		}

	}
	
	public boolean cancelarVenda(int id) {
		Venda vendaASerCancelada = this.vendaDAO.procuraPeloID(id);
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
	
	public int remover (int id) {
		if(this.vendaDAO.procuraPeloID(id) != null) {
			this.vendaDAO.remover(id);
			return 0;
		}
		
		return -1;
	}

}
