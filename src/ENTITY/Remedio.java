package ENTITY;

import java.util.HashMap;

public class Remedio extends ProdutoFinal {
	
	String tarja;
	
	public Remedio() {
		// TODO Auto-generated constructor stub
	}

	public Remedio(int id, String nome, float preco, int qntMinima, HashMap<Integer, Float> receita) {
		super(id, nome, preco, qntMinima, receita);
		// TODO Auto-generated constructor stub
	}

	public Remedio(String nome, float preco, int qntMinima, HashMap<Integer, Float> receita, String tarja) {
		super(nome, preco, qntMinima, receita);
		// TODO Auto-generated constructor stub
		this.tarja = tarja;
	}

	public String getTarja() {
		return tarja;
	}

	public void setTarja(String tarja) {
		this.tarja = tarja;
	}
	

}
