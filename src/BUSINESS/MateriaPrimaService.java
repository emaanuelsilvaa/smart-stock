package BUSINESS;

import java.util.ArrayList;

import DATA.IMateriaPrimaDAO;
import DATA.MateriaPrimaDAO;

public class MateriaPrimaService implements IMateriaPrimaService {
	protected IMateriaPrimaDAO materiaPrimaDAO;
	
	// Construtores
	public MateriaPrimaService() {
		this.materiaPrimaDAO = new MateriaPrimaDAO();
	}

	public MateriaPrimaService(IMateriaPrimaDAO materiasPrimas) {
		super();
		this.materiaPrimaDAO = materiasPrimas;
	}
	
	@Override
	public int inserir(MateriaPrima materiaPrima) {
		if (this.materiaPrimaDAO.procuraPeloId(materiaPrima.getId()) != null) {
			return -1;
		}
		this.materiaPrimaDAO.inserir(materiaPrima);
		return -1;
	}

	@Override
	public int remover(int id) {
		if (this.materiaPrimaDAO.procuraPeloId(id) != null) {
			this.materiaPrimaDAO.remover(id);
			return 0;
		}
		return -1;
	}
	
	@Override
	public int alterar() {
		// TODO things
		return 0;
	}

	@Override
	public MateriaPrima procuraPeloId(int id) {
		return this.materiaPrimaDAO.procuraPeloId(id);
	}

	@Override
	public ArrayList<MateriaPrima> procuraTodos() {
		return this.materiaPrimaDAO.procuraTodos();
	}

}
