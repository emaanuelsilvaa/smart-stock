package BUSINESS;

import java.util.ArrayList;

import ENTITY.ProdutoFinalReal;

public interface IProdutoFinalRealService {

	int inserir(ProdutoFinalReal produto);

	int remover(int id);

	int alterar();

	int alterarQuantidade(int id, int unidades);

	ProdutoFinalReal procuraPeloId(int id);

	ArrayList<ProdutoFinalReal> procuraPeloIdExterno(int id);

	ArrayList<ProdutoFinalReal> procuraTodos();

}