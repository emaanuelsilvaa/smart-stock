package BUSINESS;

import java.util.ArrayList;

public interface IProdutoFinalService {

	int inserir(ProdutoFinal produto);

	int remover(int id);

	ProdutoFinal procuraUm(int id);

	ArrayList<ProdutoFinal> procuraTodos();

	int alterar();

}