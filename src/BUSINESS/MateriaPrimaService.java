package BUSINESS;

import java.util.ArrayList;

import DATA.IMateriaPrimaDAO;
import DATA.MateriaPrimaDAO;
import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;

public final class MateriaPrimaService implements IMateriaPrimaService {
	
	protected /*@ spec_public non_null @*/ IMateriaPrimaDAO materiaPrimaDAO; 
	private /*@ spec_public non_null @*/ static IMateriaPrimaService instance;
	
	
	
	/** Construtores */
	
	/*@ assignable this.materiaPrimaDAO;
	 @ 
	 @  ensures materiaPrimaDAO != null;
	 @  ensures materiaPrimaDAO instanceof MateriaPrimaDAO;
	@*/
	private MateriaPrimaService() {
		this.materiaPrimaDAO = new MateriaPrimaDAO();
	}

	/*@ requires instance == null; 
	 @  assignable instance;
	 @  ensures instance == \result;
	 @  also
	 @  requires instance != null;
	 @  assignable instance;
	 @  ensures instance == \old(instance);
	@*/
	public static IMateriaPrimaService getInstance() {
		if(instance == null) {
			instance = new MateriaPrimaService();
		}
		return instance;
	}
	
	
	/*@ also 
	@requires materiaPrima != null;
	@ assignable this.materiaPrimaDAO;
	@ ensures materiaPrimaDAO.procuraTodos().size() == \old(materiaPrimaDAO.procuraTodos().size())+1;
	@ also
	@ public exceptional_behavior
	@ 	requires this.materiaPrimaDAO.procuraPeloId(materiaPrima.getId()) != null;
	@ 	assignable this.materiaPrimaDAO;
	@ 	signals_only BusinessRuleException;
	@*/
	@Override
	public int inserir(MateriaPrima materiaPrima) throws BusinessRuleException {
		validarCadastro(materiaPrima);
		return this.materiaPrimaDAO.inserir(materiaPrima);
	}
	

	/*@ also 
    @ requires id > 0;
	@ assignable this.materiaPrimaDAO, id;
	@ ensures this.materiaPrimaDAO.procuraTodos().size() == \old(this.materiaPrimaDAO.procuraTodos().size())-1;
	@ also
	@ 	requires this.materiaPrimaDAO.procuraPeloId(id) != null;
	@ 	signals_only BusinessRuleException;
	@*/
	@Override
	public void remover(int id) throws BusinessRuleException {
		if(this.materiaPrimaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir uma matéria-prima inexistente");
		}
		this.materiaPrimaDAO.remover(id);	
	}
	
	
	/*@ also 
	@ requires id > 0;
   	@ requires materiaPrima != null;
	@ assignable this.materiaPrimaDAO;
	@ ensures this.materiaPrimaDAO.procuraPeloId(id) == \old(this.materiaPrimaDAO.procuraTodos().size())-1;
	@ also
	@ 	requires this.materiaPrimaDAO.procuraPeloId(id) != null;
	@   requires id < 0;
	@ 	signals_only BusinessRuleException;
	@*/
	@Override
	public int alterar(int id, MateriaPrima materiaPrima) throws BusinessRuleException {
		validarCadastro(materiaPrima);
		if (this.materiaPrimaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		return this.materiaPrimaDAO.alterar(id, materiaPrima);
	}

	@Override
	public /*@ pure @*/ MateriaPrima procuraPeloId(int id) {
		return this.materiaPrimaDAO.procuraPeloId(id);
	}

	@Override
	public /*@ pure @*/ ArrayList<MateriaPrima> procuraTodos() {
		return this.materiaPrimaDAO.procuraTodos();
	}
	
	@Override
	public void validarCadastro(MateriaPrima materiaPrima) throws BusinessRuleException{
		ArrayList<String> erros = new ArrayList<String>();
		if(materiaPrima == null) {
			erros.add("Tentou inserir uma MateriaPrima nula");
		}
		if(materiaPrima.getNome() == "" || materiaPrima.getNome() == null ) {
			erros.add("Tentou inserir uma MateriaPrima com nome nulo");
		}
		if(materiaPrima.getQntMinima() < 0) {
			erros.add("Tentou inserir uma quantidade mínima negativa");
		}
		if(materiaPrima.getTipo() == "" ||  materiaPrima.getTipo() == null) {
			erros.add("Tentou inserir uma tipo nulo");
		}
		if(materiaPrima.getUnMedida() == "" || materiaPrima.getUnMedida() == null) {
			erros.add("Tentou inserir uma quantidade de medida nula");
		}
		if (erros.size()>0) {
			throw new BusinessRuleException(erros);
		}
	}
}
