package DATA;

import java.util.ArrayList;

import BUSINESS.ProdutoFinalReal;

public class ProdutoFinalRealDAO {
	protected ArrayList<ProdutoFinalReal> produtos;
	
	public ProdutoFinalRealDAO() {
		// TODO Auto-generated constructor stub
		this.produtos = new ArrayList<ProdutoFinalReal>();
	}
	public ProdutoFinalRealDAO(ArrayList<ProdutoFinalReal> produtos){
		this.produtos = produtos;
	}
	
	public int inserir(ProdutoFinalReal produto) {
		produtos.add(produto);
		return 0;
	}

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
	
	public int alterar() {
		//TODO things
		
		return 0;
	}

	public ProdutoFinalReal procuraPeloId(int id) {
		for (ProdutoFinalReal produto : this.produtos) {
			if (produto.getId() == id) {
				return produto;
			}
		}
		return null;
	}
	
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
	public ArrayList<ProdutoFinalReal> procuraTodos(){
		return this.produtos;
	}
}
