package BUSINESS;

import java.util.ArrayList;

import ENTITY.Fornecedor;
import UTIL.BusinessRuleException;

public interface IFornecedorService {

	int inserir(Fornecedor fornecedor) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int alterar(int id, Fornecedor fornecedor) throws BusinessRuleException;

	/*@ pure @*/ Fornecedor procuraPeloId(int id);

	/*@ pure @*/ ArrayList<Fornecedor> procuraTodos();

	int validarCadastro(Fornecedor fornecedor) throws BusinessRuleException;

}