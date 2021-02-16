package DATA;

import java.util.ArrayList;

import BUSINESS.MateriaPrima;

public interface IMateriaPrimaDAO {

	int inserir(MateriaPrima materiaPrima);

	int remover(int id);

	int alterar();

	MateriaPrima procuraPeloId(int id);

	ArrayList<MateriaPrima> procuraTodos();

}