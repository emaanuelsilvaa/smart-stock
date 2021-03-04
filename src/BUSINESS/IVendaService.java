package BUSINESS;

import java.util.ArrayList;
import java.util.HashMap;

import ENTITY.Cliente;
import ENTITY.ProdutoFinal;
import ENTITY.Venda;

public interface IVendaService {

	ArrayList<Venda> procuraTodos();
	
	Venda procuraPeloId(int id);
	
	boolean realizarVenda(HashMap<Integer, Integer> listaProdutos, int idCliente);
	
	boolean cancelarVenda(int id);
	
}