package ENTITY;

import java.util.Date;
import java.util.HashMap;

public class Venda {

	protected /*@ spec_public @*/ int id;
	protected /*@ spec_public @*/ float valor;
	protected /*@ spec_public @*/ int idCliente;
	protected /*@ spec_public @*/ HashMap<Integer, Integer> listaProdutos;
	protected /*@ spec_public @*/ HashMap<Integer, Integer> listaProdutosReais;
	protected /*@ spec_public @*/ Date data;

	public Venda() {

	};

	public Venda(int id, float valor, int idCliente, HashMap<Integer, Integer> listaProdutos, Date data) {
		super();
		this.id = id;
		this.valor = valor;
		this.idCliente = idCliente;
		this.listaProdutos = listaProdutos;
		this.data = data;
	}
	
	public Venda(float valor, int idCliente, HashMap<Integer, Integer> listaProdutos, Date data) {
		super();
		this.valor = valor;
		this.idCliente = idCliente;
		this.listaProdutos = listaProdutos;
		this.data = data;
	}

	public /*@ pure @*/ int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public /*@ pure @*/ float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public /*@ pure @*/ int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public /*@ pure @*/ HashMap<Integer, Integer> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(HashMap<Integer, Integer> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public /*@ pure @*/ HashMap<Integer, Integer> getListaProdutosReais() {
		return this.listaProdutosReais;
	}

	public void setListaProdutosReais(HashMap<Integer, Integer> listaProdutosReais) {
		this.listaProdutosReais = listaProdutosReais;
	}

	public /*@ pure @*/ Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
