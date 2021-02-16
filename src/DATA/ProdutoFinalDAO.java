package DATA;

import java.util.ArrayList;

import BUSINESS.ProdutoFinal;

public class ProdutoFinalDAO implements IProdutoFinalDAO {
	ArrayList<ProdutoFinal> produtos;

	public ProdutoFinalDAO() {
		// TODO Auto-generated constructor stub
		this.produtos = new ArrayList<ProdutoFinal>();
	}

	@Override
	public int inserir(ProdutoFinal produto) {
		this.produtos.add(produto);
		return 0;
	}

	@Override
	public int remover(int id) {
		for (ProdutoFinal p: this.produtos) {
			if(p.getId() == id) {
				produtos.remove(p);
			}
		}
		return 0;
	}

	@Override
	public int alterar() {
		//TODO things
		
		return 0;
	}
	@Override
	public int alterarQuantidade(int id, int unidades) {
		int unidade_atual;
		for(ProdutoFinal p : this.produtos) {
			if(p.getId() == id) {
				unidade_atual = p.getUnidades();
				p.setUnidades(unidade_atual+unidades);
				return 0;
			}
		}
		return -1;
	}

	@Override
	public ProdutoFinal procuraPeloId(int id) {
		for (ProdutoFinal produto : this.produtos) {
			if (produto.getId() == id) {
				return produto;
			}
		}
		return null;
	}
	@Override
	public ArrayList<ProdutoFinal> procuraTodos(){
		return this.produtos;
	}
}
