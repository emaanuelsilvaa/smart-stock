package BUSINESS;

import java.util.ArrayList;

import DATA.IProdutoFinalRealDAO;
import DATA.ProdutoFinalRealDAO;
import ENTITY.ProdutoFinalReal;

public final class ProdutoFinalRealService implements IProdutoFinalRealService {
	
	protected IProdutoFinalRealDAO produtoFinalRealDAO;
	protected IProdutoFinalService produtoFinalService; 
	private static IProdutoFinalRealService instance;

	public ProdutoFinalRealService() {
		// TODO Auto-generated constructor stub
		this.produtoFinalService =  ProdutoFinalService.getInstance();
		this.produtoFinalRealDAO = new ProdutoFinalRealDAO();
	}

	public static IProdutoFinalRealService getInstance() {
		if(instance == null) {
			instance = new ProdutoFinalRealService();
		}
		return instance;
	}

	@Override
	public int inserir(ProdutoFinalReal produto) {
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

	@Override
	public int remover(int id) {
		if (produtoFinalRealDAO.procuraPeloId(id) != null) {
			produtoFinalRealDAO.remover(id);
			return 0;
		}
		return -1;
	}

	@Override
	public int alterar() {
		// TODO things
		return 0;
	}
	@Override
	public int alterarQuantidade(int id, int unidades) {
		return this.produtoFinalRealDAO.alterarQuantidade(id, unidades);
	}
	@Override
	public ProdutoFinalReal procuraPeloId(int id) {
		return produtoFinalRealDAO.procuraPeloId(id);
	}
	@Override
	public ArrayList<ProdutoFinalReal> procuraPeloIdExterno(int id){
		return produtoFinalRealDAO.procuraPeloIdExterno(id);
	}

	@Override
	public ArrayList<ProdutoFinalReal> procuraTodos() {
		return produtoFinalRealDAO.procuraTodos();
	}

}
