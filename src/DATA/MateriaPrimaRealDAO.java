package DATA;

import java.util.ArrayList;

import ENTITY.Cliente;
import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;

public class MateriaPrimaRealDAO implements IMateriaPrimaRealDAO {
	
	protected ArrayList<MateriaPrimaReal> materiasPrimas;
	protected int idSerial;
	
	public MateriaPrimaRealDAO() {
		// TODO Auto-generated constructor stub
		this.materiasPrimas = new ArrayList<MateriaPrimaReal>();
		this.idSerial = 1;
	}
	public MateriaPrimaRealDAO(ArrayList<MateriaPrimaReal> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}
	@Override
	public int inserir(MateriaPrimaReal materiaPrima) {
		materiaPrima.setId(this.pegaEIncremanetaId());
		this.materiasPrimas.add(materiaPrima);
		return materiaPrima.getId();
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
		return aux.getId();
	}
	@Override
	public int alterar(int id, MateriaPrimaReal materiaPrima) {
		for (MateriaPrimaReal m : this.materiasPrimas) {
			if (m.getId() == id) {
				m.setId(id);
				m.setIdExterno(materiaPrima.getIdExterno());
				m.setPreco(materiaPrima.getPreco());
				m.setValidade(materiaPrima.getValidade());
				m.setQuantidade(materiaPrima.getQuantidade());
				m.setFornecedor(materiaPrima.getFornecedor());
				break;
			}
		}
		return id;
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
	public /*@ pure @*/ MateriaPrimaReal procuraPeloId(int id) {
		for (MateriaPrimaReal materiaPrima : this.materiasPrimas) {
			if (materiaPrima.getId() == id) {
				return materiaPrima;
			}
		}
		return null;
	}
	@Override
	public /*@ pure @*/ ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id){
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
	public /*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodos(){
		return this.materiasPrimas;
	}	
	
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
}
