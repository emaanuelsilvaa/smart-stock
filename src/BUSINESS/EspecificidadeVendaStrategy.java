package BUSINESS;

import java.util.ArrayList;

import ENTITY.Especificidade;
import ENTITY.ProdutoFinal;
import ENTITY.Venda;
import UTIL.BusinessRuleException;

public interface EspecificidadeVendaStrategy {
	ArrayList<String> validarEspecificidades (Especificidade especificidade, Venda venda);
}
