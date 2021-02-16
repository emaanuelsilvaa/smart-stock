package DATA;

import java.util.ArrayList;

import BUSINESS.MateriaPrima;

public class MateriaPrimaDAO implements IMateriaPrimaDAO {
	protected ArrayList<MateriaPrima> materiasPrimas;
	
	// Construtores
	public MateriaPrimaDAO() {
		this.materiasPrimas = new ArrayList<MateriaPrima>();
	}

	public MateriaPrimaDAO(ArrayList<MateriaPrima> materiasPrimas) {
		super();
		this.materiasPrimas = materiasPrimas;
	}

	@Override
	public int inserir(MateriaPrima materiaPrima) {
		this.materiasPrimas.add(materiaPrima);
		return 0;
	}

	@Override
	public int remover(int id) {
		MateriaPrima aux = new MateriaPrima();
		for (MateriaPrima m: this.materiasPrimas) {
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
	public MateriaPrima procuraPeloId(int id) {
		for (MateriaPrima m : this.materiasPrimas) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	@Override
	public ArrayList<MateriaPrima> procuraTodos(){
		return this.materiasPrimas;
	}
	
}
