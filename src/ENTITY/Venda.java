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

	/*@
	 @ ensures \result == id;
	 */
	public /*@ pure @*/ int getId() {
		return id;
	}
	
	/*@ requires id > 0;
	 @ assignable this.id;
	 @ ensures this.id == id;
	 @*/
	public void setId(int id) {
		this.id = id;
	}

	/*@
	 @ ensures \result == this.valor;
	 @*/
	public /*@ pure @*/ float getValor() {
		return valor;
	}

	/*@ requires valor > 0;
	 @ assignable this.valor;
	 @ ensures this.valor == valor;
	 @*/
	public void setValor(float valor) {
		this.valor = valor;
	}

	/*@
	 @ ensures \result == this.idCliente;
	 @*/
	public /*@ pure @*/ int getIdCliente() {
		return idCliente;
	}

	/*@ requires idCliente > 0;
	 @ assignable this.idCliente;
	 @ ensures this.idCliente == idCliente;
	 @*/
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	/*@
	 @ ensures \result == this.listaProdutos;
	 @*/
	public /*@ pure @*/ HashMap<Integer, Integer> getListaProdutos() {
		return listaProdutos;
	}


	public void setListaProdutos(HashMap<Integer, Integer> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	/*@
	 @ ensures \result == this.listaProdutosReais;
	 @*/
	public /*@ pure @*/ HashMap<Integer, Integer> getListaProdutosReais() {
		return this.listaProdutosReais;
	}

	public void setListaProdutosReais(HashMap<Integer, Integer> listaProdutosReais) {
		this.listaProdutosReais = listaProdutosReais;
	}

	/*@
	 @ ensures \result == this.data;
	 @*/
	public /*@ pure @*/ Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
