package DATA;

import java.util.ArrayList;

import ENTITY.Cliente;
import ENTITY.ProdutoFinalReal;

public class ProdutoFinalRealDAO implements IProdutoFinalRealDAO {
	protected ArrayList<ProdutoFinalReal> produtos;
	
	public ProdutoFinalRealDAO() {
		// TODO Auto-generated constructor stub
		this.produtos = new ArrayList<ProdutoFinalReal>();
	}
	public ProdutoFinalRealDAO(ArrayList<ProdutoFinalReal> produtos){
		this.produtos = produtos;
	}
	
	@Override
	public int inserir(ProdutoFinalReal produto) {
		produtos.add(produto);
		return 0;
	}

	@Override
	public int remover(int id) {
		ProdutoFinalReal aux = new ProdutoFinalReal();
		for (ProdutoFinalReal p: this.produtos) {
			if(p.getId() == id) {
				aux = p;
				break;
			}
		}
		this.produtos.remove(aux);
		return 0;
	}
	
	@Override
	public int alterar(int id, ProdutoFinalReal produto) {
		for (ProdutoFinalReal pr : this.produtos) {
			if (pr.getId() == id) {
				pr.setId(produto.getId());
				pr.setIdExterno(produto.getIdExterno());
				pr.setValidade(produto.getValidade());
				pr.setQuantidade(produto.getQuantidade());
				break;
			}
		}
		return 0;
	}

	@Override
	public ProdutoFinalReal procuraPeloId(int id) {
		for (ProdutoFinalReal produto : this.produtos) {
			if (produto.getId() == id) {
				return produto;
			}
		}
		return null;
	}
	
	@Override
	public int alterarQuantidade(int id, int unidades) {
		int unidade_atual;
		for(ProdutoFinalReal p : this.produtos) {
			if(p.getId() == id) {
				unidade_atual = p.getQuantidade();
				p.setQuantidade(unidade_atual+unidades);
				return 0;
			}
		}
		return -1;
	}
	@Override
	public ArrayList<ProdutoFinalReal> procuraPeloIdExterno(int id){
		// Retorna todos os produtos de um mesmo tipo de ProdutoFinal
		ArrayList<ProdutoFinalReal> produtosAux = new ArrayList<ProdutoFinalReal>();
		for (ProdutoFinalReal p : this.produtos) {
			if (p.getIdExterno()==id) {
				produtosAux.add(p);
			}
		}
		return produtosAux;
	}
	@Override
	public ArrayList<ProdutoFinalReal> procuraTodos(){
		return this.produtos;
	}
}
