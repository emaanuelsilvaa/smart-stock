package ENTITY;

import java.util.HashMap;

public class ProdutoFinal {
	protected int id;
	protected String nome;
	protected float preco;
	protected int qntMinima;
	protected HashMap<Integer, Float> receita; // <idMateriaPrima, quantidade>

	// Construtores
	public ProdutoFinal() {
	}

	public ProdutoFinal(int id, String nome, float preco, int qntMinima, HashMap<Integer, Float> receita) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.qntMinima = qntMinima;
		this.receita = receita;
	}
	

	public ProdutoFinal(String nome, float preco, int qntMinima, HashMap<Integer, Float> receita) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.qntMinima = qntMinima;
		this.receita = receita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getQntMinima() {
		return qntMinima;
	}

	public void setQntMinima(int qntMinima) {
		this.qntMinima = qntMinima;
	}

	public HashMap<Integer, Float> getReceita() {
		return receita;
	}

	public void setReceita(HashMap<Integer, Float> receita) {
		this.receita = receita;
	}
	

}
