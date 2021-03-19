package BUSINESS;

import java.util.Date;
import java.util.HashMap;

import UTIL.BusinessRuleException;

public interface IRelatorioService {

	HashMap<Integer, Integer> listarReposicaoProduto(Date dataInicio, Date dataFim) throws BusinessRuleException;

	HashMap<Integer, Float> listarReposicaoMateriaPrima(Date dataInicio, Date dataFim)  throws BusinessRuleException;

}