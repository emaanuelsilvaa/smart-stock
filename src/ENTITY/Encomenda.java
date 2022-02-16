package ENTITY;

import java.util.Date;
import java.util.HashMap;

public class Encomenda extends Venda {
	protected /*@ spec_public @*/ Date dataEntrega;
	
	public Encomenda() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Encomenda(int id, float valor, int idCliente, HashMap<Integer, Integer> listaProdutos, Date data,
			Date dataEntrega) {
		super(id, valor, idCliente, listaProdutos, data);
		this.dataEntrega = dataEntrega;
	}
	
	public Encomenda(float valor, int idCliente, HashMap<Integer, Integer> listaProdutos, Date data,
			Date dataEntrega) {
		super(valor, idCliente, listaProdutos, data);
		this.dataEntrega = dataEntrega;
	}
	
	//@ ensures \result == this.dataEntrega;
	public /*@ pure @*/ Date getDataEntrega() {
		return dataEntrega;
	}
	
	/*@ assignable this.dataEntrega;
	 @  ensures this.dataEntrega == dataEntrega;
	@*/
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

}
