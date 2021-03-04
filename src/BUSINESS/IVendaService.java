package BUSINESS;

import java.util.ArrayList;
import java.util.HashMap;

import ENTITY.Cliente;
import ENTITY.ProdutoFinal;
import ENTITY.Venda;

public interface IVendaService {

	ArrayList<Venda> procuraTodos();

	boolean realizarVenda(HashMap<Integer, Integer> listaProdutos, int idCliente);

	boolean realizarVenda(ArrayList<ProdutoFinal> listaDeProdutos, Cliente comprador);

}