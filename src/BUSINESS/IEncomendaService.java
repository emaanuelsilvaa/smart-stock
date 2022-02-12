package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;
import UTIL.BusinessRuleException;

public interface IEncomendaService {
	//@ public model instance ArrayList<Encomenda> encomendas;
	//@ public model instance Encomenda enc;
	
	//@ ensures encomendas.equals(\result);
	/*@ pure @*/ ArrayList<Encomenda> procuraTodos();

	/*@ assignable enc;
	 @  ensures enc != null;
	 @	ensures enc.getId() == id;
	 @  also
	 @  assignable enc;
	 @  ensures enc == null;
	@*/
	/*@ pure @*/ Encomenda procuraPeloId (int id);

	/*@ requires encomenda == null;
	 @  requires encomenda.getValor() <= 0;
	 @  requires encomenda.getIdCliente() < 1;
	 @  requires encomenda.getListaProdutos().isEmpty();
	 @  signals_only BusinessRuleException;
	@*/
	int inserir(Encomenda encomenda) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int consumarEncomenda(int id) throws BusinessRuleException;

	/*@ requires encomenda == null;
	 @  requires encomenda.getValor() <= 0;
	 @  requires encomenda.getIdCliente() < 1;
	 @  requires encomenda.getListaProdutos().isEmpty();
	 @  signals_only BusinessRuleException;
	@*/
	int alterar(int id, Encomenda encomenda) throws BusinessRuleException;

	/*@ ensures enc.getId() == \result;
	 @  ensures enc.getIdCliente() == idCliente;
  	 @  ensures enc.getListaProdutos().equals(listaProdutos);
	 @  ensures enc.getDataEntrega().compareTo(dataEntrega) == 0;
	@*/
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