package BUSINESS;

import java.util.ArrayList;

import DATA.ProdutoFinalRealDAO;

public class ProdutoFinalRealService {
	
	protected ProdutoFinalRealDAO produtoFinalRealDAO;
	protected ProdutoFinalService produtoFinalService; // usado para as verificações genericas

	public ProdutoFinalRealService() {
		// TODO Auto-generated constructor stub
		this.produtoFinalService = new ProdutoFinalService();
		this.produtoFinalRealDAO = new ProdutoFinalRealDAO();
	}

	public ProdutoFinalRealService(ProdutoFinalRealDAO produtoFinalRealDAO, ProdutoFinalService produtoFinalService) {
		super();
		this.produtoFinalRealDAO = produtoFinalRealDAO;
		this.produtoFinalService = produtoFinalService;
	}

	public int inserir(ProdutoFinalReal produto) {
		System.out.println(produtoFinalRealDAO.procuraPeloId(1));
		if (produtoFinalRealDAO.procuraPeloId(produto.getId()) != null) {
			// ID ja está cadastrado
			return -1;
		}
		if (produtoFinalService.procuraPeloId(produto.getIdExterno())==null) {
			// N tem um ProdutoFinal correspondente
			return -1;
		}
		produtoFinalRealDAO.inserir(produto);
		return 0;
	}

	public int remover(int id) {
		if (produtoFinalRealDAO.procuraPeloId(id) != null) {
			produtoFinalRealDAO.remover(id);
			return 0;
		}
		return -1;
	}

	public int alterar() {
		// TODO things
		return 0;
	}
	public int alterarQuantidade(int id, int unidades) {
		return this.produtoFinalRealDAO.alterarQuantidade(id, unidades);
	}
	public ProdutoFinalReal procuraPeloId(int id) {
		return produtoFinalRealDAO.procuraPeloId(id);
	}
	public ArrayList<ProdutoFinalReal> procuraPeloIdExterno(int id){
		return produtoFinalRealDAO.procuraPeloIdExterno(id);
	}

	public ArrayList<ProdutoFinalReal> procuraTodos() {
		return produtoFinalRealDAO.procuraTodos();
	}

}
