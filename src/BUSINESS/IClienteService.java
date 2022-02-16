package BUSINESS;

import java.util.ArrayList;

import ENTITY.Cliente;
import UTIL.BusinessRuleException;

public interface IClienteService {
	//@ public model instance IClienteDAO clienteBase;
	
	/*@
	  @ requires  cliente!=null;
	  */
	int inserir(Cliente cliente) throws BusinessRuleException;
	
	/*@ 
	  @ requires  id > 0;
	  @*/
	int remover(int id) throws BusinessRuleException;

	/*@ public normal_behavior
	  @ requires  cliente!=null;
	 @*/
	int alterar(int id, Cliente cliente) throws BusinessRuleException;
	
	//@ ensures id > 0;
	/*@ pure @*/ Cliente procuraPeloId(int id);

	/*@ pure @*/ ArrayList<Cliente> procuraTodos();

	int validarCadastro(Cliente cliente) throws BusinessRuleException;

}