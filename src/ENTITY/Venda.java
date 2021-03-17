package ENTITY;

import java.util.Date;
import java.util.HashMap;

public class Venda {

	protected int id;
	protected float valor;
	protected int idCliente;
	protected HashMap<Integer, Integer> listaProdutos;
	protected HashMap<Integer, Integer> listaProdutosReais;
	protected Date data;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public HashMap<Integer, Integer> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(HashMap<Integer, Integer> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public HashMap<Integer, Integer> getListaProdutosReais() {
		return this.listaProdutosReais;
	}

	public void setListaProdutosReais(HashMap<Integer, Integer> listaProdutosReais) {
		this.listaProdutosReais = listaProdutosReais;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
