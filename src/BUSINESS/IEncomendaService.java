package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;
import UTIL.BusinessRuleException;

public interface IEncomendaService {
	
	/*@ pure @*/ ArrayList<Encomenda> procuraTodos();

	/*@ pure @*/ Encomenda procuraPeloId (int id);

	/*@ requires encomenda != null;
	 @  requires encomenda.getValor() > 0;
	 @  requires encomenda.getIdCliente() >= 1;
	 @  requires !encomenda.getListaProdutos().isEmpty();
	 @  ensures \result == encomenda.getId();
	 @  also
	 @  requires encomenda == null;
	 @  requires encomenda.getValor() <= 0;
	 @  requires encomenda.getIdCliente() < 1;
	 @  requires encomenda.getListaProdutos().isEmpty();
	 @  signals_only BusinessRuleException;
	@*/
	int inserir(Encomenda encomenda) throws BusinessRuleException;

	//@ ensures \result == id;
	int remover(int id) throws BusinessRuleException;

	/*@ requires id > 0;
	 @  ensures \result == id;
	 @  also
	 @  requires id <= 0;
	 @  signals_only BusinessRuleException;
	@*/
	int consumarEncomenda(int id) throws BusinessRuleException;

	/*@ requires encomenda != null;
	 @  requires encomenda.getValor() > 0;
	 @  requires encomenda.getIdCliente() >= 1;
	 @  requires !encomenda.getListaProdutos().isEmpty();
	 @  ensures \result == id;
	 @  also
	 @  requires encomenda == null;
	 @  requires encomenda.getValor() <= 0;
	 @  requires encomenda.getIdCliente() < 1;
	 @  requires encomenda.getListaProdutos().isEmpty();
	 @  signals_only BusinessRuleException;
	@*/
	int alterar(int id, Encomenda encomenda) throws BusinessRuleException;

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