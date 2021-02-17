package BUSINESS;

import java.util.ArrayList;

public interface IFornecedorService {

	int inserir(Fornecedor fornecedor);

	int remover(int id);

	int alterar();

	Fornecedor procuraPeloId(int id);

	ArrayList<Fornecedor> procuraTodos();

}