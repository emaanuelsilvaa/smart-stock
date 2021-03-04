package DATA;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;

public class MateriaPrimaRealDAO implements IMateriaPrimaRealDAO {
	
	protected ArrayList<MateriaPrimaReal> materiasPrimas;
	
	public MateriaPrimaRealDAO() {
		// TODO Auto-generated constructor stub
		this.materiasPrimas = new ArrayList<MateriaPrimaReal>();
	}
	public MateriaPrimaRealDAO(ArrayList<MateriaPrimaReal> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}
	@Override
	public int inserir(MateriaPrimaReal materiaPrima) {
		this.materiasPrimas.add(materiaPrima);
		return 0;
	}
	@Override
	public int remover(int id) {
		MateriaPrimaReal aux = new MateriaPrimaReal();
		for (MateriaPrimaReal m: this.materiasPrimas) {
			if(m.getId() == id) {
				aux = m;
				break;
			}
		}
		this.materiasPrimas.remove(aux);
		return 0;
	}
	@Override
	public int alterar() {
		//TODO things
		
		return 0;
	}
	@Override
	public int alterarQuantidade(int id, float quantidade) {
		float quantidade_atual;
		for(MateriaPrimaReal m : this.materiasPrimas) {
			if(m.getId() == id) {
				quantidade_atual = m.getQuantidade();
				m.setQuantidade(quantidade_atual+quantidade);
				return 0;
			}
		}
		return -1;
	}
	@Override
	public MateriaPrimaReal procuraPeloId(int id) {
		for (MateriaPrimaReal materiaPrima : this.materiasPrimas) {
			if (materiaPrima.getId() == id) {
				return materiaPrima;
			}
		}
		return null;
	}
	@Override
	public ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id){
		// Retorna todos os produtos de um mesmo tipo de ProdutoFinal
		ArrayList<MateriaPrimaReal> materiasAux = new ArrayList<MateriaPrimaReal>();
		for (MateriaPrimaReal m : this.materiasPrimas) {
			if (m.getIdExterno()==id) {
				materiasAux.add(m);
			}
		}
		return materiasAux;
	}
	@Override
	public ArrayList<MateriaPrimaReal> procuraTodos(){
		return this.materiasPrimas;
	}	
}
