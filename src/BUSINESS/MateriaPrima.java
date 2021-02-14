package BUSINESS;
import java.util.Date;

public class MateriaPrima {
	protected int idMateriaPrima;
	protected String nome;
	protected String tipo;
	protected float preço;
	protected Fornecedor fornecedor;
	protected boolean perecivel;
	protected float quantidade;
	protected String unMedida;
	protected Date validade;
	

	// Construtores
	public MateriaPrima() {
		
	}

	public MateriaPrima(int idMateriaPrima, String nome, String tipo, float preço, Fornecedor fornecedor,
			boolean perecivel, float quantidade, String unMedida, Date validade) {
		super();
		this.idMateriaPrima = idMateriaPrima;
		this.nome = nome;
		this.tipo = tipo;
		this.preço = preço;
		this.fornecedor = fornecedor;
		this.perecivel = perecivel;
		this.quantidade = quantidade;
		this.unMedida = unMedida;
		this.validade = validade;
	}

	// Getters e Setters
	public int getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdMateriaPrima(int idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
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

	public float getPreço() {
		return preço;
	}

	public void setPreço(float preço) {
		this.preço = preço;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public boolean isPerecivel() {
		return perecivel;
	}

	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnMedida() {
		return unMedida;
	}

	public void setUnMedida(String unMedida) {
		this.unMedida = unMedida;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

}
