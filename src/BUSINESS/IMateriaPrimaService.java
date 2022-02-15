package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;

public interface IMateriaPrimaService {
	// public model instance ArrayList<MateriaPrima> materiasPrima;
	// public model instance MateriaPrima mat;
	// public model instance IMateriaPrimaDAO materiaPrimaDAOModel;
	
	/*@
	  @ requires  materiaPrima!=null;
	  @ requires materiaPrima.getPreco() < 0;
	  @ requires materiaPrima.getQuantidade();
	  @ requires materiaPrima.getQntMinima() < 0;
	  @ ensures (\forall IMateriaPrimaDAO i; i != materiaPrima ; materiaPrimaDAO.procuraTodos.get(i)<==>\old(materiaPrimaDAO.procuraTodos.get(i)));
	  @ ensures clienteDAO.procuraTodos.size() == \old(clienteDAO.procuraTodos.size())+1;
	  @  signals_only BusinessRuleException;
	  */
	int inserir(MateriaPrima materiaPrima) throws BusinessRuleException;
	
	
	/*@ requires  id != null;
	  @ ensures materiaPrimaDAO instanceof materiaPrimaDAO;
	  @ ensures materiaPrimaDAO.procuraTodos.size() == \old(materiaPrimaDAO.procuraTodos.size())+1;
	  @  signals_only BusinessRuleException;
	  */
	void remover(int id) throws BusinessRuleException;

	/*@ requires  id != null;
	  @ requires id >=0 ;
	  @ requires materiaPrima != null;
	  @ ensures materiaPrimaDAO instanceof materiaPrimaDAO;
	  @ ensures materiaPrimaDAO.procuraTodos.size() == \old(materiaPrimaDAO.procuraTodos.size());
	  @  signals_only BusinessRuleException;
	  */
	int alterar(int id, MateriaPrima materiaPrima) throws BusinessRuleException;

	/*@ requires  id != null;
	  @ requires id >=0 ;
	  */
	/*@ pure @*/ MateriaPrima procuraPeloId(int id);

	
	/*@ pure @*/ ArrayList<MateriaPrima> procuraTodos();

	void validarCadastro(MateriaPrima materiaPrimaid) throws BusinessRuleException;

}