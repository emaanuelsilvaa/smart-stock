package BUSINESS;
import java.util.ArrayList;
import java.util.Date;

public class Venda {
	
	protected int id;
	protected float valor;
	protected Cliente cliente;
	protected ArrayList <ProdutoFinal> listProdutoFinal;
	protected Date data;
	
	public Venda() {
		
	};
	
	public Venda(int id, float valor, Cliente cliente, ArrayList<ProdutoFinal> listProdutoFinal, Date data) {
		this.id = id;
		this.valor = valor;
		this.cliente = cliente;
		this.listProdutoFinal = listProdutoFinal;
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


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public ArrayList<ProdutoFinal> getListProdutoFinal() {
		return listProdutoFinal;
	}


	public void setListProdutoFinal(ArrayList<ProdutoFinal> listProdutoFinal) {
		this.listProdutoFinal = listProdutoFinal;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	

}
