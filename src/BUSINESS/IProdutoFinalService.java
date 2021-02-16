package BUSINESS;

import java.util.ArrayList;

public interface IProdutoFinalService {

	int inserir(ProdutoFinal produto);

	int remover(ProdutoFinal produto);

	ProdutoFinal procuraUm(ProdutoFinal produto);

	ArrayList<ProdutoFinal> procuraTodos();

	int alterar();

}