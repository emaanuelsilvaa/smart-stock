package DATA;

import java.util.ArrayList;

import ENTITY.Cliente;

public interface IClienteDAO {

	int inserir(Cliente cliente);

	int remover(int id);

	int alterar(int id, Cliente cliente);

	Cliente procuraPeloId(int id);

	ArrayList<Cliente> procuraTodos();

}