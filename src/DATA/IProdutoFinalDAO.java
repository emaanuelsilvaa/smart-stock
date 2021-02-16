package DATA;

import java.util.ArrayList;

import BUSINESS.ProdutoFinal;

public interface IProdutoFinalDAO {

	int inserir(ProdutoFinal produto);

	int remover(int id);

	int alterar();

	int alterarQuantidade(int id, int unidades);

	ProdutoFinal procuraPeloId(int id);

	ArrayList<ProdutoFinal> procuraTodos();

}