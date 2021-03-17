package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;
import UTIL.BusinessRuleException;

public interface IEncomendaService {

	ArrayList<Encomenda> procuraTodos();

	Encomenda procuraPeloId(int id);

	int inserir(Encomenda encomenda);

	boolean remover(int id);

	int cosumarEncomenda(int id) throws BusinessRuleException;

	int alterar(int id, Encomenda encomenda);

	int realizarEncomenda(HashMap<Integer, Integer> listaProdutos, int idCliente, Date dataEntrega);

}