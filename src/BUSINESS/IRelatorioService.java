package BUSINESS;

import java.util.Date;
import java.util.HashMap;

public interface IRelatorioService {

	HashMap<Integer, Integer> listarReposicaoProduto(Date dataInicio, Date dataFim);

	HashMap<Integer, Float> listarReposicaoMateriaPrima(Date dataInicio, Date dataFim);

}