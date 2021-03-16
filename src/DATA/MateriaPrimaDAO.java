package DATA;

import java.util.ArrayList;

import ENTITY.Cliente;
import ENTITY.MateriaPrima;
import ENTITY.ProdutoFinal;

public class MateriaPrimaDAO implements IMateriaPrimaDAO {
	protected ArrayList<MateriaPrima> materiasPrimas;
	protected int idSerial;
	
	// Construtores
	public MateriaPrimaDAO() {
		this.materiasPrimas = new ArrayList<MateriaPrima>();
		this.idSerial = 1;
	}

	public MateriaPrimaDAO(ArrayList<MateriaPrima> materiasPrimas) {
		super();
		this.materiasPrimas = materiasPrimas;
	}

	@Override
	public int inserir(MateriaPrima materiaPrima) {
		this.materiasPrimas.add(materiaPrima);
		return materiaPrima.getId();
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
		return aux.getId();
	}

	@Override
	public int alterar(int id, MateriaPrima materiaPrima) {
		for (MateriaPrima p : this.materiasPrimas) {
			if (p.getId() == id) {
				p.setId(materiaPrima.getId());
				p.setNome(materiaPrima.getNome());
				p.setTipo(materiaPrima.getTipo());
				p.setPerecivel(materiaPrima.getPerecivel());
				p.setUnMedida(materiaPrima.getUnMedida());
				p.setQntMinima(materiaPrima.getQntMinima());
				break;
			}
		}
		return id;
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
	
	@Override
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
	
}
