package BUSINESS;

import java.util.ArrayList;
import java.util.HashMap;

import ENTITY.Venda;
import UTIL.BusinessRuleException;

public interface IVendaService {
	
	int remover(int id) throws BusinessRuleException;

	ArrayList<Venda> procuraTodos();
	
	Venda procuraPeloId(int id);
	
	int realizarVenda(HashMap<Integer, Integer> listaProdutos, int idCliente) throws BusinessRuleException;	
	
	int cancelarVenda(int id) throws BusinessRuleException;

	void validarCadastro(Venda venda) throws BusinessRuleException;
	
}