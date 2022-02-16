package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import UTIL.BusinessRuleException;

public interface IMateriaPrimaRealService {
	
	/*@ requires materiaPrimaReal == null;
	 @  requires materiaPrimaReal.getPreco() < 0;
	 @  requires materiaPrimaReal.getQuantidade() < 0; 
	 @  signals_only BusinessRuleException;
	@*/
	int inserir(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	/*@ requires materiaPrimaReal == null;
	 @  requires materiaPrimaReal.getPreco() < 0;
	 @  requires materiaPrimaReal.getQuantidade() < 0; 
	 @  signals_only BusinessRuleException;
	@*/
	int alterar(int id, MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

	/*@ requires quantidade < 0;
	 @  signals_only BusinessRuleException;
	@*/
	int alterarQuantidade(int id, float quantidade) throws BusinessRuleException;

	/*@ pure @*/ MateriaPrimaReal procuraPeloId(int id);

	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id);

	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodos();

	/*@ requires materiaPrimaReal == null;
	 @  requires materiaPrimaReal.getPreco() < 0;
	 @  requires materiaPrimaReal.getQuantidade() < 0; 
	 @  signals_only BusinessRuleException;
	@*/
	void validarCadastro(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

}