package ENTITY;

public class MateriaPrima {
	protected int id;
	protected String nome;
	protected String tipo;
	protected boolean perecivel;
	protected String unMedida;
	

	// Construtores
	public MateriaPrima() {
		
	}

	public MateriaPrima(int id, String nome, String tipo,
			boolean perecivel, String unMedida) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.perecivel = perecivel;
		this.unMedida = unMedida;
	}

	// Getters e Setters
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public boolean isPerecivel() {
		return perecivel;
	}

	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}

	public String getUnMedida() {
		return unMedida;
	}

	public void setUnMedida(String unMedida) {
		this.unMedida = unMedida;
	}

}
