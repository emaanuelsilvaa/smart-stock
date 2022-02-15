package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;

public interface IMateriaPrimaService {
	
	/*@
	  @ requires  materiaPrima!=null;
	  @  signals_only BusinessRuleException;
	  */
	int inserir(MateriaPrima materiaPrima) throws BusinessRuleException;
	
	
	/*@ requires  id > 0;
	  @  signals_only BusinessRuleException;
	  */
	void remover(int id) throws BusinessRuleException;

	/*@ 
	  @ requires id >0 ;
	  @ requires materiaPrima != null;
	  @  signals_only BusinessRuleException;
	  */
	int alterar(int id, MateriaPrima materiaPrima) throws BusinessRuleException;

	/*@ 
	  @ requires id >0 ;
	  */
	/*@ pure @*/ MateriaPrima procuraPeloId(int id);

	
	/*@ pure @*/ ArrayList<MateriaPrima> procuraTodos();

	void validarCadastro(MateriaPrima materiaPrimaid) throws BusinessRuleException;

}