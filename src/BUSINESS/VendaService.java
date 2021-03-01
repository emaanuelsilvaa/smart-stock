package BUSINESS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import DATA.VendaDAO;

public class VendaService implements IVendaService {

	protected VendaDAO vendaDAO;
	protected EstoqueService estoqueService;
	protected ProdutoFinalService produtoFinalService;
	protected ProdutoFinalRealService produtoFinalRealService;
	
	public VendaService() {
		this.vendaDAO = new VendaDAO();
		this.estoqueService = new EstoqueService();
	};

	
	public VendaService(VendaDAO vendaDAO, EstoqueService estoqueService, ProdutoFinalService produtoFinalService,
			ProdutoFinalRealService produtoFinalRealService) {
		super();
		this.vendaDAO = vendaDAO;
		this.estoqueService = estoqueService;
		this.produtoFinalService = produtoFinalService;
		this.produtoFinalRealService = produtoFinalRealService;
	}


	public ArrayList<Venda> procuraTodos(){
		return vendaDAO.procuraTodos();
	}

	public boolean realizarVenda(HashMap<Integer, Integer> listaProdutos, int idCliente) {
		Date data = new Date();
		boolean temMateriaPrimaSuficiente = true;
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
						break;
					} else {
						estoqueService.baixaProdutoFinal(p.getId(), quantidadeMax);
						quantidade -= quantidadeMax;
					}
				}
			}
			// Gerando um ID automaticamente para a nova venda
			int newID = vendaDAO.getNextID();
			this.vendaDAO.inserir(new Venda(newID, valorDaVenda, idCliente, listaProdutos, data));
			return true;
		}

		else {
			return false;
		}

	}

	@Override
	public boolean realizarVenda(ArrayList<ProdutoFinal> listaDeProdutos, Cliente comprador) {
		// TODO Auto-generated method stub
		return false;
	};

}
