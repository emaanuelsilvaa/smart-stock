package BUSINESS;

import java.util.ArrayList;

import ENTITY.Cliente;
import UTIL.BusinessRuleException;

public interface IClienteService {

	int inserir(Cliente cliente) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int alterar(int id, Cliente cliente) throws BusinessRuleException;

	Cliente procuraPeloId(int id);

	ArrayList<Cliente> procuraTodos();

	int validarCadastro(Cliente cliente) throws BusinessRuleException;

	int pegaEIncremanetaId();

}