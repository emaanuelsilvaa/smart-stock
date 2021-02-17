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
	
	@Override
	public int validarCadastro(int id) {
		// Checagem se matéria prima existe		
		if(this.materiaPrimaDAO.procuraPeloId(id) == null) {
			return -1;
		}
		else {	
			MateriaPrima materiaPrima = this.materiaPrimaDAO.procuraPeloId(id);
			// Validação de Tipo (Padrão: Alimento ou Embalagem.)
			if(materiaPrima.getTipo().toLowerCase() != "alimento" &&
			   materiaPrima.getTipo().toLowerCase() != "embalagem") {
				return -1;
			}
		}
		
		return 0;
	}

}
