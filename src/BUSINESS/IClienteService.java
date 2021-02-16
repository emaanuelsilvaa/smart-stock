package BUSINESS;

import java.util.ArrayList;

public interface IClienteService {

	int inserir(Cliente cliente);

	int remover(int id);

	int alterar();

	Cliente procuraPeloId(int id);

	ArrayList<Cliente> procuraTodos();

}