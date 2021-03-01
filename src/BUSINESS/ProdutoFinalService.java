package BUSINESS;

import java.util.ArrayList;

import DATA.ProdutoFinalDAO;

public class ProdutoFinalService implements IProdutoFinalService {
	protected ProdutoFinalDAO produtoFinalDAO;

	public ProdutoFinalService() {
		// TODO Auto-generated constructor stub
		this.produtoFinalDAO = new ProdutoFinalDAO();
	}
	public ProdutoFinalService(ProdutoFinalDAO produtoFinalDAO) {
		this.produtoFinalDAO = produtoFinalDAO;
	}
	@Override
	public int inserir(ProdutoFinal produto) {
		// Aqui temos uma decisão de projeto. Como será a inserção de um produto?
		// Desta maneira o proprio usuário qm seta o ID, mas vc pode fzr uma função p
		// pegar
		// o próximo ID disponível e salvar na memória.
		if (produtoFinalDAO.procuraPeloId(produto.getId()) != null) {
			// ID está cadastrado
			return -1;
		}
		produtoFinalDAO.inserir(produto);
		return 0;
	}

	@Override
	public int remover(int id) {
		if (produtoFinalDAO.procuraPeloId(id) != null) {
			produtoFinalDAO.remover(id);
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
	public ProdutoFinal procuraPeloId(int id) {
		return produtoFinalDAO.procuraPeloId(id);
	}

	@Override
	public ArrayList<ProdutoFinal> procuraTodos() {
		return produtoFinalDAO.procuraTodos();
	}

}
