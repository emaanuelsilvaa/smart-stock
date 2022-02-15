package DATA;

import java.util.ArrayList;

import ENTITY.MateriaPrima;

public interface IMateriaPrimaDAO {

	int inserir(MateriaPrima materiaPrima);

	int remover(int id);

	int alterar(int id, MateriaPrima materiaPrima);

	/*@ pure @*/ MateriaPrima procuraPeloId(int id);

	/*@ pure @*/ ArrayList<MateriaPrima> procuraTodos();
	
	public int pegaEIncremanetaId();

}