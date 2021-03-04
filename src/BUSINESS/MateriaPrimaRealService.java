package BUSINESS;

import java.util.ArrayList;

import DATA.IMateriaPrimaRealDAO;
import DATA.MateriaPrimaRealDAO;
import ENTITY.MateriaPrimaReal;

public final class MateriaPrimaRealService implements IMateriaPrimaRealService {

	protected IMateriaPrimaRealDAO materiaPrimaRealDAO;
	protected IMateriaPrimaService materiaPrimaService;
	private static IMateriaPrimaRealService instance;

	private MateriaPrimaRealService() {
		// TODO Auto-generated constructor stub
		this.materiaPrimaRealDAO = new MateriaPrimaRealDAO();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
	}
	public static IMateriaPrimaRealService getInstance() {
		if (instance == null) {
			instance = new MateriaPrimaRealService();
		}
		return instance;
	}

	public MateriaPrimaRealService(IMateriaPrimaRealDAO materiaPrimaRealDAO, IMateriaPrimaService materiaPrimaService) {
		super();
		this.materiaPrimaRealDAO = materiaPrimaRealDAO;
		this.materiaPrimaService = materiaPrimaService;
	}

	@Override
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

	@Override
	public int remover(int id) {
		if (this.materiaPrimaRealDAO.procuraPeloId(id) != null) {
			this.materiaPrimaRealDAO.remover(id);
			return 0;
		}
		return -1;
	}

	@Override
	public int alterar(int id, MateriaPrimaReal materiaPrimaReal) {
		if (this.materiaPrimaRealDAO.procuraPeloId(id) != null) {
			this.materiaPrimaRealDAO.alterar(id, materiaPrimaReal);
			return 0;
		}
		return -1;
	}
	@Override
	public int alterarQuantidade(int id, float quantidade) {
		return this.materiaPrimaRealDAO.alterarQuantidade(id, quantidade);
	}
	@Override
	public MateriaPrimaReal procuraPeloId(int id) {
		return this.materiaPrimaRealDAO.procuraPeloId(id);
	}
	@Override
	public ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id){
		return this.procuraPeloIdExterno(id);
	}

	@Override
	public ArrayList<MateriaPrimaReal> procuraTodos() {
		return this.materiaPrimaRealDAO.procuraTodos();
	}

	@Override
	public int validarCadastro(int id) {
		// Checagem se matéria prima existe
		if (this.materiaPrimaRealDAO.procuraPeloId(id) == null) {
			return -1;
		}
		return 0;
	}

}
