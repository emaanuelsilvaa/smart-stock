package DATA;

import java.util.ArrayList;

import ENTITY.ProdutoFinal;

public interface IProdutoFinalDAO {

	int inserir(ProdutoFinal produto);

	int remover(int id);

	int alterar(int id, ProdutoFinal produto);

	ProdutoFinal procuraPeloId(int id);

	ArrayList<ProdutoFinal> procuraTodos();

}