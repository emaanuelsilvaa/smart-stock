package DATA;

import java.util.ArrayList;

import BUSINESS.ProdutoFinal;

public class ProdutoFinalDAO {
	ArrayList<ProdutoFinal> produtos;

	public ProdutoFinalDAO() {
		// TODO Auto-generated constructor stub
		this.produtos = new ArrayList<ProdutoFinal>();
	}

	public int inserir(ProdutoFinal produto) {
		this.produtos.add(produto);
		return 0;
	}

	public int remover(ProdutoFinal produto) {
		this.produtos.remove(produto);
		return 0;
	}

	public int alterar() {
		//TODO things
		return 0;
	}

	public ProdutoFinal procuraPeloId(int id) {
		for (ProdutoFinal produto : this.produtos) {
			if (produto.getId() == id) {
				return produto;
			}
		}
		return null;
	}
	public ArrayList<ProdutoFinal> procuraTodos(){
		return this.produtos;
	}
}
