package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public interface IEstoqueService {
	
	/*@ requires id > 0;
	 @  requires quantidade > 0;
	 @  ensures \result == 0; 
	@*/
	int baixaProdutoFinal(int id, int quantidade);
	
	/*@ requires id > 0;
	 @  requires quantidade > 0;
	 @  ensures \result == 0; 
	@*/
	int reporProdutoFinal(int id, int quantidade);

	/*@ requires id > 0;
	 @  requires quantidade > 0;
	 @  ensures \result == 0;
	 @  also
	 @  requires quantidade < 0;
	 @  signals_only BusinessRuleException;
	@*/
	int baixaMateriaPrima(int id, float quantidade) throws BusinessRuleException;

	/*@ pure @*/ ArrayList<ProdutoFinalReal> procuraTodosProdutos();

	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodasMaterias();

	/*@ pure @*/ int verificaDisponibilidadeProduto(int id, int quantidade);

	/*@ pure @*/ float verificaDisponibilidadeMateriaPrima(int id, float quantidade);

}