package BUSINESS;

import ENTITY.ProdutoFinal;
import UTIL.BusinessRuleException;

public interface ValidarStrategy {
	void validarEspecificidades (ProdutoFinal produtoFinal) throws BusinessRuleException;
}
