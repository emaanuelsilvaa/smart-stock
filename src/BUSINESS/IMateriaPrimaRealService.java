package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import UTIL.BusinessRuleException;

public interface IMateriaPrimaRealService {
	//@ public model instance ArrayList<MateriaPrimaReal> materiais;
	//@ public model instance MateriaPrimaReal mat;
	
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

	/*@ assignable mat;
	 @  ensures mat != null;
	 @	ensures mat.getId() == id;
	 @  also
	 @  assignable mat;
	 @  ensures mat == null;
	@*/
	/*@ pure @*/ MateriaPrimaReal procuraPeloId(int id);

	/*@ assginable materiais;
	 @  ensures (\forall int i; i >= 0 && i < materiais.size();
	 @  			materiais[i].getIdExterno() == id
	 @  		);
	@*/
	ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id);

	//@ ensures materiais.equals(\result);
	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodos();

	/*@ requires materiaPrimaReal == null;
	 @  requires materiaPrimaReal.getPreco() < 0;
	 @  requires materiaPrimaReal.getQuantidade() < 0; 
	 @  signals_only BusinessRuleException;
	@*/
	void validarCadastro(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

}