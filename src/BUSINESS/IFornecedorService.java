package BUSINESS;

import java.util.ArrayList;

import ENTITY.Fornecedor;

public interface IFornecedorService {

	int inserir(Fornecedor fornecedor);

	int remover(int id);

	int alterar();

	Fornecedor procuraPeloId(int id);

	ArrayList<Fornecedor> procuraTodos();

	int validarCadastro(int id);

}