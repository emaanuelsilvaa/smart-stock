package BUSINESS;

import java.util.ArrayList;

import DATA.ProdutoFinalDAO;

public class ProdutoFinalService {
	ProdutoFinalDAO produtoFinalDAO;

	public ProdutoFinalService() {
		// TODO Auto-generated constructor stub
		this.produtoFinalDAO = new ProdutoFinalDAO();
	}

	public int inserir(ProdutoFinal produto) {
		// Aqui temos uma decisão de projeto. Como será a inserção de um produto?
		// Desta maneira o proprio usuário qm seta o ID, mas vc pode fzr uma função p
		// pegar
		// o próximo ID disponível e salvar na memória.
		if (produtoFinalDAO.procuraPeloId(produto.getId()) != null) {
			// ID está cadastraso
			return -1;
		}
		produtoFinalDAO.inserir(produto);
		return -1;
	}

	public int remover(ProdutoFinal produto) {
		if (produtoFinalDAO.procuraPeloId(produto.getId()) != null) {
			produtoFinalDAO.remover(produto);
			return 0;
		}
		return -1;
	}

	public ProdutoFinal procuraUm(ProdutoFinal produto) {
		return produtoFinalDAO.procuraPeloId(produto.getId());
	}

	public ArrayList<ProdutoFinal> procuraTodos() {
		return produtoFinalDAO.procuraTodos();
	}

	public int alterar() {
		// TODO things
		return 0;
	}
}
