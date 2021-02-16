package DATA;

import java.util.ArrayList;

import BUSINESS.ProdutoFinal;

public class ProdutoFinalDAO {
	ArrayList<ProdutoFinal> produtos;

	public ProdutoFinalDAO() {
		// TODO Auto-generated constructor stub
		this.produtos = new ArrayList<ProdutoFinal>();
	}

	public void inserir(ProdutoFinal produto) {
		this.produtos.add(produto);
	}

	public void remover(ProdutoFinal produto) {
		this.produtos.remove(produto);
	}

	public void alterar() {
		//TODO things
	}

	public ProdutoFinal procuraPeloId(int id) {
		for (ProdutoFinal produto : this.produtos) {
			if (produto.getIdProdutoFinal() == id) {
				return produto;
			}
		}
		return null;
	}
	public ArrayList<ProdutoFinal> procuraTodos(){
		return this.produtos;
	}
}
