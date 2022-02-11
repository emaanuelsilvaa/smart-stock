package BUSINESS;

import java.util.ArrayList;
import java.util.HashMap;

import ENTITY.Venda;
import UTIL.BusinessRuleException;

public interface IVendaService {
	
	//@ public model instance String gender;
	
	int remover(int id) throws BusinessRuleException;

	/*@ pure @*/ ArrayList<Venda> procuraTodos();
	
	/*@ pure @*/ Venda procuraPeloId(int id);
	
	int realizarVenda(HashMap<Integer, Integer> listaProdutos, int idCliente) throws BusinessRuleException;	
	
	int cancelarVenda(int id) throws BusinessRuleException;

	void validarCadastro(Venda venda) throws BusinessRuleException;
	
}