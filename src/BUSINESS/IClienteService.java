package BUSINESS;

import java.util.ArrayList;

import ENTITY.Cliente;
import UTIL.BusinessRuleException;

public interface IClienteService {
	//@ public model instance IClienteDAO clienteDAO;
	
	/*@ public normal_behavior
	  @ requires  cliente!=null;
	  @ ensures (\forall Cliente i; i != cliente ;clienteDAO.procuraTodos.get(i)<==>\old(clienteDAO.procuraTodos.get(i)));
	  @ ensures clienteDAO.procuraTodos.size() == \old(clienteDAO.procuraTodos.size())+1;
	  */
	int inserir(Cliente cliente) throws BusinessRuleException;
	
	/*@ 
	  @ requires  id != null && id >= 0;
	  @ ensures clienteDAO.procuraTodos.size() == \old(clienteDAO.procuraTodos.size())-1;
	  @*/
	int remover(int id) throws BusinessRuleException;

	/*@ public normal_behavior
	  @ requires  cliente!=null;
	  @ requires  id != null;
	  @ ensures clienteDAO.procuraTodos.size() == \old(clienteDAO.procuraTodos.size());
	  @ ensures \result == (\exists int i; i<=0 && i< clienteDAO.procuraTodos.size() ; clienteDAO.procuraTodos.get(i).equals(cliente)); 
	  @*/
	int alterar(int id, Cliente cliente) throws BusinessRuleException;
	
	//@ ensures id > 0;
	/*@ pure @*/ Cliente procuraPeloId(int id);

	/*@ pure @*/ ArrayList<Cliente> procuraTodos();

	int validarCadastro(Cliente cliente) throws BusinessRuleException;

}