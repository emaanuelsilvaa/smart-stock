package DATA;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;

public interface IMateriaPrimaRealDAO {

	int inserir(MateriaPrimaReal materiaPrima);

	int remover(int id);

	int alterar(int id, MateriaPrimaReal materiaPrima);

	int alterarQuantidade(int id, float quantidade);

	/*@ pure @*/MateriaPrimaReal procuraPeloId(int id);

	/*@ pure @*/ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id);

	/*@ pure @*/ArrayList<MateriaPrimaReal> procuraTodos();

}