package DATA;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;

public interface IMateriaPrimaRealDAO {

	int inserir(MateriaPrimaReal materiaPrima);

	int remover(int id);

	int alterar(int id, MateriaPrimaReal materiaPrima);

	int alterarQuantidade(int id, float quantidade);

	MateriaPrimaReal procuraPeloId(int id);

	ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id);

	ArrayList<MateriaPrimaReal> procuraTodos();

}