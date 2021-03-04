package DATA;

import java.util.ArrayList;

import ENTITY.ProdutoFinalReal;

public interface IProdutoFinalRealDAO {

	int inserir(ProdutoFinalReal produto);

	int remover(int id);

	int alterar();

	ProdutoFinalReal procuraPeloId(int id);

	int alterarQuantidade(int id, int unidades);

	ArrayList<ProdutoFinalReal> procuraPeloIdExterno(int id);

	ArrayList<ProdutoFinalReal> procuraTodos();

}