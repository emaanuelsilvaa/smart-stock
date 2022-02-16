package DATA;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;

public interface IMateriaPrimaRealDAO {

	int inserir(MateriaPrimaReal materiaPrima);

	/*@ requires id > 0;
	 @  ensures \result == id;
	@*/
	int remover(int id);

	/*@ requires id > 0;
	 @  requires materiaPrima != null;
	 @  ensures \result == id;
	@*/
	int alterar(int id, MateriaPrimaReal materiaPrima);

	int alterarQuantidade(int id, float quantidade);

	/*@ pure @*/ MateriaPrimaReal procuraPeloId(int id);

	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id);

	/*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodos();

}