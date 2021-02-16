package BUSINESS;

import java.util.ArrayList;

public class ProdutoFinal {
	protected int id;
	protected String nome;
	protected float preço;
	protected ArrayList<MateriaPrima> listaMateriaPrima;
	protected int unidades;

	// Construtores
	public ProdutoFinal() {

	}

	public ProdutoFinal(int idProdutoFinal, String nome, float preço, ArrayList<MateriaPrima> listaMateriaPrima, int unidades) {
		super();
		this.id = idProdutoFinal;
		this.nome = nome;
		this.preço = preço;
		this.listaMateriaPrima = listaMateriaPrima;
		this.unidades = unidades;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public void setId(int idProdutoFinal) {
		this.id = idProdutoFinal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreço() {
		return preço;
	}

	public void setPreço(float preço) {
		this.preço = preço;
	}

	public ArrayList<MateriaPrima> getListaMateriaPrima() {
		return listaMateriaPrima;
	}

	public void setListaMateriaPrima(ArrayList<MateriaPrima> listaMateriaPrima) {
		this.listaMateriaPrima = listaMateriaPrima;
	}

}
