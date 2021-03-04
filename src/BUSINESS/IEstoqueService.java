package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinalReal;

public interface IEstoqueService {

	int baixaProdutoFinal(int id, int quantidade);
	
	int reporProdutoFinal(int id, int quantidade);

	int baixaMateriaPrima(int id, float quantidade);

	ArrayList<ProdutoFinalReal> procuraTodosProdutos();

	ArrayList<MateriaPrimaReal> procuraTodasMaterias();

	boolean verificaDisponibilidadeProduto(int id, int quantidade);

	boolean verificaDisponibilidadeMateriaPrima(int id, float quantidade);

}