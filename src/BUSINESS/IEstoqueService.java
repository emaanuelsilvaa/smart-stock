package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public interface IEstoqueService {
	//@ public model instance ArrayList<ProdutoFinalReal> produtos;
	//@ public model instance ArrayList<MateriaPrimaReal> materias_primas;
	
	int baixaProdutoFinal(int id, int quantidade);
	
	int reporProdutoFinal(int id, int quantidade);

	/*@ requires quantidade < 0;
	 @  signals_only BusinessRuleException;
	@*/
	int baixaMateriaPrima(int id, float quantidade) throws BusinessRuleException;

	//@ ensures produtos.equals(\result);
	/*@ pure @*/ ArrayList<ProdutoFinalReal> procuraTodosProdutos();

	//@ ensures materias_primas.equals(\result);
	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodasMaterias();

	int verificaDisponibilidadeProduto(int id, int quantidade);

	float verificaDisponibilidadeMateriaPrima(int id, float quantidade);

}