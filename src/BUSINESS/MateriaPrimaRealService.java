package BUSINESS;

import java.util.ArrayList;

import DATA.MateriaPrimaRealDAO;

public class MateriaPrimaRealService {

	protected MateriaPrimaRealDAO materiaPrimaRealDAO;
	protected MateriaPrimaService materiaPrimaService;

	public MateriaPrimaRealService() {
		// TODO Auto-generated constructor stub
	}

	public MateriaPrimaRealService(MateriaPrimaRealDAO materiaPrimaRealDAO, MateriaPrimaService materiaPrimaService) {
		super();
		this.materiaPrimaRealDAO = materiaPrimaRealDAO;
		this.materiaPrimaService = materiaPrimaService;
	}

	public int inserir(MateriaPrimaReal materiaPrimaReal) {
		if (this.materiaPrimaRealDAO.procuraPeloId(materiaPrimaReal.getId()) != null) {
			return -1;
		}
		if(this.materiaPrimaService.procuraPeloId(materiaPrimaReal.getIdExterno())== null) {
			// Não tem materia prima geral vinvulada
			return -1;
		}
		this.materiaPrimaRealDAO.inserir(materiaPrimaReal);
		return -1;
	}

	public int remover(int id) {
		if (this.materiaPrimaRealDAO.procuraPeloId(id) != null) {
			this.materiaPrimaRealDAO.remover(id);
			return 0;
		}
		return -1;
	}

	public int alterar() {
		// TODO things
		return 0;
	}
	public int alterarQuantidade(int id, float quantidade) {
		return this.materiaPrimaRealDAO.alterarQuantidade(id, quantidade);
	}
	public MateriaPrimaReal procuraPeloId(int id) {
		return this.materiaPrimaRealDAO.procuraPeloId(id);
	}
	public ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id){
		return this.procuraPeloIdExterno(id);
	}

	public ArrayList<MateriaPrimaReal> procuraTodos() {
		return this.materiaPrimaRealDAO.procuraTodos();
	}

	public int validarCadastro(int id) {
		// Checagem se matéria prima existe
		if (this.materiaPrimaRealDAO.procuraPeloId(id) == null) {
			return -1;
		}
		return 0;
	}

}
