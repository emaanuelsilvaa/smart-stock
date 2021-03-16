package BUSINESS;

import java.util.ArrayList;

import ENTITY.ProdutoFinal;
import UTIL.BusinessRuleException;

public interface IProdutoFinalService {

	int inserir(ProdutoFinal produto) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int alterar(int id, ProdutoFinal produto) throws BusinessRuleException;

	ProdutoFinal procuraPeloId(int id);

	ArrayList<ProdutoFinal> procuraTodos();

	int pegaEIncremanetaId();

	void validarCadastro(ProdutoFinal produtoFinal) throws BusinessRuleException;

}