package BUSINESS;

import java.util.ArrayList;

import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public interface IProdutoFinalRealService {

	int inserir(ProdutoFinalReal produto) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int alterar(int id, ProdutoFinalReal produto) throws BusinessRuleException;

	int alterarQuantidade(int id, int unidades);

	/*@ pure @*/ ProdutoFinalReal procuraPeloId(int id);

	/*@ pure @*/ ArrayList<ProdutoFinalReal> procuraPeloIdExterno(int id);

	/*@ pure @*/ ArrayList<ProdutoFinalReal> procuraTodos();

	void validarCadastro(ProdutoFinalReal produtoFinalReal) throws BusinessRuleException;

}