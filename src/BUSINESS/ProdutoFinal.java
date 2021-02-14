package BUSINESS;

public class ProdutoFinal {
	protected int idProdutoFinal;
	protected String nome;
	protected float preço;
	protected MateriaPrima[] listaMateriaPrima;
	
	// Construtores
	public ProdutoFinal() {
		
	}

	public ProdutoFinal(int idProdutoFinal, String nome, float preço, MateriaPrima[] listaMateriaPrima) {
		super();
		this.idProdutoFinal = idProdutoFinal;
		this.nome = nome;
		this.preço = preço;
		this.listaMateriaPrima = listaMateriaPrima;
	}

	// Getters e Setters
	public int getIdProdutoFinal() {
		return idProdutoFinal;
	}

	public void setIdProdutoFinal(int idProdutoFinal) {
		this.idProdutoFinal = idProdutoFinal;
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

	public MateriaPrima[] getListaMateriaPrima() {
		return listaMateriaPrima;
	}

	public void setListaMateriaPrima(MateriaPrima[] listaMateriaPrima) {
		this.listaMateriaPrima = listaMateriaPrima;
	}

}
