package ENTITY;

import java.util.HashMap;

public class Bone extends ProdutoFinal {
	String cor;
	String estilo;
	String fecho;

	public Bone() {
		// TODO Auto-generated constructor stub
	}

	public Bone(int id, String nome, float preco, int qntMinima, HashMap<Integer, Float> receita) {
		super(id, nome, preco, qntMinima, receita);
		// TODO Auto-generated constructor stub
	}

	public Bone(String nome, float preco, int qntMinima, HashMap<Integer, Float> receita, String cor, String estilo, String fecho) {
		super(nome, preco, qntMinima, receita);
		// TODO Auto-generated constructor stub
		this.cor = cor;
		this.estilo = estilo;
		this.fecho = fecho;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getFecho() {
		return fecho;
	}

	public void setFecho(String fecho) {
		this.fecho = fecho;
	}
	
}
