package BUSINESS;

import java.util.ArrayList;

import DATA.IMateriaPrimaRealDAO;
import DATA.MateriaPrimaRealDAO;
import ENTITY.MateriaPrimaReal;
import UTIL.BusinessRuleException;

public final class MateriaPrimaRealService implements IMateriaPrimaRealService {

	protected /*@ spec_public non_null @*/ IMateriaPrimaRealDAO materiaPrimaRealDAO;
	protected /*@ spec_public non_null @*/ IMateriaPrimaService materiaPrimaService;
	protected /*@ spec_public non_null @*/ IFornecedorService fornecedorService;
	private /*@ spec_public non_null @*/ static IMateriaPrimaRealService instance;

	/*@ assignable materiaPrimaRealDAO, materiaPrimaService, fornecedorService;
	 @  ensures materiaPrimaRealDAO != null;
	 @  ensures materiaPrimaService != null;
	 @  ensures fornecedorService != null;
	@*/
	private MateriaPrimaRealService() {
		this.materiaPrimaRealDAO = new MateriaPrimaRealDAO();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
		this.fornecedorService = FornecedorService.getInstance();
	}
	
	/*@ requires instance == null; 
	 @  assignable instance;
	 @  ensures instance == \result;
	 @  also
	 @  requires instance != null;
	 @  assignable instance;
	 @  ensures instance == \old(instance);
	@*/
	public static IMateriaPrimaRealService getInstance() {
		if (instance == null) {
			instance = new MateriaPrimaRealService();
		}
		return instance;
	}

	/*@ assignable materiaPrimaRealDAO, materiaPrimaService, fornecedorService;
	 @  ensures fornecedorService != null;
	 @ 	ensures this.materiaPrimaRealDAO == materiaPrimaRealDAO;
	 @  ensures this.materiaPrimaService == materiaPrimaService;
	@*/
	public MateriaPrimaRealService(IMateriaPrimaRealDAO materiaPrimaRealDAO, IMateriaPrimaService materiaPrimaService) {
		super();
		this.materiaPrimaRealDAO = materiaPrimaRealDAO;
		this.materiaPrimaService = materiaPrimaService;
	}

	@Override
	/*@ also
	 @  requires \same;
	 @  requires fornecedorService.procuraPeloId(materiaPrimaReal.getFornecedor()) == null;
	 @  requires materiaPrimaService.procuraPeloId(materiaPrimaReal.getIdExterno()) == null;
	 @  signals_only BusinessRuleException;
	 @  also
	 @  requires materiaPrimaReal != null;
	 @  requires materiaPrimaReal.getPreco() >= 0;
	 @  requires materiaPrimaReal.getQuantidade() >= 0;
	 @  requires fornecedorService.procuraPeloId(materiaPrimaReal.getFornecedor()) == null;
	 @  requires materiaPrimaService.procuraPeloId(materiaPrimaReal.getIdExterno()) == null;
	 @  ensures materiaPrimaReal.getId() == \result;
	@*/
	public int inserir(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException {
		this.validarCadastro(materiaPrimaReal);
		return this.materiaPrimaRealDAO.inserir(materiaPrimaReal);
	}

	/*@ also 
	 @ requires materiaPrimaRealDAO.procuraPeloId(id) != null;
	 @  ensures \result == id;
	 @  also
	 @  requires materiaPrimaRealDAO.procuraPeloId(id) == null;
	 @  signals_only BusinessRuleException;
	@*/
	@Override
	public int remover(int id) throws BusinessRuleException{
		if (this.materiaPrimaRealDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir uma matéria-prima real inexistente");
		}
		return 	this.materiaPrimaRealDAO.remover(id);
	}

	/*@ also
	 @  requires \same;
	 @  requires fornecedorService.procuraPeloId(materiaPrimaReal.getFornecedor()) == null;
	 @  requires materiaPrimaService.procuraPeloId(materiaPrimaReal.getIdExterno()) == null;
	 @  requires materiaPrimaRealDAO.procuraPeloId(id) == null;
	 @  signals_only BusinessRuleException;
	 @  also
	 @  requires materiaPrimaReal != null;
	 @  requires materiaPrimaReal.getPreco() >= 0;
	 @  requires materiaPrimaReal.getQuantidade() >= 0;
	 @  requires fornecedorService.procuraPeloId(materiaPrimaReal.getFornecedor()) == null;
	 @  requires materiaPrimaService.procuraPeloId(materiaPrimaReal.getIdExterno()) == null;
	 @  requires materiaPrimaRealDAO.procuraPeloId(id) != null;
	 @  ensures id == \result;
	@*/
	@Override
	public int alterar(int id, MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException {
		this.validarCadastro(materiaPrimaReal);
		if (this.materiaPrimaRealDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		return this.materiaPrimaRealDAO.alterar(id, materiaPrimaReal);
	}
	
	/*@ also
	 @  requires quantidade >= 0;
	 @  ensures materiaPrimaRealDAO.procuraPeloId(id).getQuantidade() ==
	 @  	\old(materiaPrimaRealDAO.procuraPeloId(id).getQuantidade()) + quantidade;
	@*/
	@Override
	public int alterarQuantidade(int id, float quantidade) throws BusinessRuleException {
		if (quantidade < 0){
			throw new BusinessRuleException("Quantidade negativa");
		}
		return this.materiaPrimaRealDAO.alterarQuantidade(id, quantidade);
	}
	@Override
	public /*@ pure @*/ MateriaPrimaReal procuraPeloId(int id) {
		return this.materiaPrimaRealDAO.procuraPeloId(id);
	}
	@Override
	public ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id){
		return this.materiaPrimaRealDAO.procuraPeloIdExterno(id);
	}

	@Override
	public /*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodos() {
		return this.materiaPrimaRealDAO.procuraTodos();
	}

	
	/*@ also
	 @  requires \same;
	 @  requires fornecedorService.procuraPeloId(materiaPrimaReal.getFornecedor()) == null;
	 @  requires materiaPrimaService.procuraPeloId(materiaPrimaReal.getIdExterno()) == null;
	 @  signals_only BusinessRuleException;
	@*/
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
		if(materiaPrimaReal.getPreco() < 0) {
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
