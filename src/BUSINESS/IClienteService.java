package BUSINESS;

import java.util.ArrayList;

import ENTITY.Cliente;

public interface IClienteService {

	int inserir(Cliente cliente);

	int remover(int id);

	int alterar(int id, Cliente cliente);

	Cliente procuraPeloId(int id);

	ArrayList<Cliente> procuraTodos();

	int validarCadastro(int id);

}