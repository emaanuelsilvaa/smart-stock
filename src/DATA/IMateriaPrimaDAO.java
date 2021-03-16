package DATA;

import java.util.ArrayList;

import ENTITY.MateriaPrima;

public interface IMateriaPrimaDAO {

	int inserir(MateriaPrima materiaPrima);

	int remover(int id);

	int alterar(int id, MateriaPrima materiaPrima);

	MateriaPrima procuraPeloId(int id);

	ArrayList<MateriaPrima> procuraTodos();
	
	public int pegaEIncremanetaId();

}