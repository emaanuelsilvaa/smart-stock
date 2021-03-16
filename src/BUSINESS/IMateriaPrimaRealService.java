package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import UTIL.BusinessRuleException;

public interface IMateriaPrimaRealService {

	int inserir(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int alterar(int id, MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

	int alterarQuantidade(int id, float quantidade) throws BusinessRuleException;

	MateriaPrimaReal procuraPeloId(int id);

	ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id);

	ArrayList<MateriaPrimaReal> procuraTodos();

	void validarCadastro(MateriaPrimaReal materiaPrimaReal) throws BusinessRuleException;

}