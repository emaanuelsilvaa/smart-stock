package ENTITY;

import java.util.HashMap;

public class Alimento extends ProdutoFinal {
	
	float calorias;
	
	public Alimento() {
		// TODO Auto-generated constructor stub
	}

	public Alimento(int id, String nome, float preco, int qntMinima, HashMap<Integer, Float> receita) {
		super(id, nome, preco, qntMinima, receita);
		// TODO Auto-generated constructor stub
	}

	public Alimento(String nome, float preco, int qntMinima, HashMap<Integer, Float> receita, float calorias) {
		super(nome, preco, qntMinima, receita);
		// TODO Auto-generated constructor stub
		this.calorias = calorias;
	}

	public float getCalorias() {
		return calorias;
	}

	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}
	
}
