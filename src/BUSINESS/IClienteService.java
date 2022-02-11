package BUSINESS;

import java.util.ArrayList;

import ENTITY.Cliente;
import UTIL.BusinessRuleException;

public interface IClienteService {
	//@ public model instance IClienteDAO clienteDAO;

	int inserir(Cliente cliente) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int alterar(int id, Cliente cliente) throws BusinessRuleException;
	
	//@ ensures \result == clienteDAO.procuraPeloId(id);
	/*@ pure @*/ Cliente procuraPeloId(int id);

	/*@ pure @*/ ArrayList<Cliente> procuraTodos();

	int validarCadastro(Cliente cliente) throws BusinessRuleException;

}