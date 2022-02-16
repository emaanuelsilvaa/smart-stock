package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import UTIL.BusinessRuleException;

public interface IMateriaPrimaRealService {

	int inserir(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int alterar(int id, MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

	int alterarQuantidade(int id, float quantidade) throws BusinessRuleException;

	/*@ pure @*/ MateriaPrimaReal procuraPeloId(int id);

	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id);

	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodos();

	void validarCadastro(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

}