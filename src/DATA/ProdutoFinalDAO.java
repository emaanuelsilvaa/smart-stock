package DATA;

import java.util.ArrayList;

import ENTITY.Cliente;
import ENTITY.ProdutoFinal;

public class ProdutoFinalDAO implements IProdutoFinalDAO {
	protected ArrayList<ProdutoFinal> produtos;

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
		ProdutoFinal aux = new ProdutoFinal();
		for (ProdutoFinal p: this.produtos) {
			if(p.getId() == id) {
				aux = p;
				break;
			}
		}
		this.produtos.remove(aux);
		return 0;
	}

	@Override
	public int alterar(int id, ProdutoFinal produto) {
		for (ProdutoFinal p : this.produtos) {
			if (p.getId() == id) {
				p.setId(produto.getId());
				p.setNome(produto.getNome());
				p.setPreco(produto.getPreco());
				p.setQntMinima(produto.getQntMinima());
				p.setReceita(produto.getReceita());
				break;
			}
		}
		return 0;
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
