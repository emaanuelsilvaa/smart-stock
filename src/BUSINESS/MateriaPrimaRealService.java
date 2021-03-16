package BUSINESS;

import java.util.ArrayList;

import DATA.IMateriaPrimaRealDAO;
import DATA.MateriaPrimaRealDAO;
import ENTITY.MateriaPrimaReal;
import UTIL.BusinessRuleException;

public final class MateriaPrimaRealService implements IMateriaPrimaRealService {

	protected IMateriaPrimaRealDAO materiaPrimaRealDAO;
	protected IMateriaPrimaService materiaPrimaService;
	protected IFornecedorService fornecedorService;
	private static IMateriaPrimaRealService instance;

	private MateriaPrimaRealService() {
		// TODO Auto-generated constructor stub
		this.materiaPrimaRealDAO = new MateriaPrimaRealDAO();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
		this.fornecedorService = FornecedorService.getInstance();
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
	public int inserir(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException {
		this.validarCadastro(materiaPrimaReal);
		this.materiaPrimaRealDAO.inserir(materiaPrimaReal);
		return -1;
	}

	@Override
	public int remover(int id) throws BusinessRuleException{
		if (this.materiaPrimaRealDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir uma matéria-prima real inexistente");
		}
		return 	this.materiaPrimaRealDAO.remover(id);
	}

	@Override
	public int alterar(int id, MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException {
		this.validarCadastro(materiaPrimaReal);
		if (this.materiaPrimaRealDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		return this.materiaPrimaRealDAO.alterar(id, materiaPrimaReal);
	}
	@Override
	public int alterarQuantidade(int id, float quantidade) throws BusinessRuleException {
		if (quantidade < 0){
			throw new BusinessRuleException("Quantidade negativa");
		}
		return this.materiaPrimaRealDAO.alterarQuantidade(id, quantidade);
	}
	@Override
	public MateriaPrimaReal procuraPeloId(int id) {
		return this.materiaPrimaRealDAO.procuraPeloId(id);
	}
	@Override
	public ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id){
		return this.materiaPrimaRealDAO.procuraPeloIdExterno(id);
	}

	@Override
	public ArrayList<MateriaPrimaReal> procuraTodos() {
		return this.materiaPrimaRealDAO.procuraTodos();
	}

	@Override
	public void validarCadastro(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException {
		ArrayList<String> erros = new ArrayList<String>();
		if(materiaPrimaReal == null) {
			erros.add("Tentou inserir uma MateriaPrimaReal nula");
		}
		if(this.fornecedorService.procuraPeloId(materiaPrimaReal.getFornecedor())== null) {
			erros.add("Tentou inserir sem Fornecedor");
		}
		if(this.materiaPrimaService.procuraPeloId(materiaPrimaReal.getIdExterno())== null) {
			erros.add("Tentou inserir sem um tipo de matéria-prima");
		}
		if(materiaPrimaReal.getPreço() < 0) {
			erros.add("Tentou inserir um preço negativo");
		}
		if(materiaPrimaReal.getQuantidade() < 0) {
			erros.add("Tentou inserir uma quantidade negativa");
		} 
		if (erros.size()>0) {
			throw new BusinessRuleException(erros);
		}

	}

}
