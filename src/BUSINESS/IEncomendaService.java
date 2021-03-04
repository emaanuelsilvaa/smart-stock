package BUSINESS;

import java.util.ArrayList;

import ENTITY.Encomenda;

public interface IEncomendaService {

	ArrayList<Encomenda> procuraTodos();

	Encomenda procuraPeloId(int id);

	int inserir(Encomenda encomenda);

	boolean remover(int id);

	int cosumarEncomenda(Encomenda encomenda);

	int alterar(int id, Encomenda encomenda);

}