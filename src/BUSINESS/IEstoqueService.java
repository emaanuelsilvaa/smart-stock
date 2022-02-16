package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public interface IEstoqueService {
	
	int baixaProdutoFinal(int id, int quantidade);
	
	int reporProdutoFinal(int id, int quantidade);

	/*@ requires quantidade < 0;
	 @  signals_only BusinessRuleException;
	@*/
	int baixaMateriaPrima(int id, float quantidade) throws BusinessRuleException;

	/*@ pure @*/ ArrayList<ProdutoFinalReal> procuraTodosProdutos();

	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodasMaterias();

	int verificaDisponibilidadeProduto(int id, int quantidade);

	float verificaDisponibilidadeMateriaPrima(int id, float quantidade);

}