package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;
import UTIL.BusinessRuleException;

public interface IEncomendaService {
	//@ public model instance ArrayList<Encomenda> encomendas;
	//@ public model instance Encomenda encomenda;
	
	//@ ensures encomendas.equals(\result);
	ArrayList<Encomenda> procuraTodos();

	/*@ assignable encomenda;
	 @  ensures encomenda != null;
	 @	ensures encomenda.getId() == id;
	 @  also
	 @  assignable encomenda;
	 @  ensures encomenda == null;
	@*/
	Encomenda procuraPeloId (int id);

	int inserir(Encomenda encomenda) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int consumarEncomenda(int id) throws BusinessRuleException;

	int alterar(int id, Encomenda encomenda) throws BusinessRuleException;

	// /*@ ensures encomenda.getId() == \result;
	//  @  ensures encomenda.getIdCliente() == idCliente;
	//  @  ensures encomenda.getListaProdutos().equals(listaProdutos);
	//  @  ensures encomenda.getDataEntrega().compareTo(dataEntrega) == 0;
	// @*/
	int realizarEncomenda(HashMap<Integer, Integer> listaProdutos, int idCliente, Date dataEntrega);

	/*@ requires encomenda != null;
	 @  requires encomenda.getValor() > 0;
	 @  requires encomenda.getIdCliente() >= 1;
	 @  requires !encomenda.getListaProdutos().isEmpty();
	 @  ensures \result == 0;
	 @  also
	 @  requires encomenda == null;
	 @  requires encomenda.getValor() <= 0;
	 @  requires encomenda.getIdCliente() < 1;
	 @  requires encomenda.getListaProdutos().isEmpty();
	 @  signals_only BusinessRuleException;
	@*/
	int validarCadastro(Encomenda encomenda) throws BusinessRuleException;

}