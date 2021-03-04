package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;

public interface IEncomendaService {

	ArrayList<Encomenda> procuraTodos();

	Encomenda procuraPeloId(int id);

	int inserir(Encomenda encomenda);

	boolean remover(int id);

	int cosumarEncomenda(Encomenda encomenda);

	int alterar(int id, Encomenda encomenda);

	int realizarEncomenda(HashMap<Integer, Integer> listaProdutos, int idCliente, Date dataEntrega);

}