package DATA;

import java.util.ArrayList;

import ENTITY.Fornecedor;

public interface IFornecedorDAO {

	int inserir(Fornecedor fornecedor);

	int remover(int id);

	int alterar(int id, Fornecedor fornecedor);

	Fornecedor procuraPeloId(int id);

	ArrayList<Fornecedor> procuraTodos();

	int pegaEIncremanetaId();

}