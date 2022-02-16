package ENTITY;

import java.util.Date;
import java.util.HashMap;

public class Encomenda extends Venda {
	protected /*@ spec_public @*/ Date dataEntrega;
	
	//@ public invariant dataEntrega.compareTo(new Date()) > 0;
	
	public Encomenda() {
		// TODO Auto-generated constructor stub
	}
	
	/*@ assignable \everything;
	 @  ensures this.id == id;
	 @  ensures this.valor == valor;
	 @  ensures this.idCliente == idCliente;
	 @  ensures this.listaProdutos.equals(listaProdutos);
	 @  ensures this.data.compareTo(data) == 0;
	 @  ensures this.dataEntrega.compareTo(dataEntrega) == 0;
	@*/
	public Encomenda(int id, float valor, int idCliente, HashMap<Integer, Integer> listaProdutos, Date data,
			Date dataEntrega) {
		super(id, valor, idCliente, listaProdutos, data);
		this.dataEntrega = dataEntrega;
	}
	
	/*@ assignable \everything;
	 @  ensures this.valor == valor;
	 @  ensures this.idCliente == idCliente;
	 @  ensures this.listaProdutos.equals(listaProdutos);
	 @  ensures this.data.compareTo(data) == 0;
	 @  ensures this.dataEntrega.compareTo(dataEntrega) == 0;
	@*/
	public Encomenda(float valor, int idCliente, HashMap<Integer, Integer> listaProdutos, Date data,
			Date dataEntrega) {
		super(valor, idCliente, listaProdutos, data);
		this.dataEntrega = dataEntrega;
	}
	
	//@ ensures \result == dataEntrega;
	public /*@ pure @*/ Date getDataEntrega() {
		return dataEntrega;
	}
	
	/*@ assignable this.dataEntrega;
	 @  ensures this.dataEntrega.compareTo(dataEntrega) == 0;
	@*/
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

}
