package ENTITY;

import java.util.Date;

public class MateriaPrimaReal{
    protected int id;
    protected int idExterno;
	protected float preço;
    protected  Date validade;
    protected float quantidade;
	protected int idFornecedor;
	
	public MateriaPrimaReal() {
		super();
	}

	public MateriaPrimaReal(int id, int idExterno, float preço, Date validade, float quantidade,
			int idFornecedor) {
		super();
		this.id = id;
		this.idExterno = idExterno;
		this.preço = preço;
		this.validade = validade;
		this.quantidade = quantidade;
		this.idFornecedor = idFornecedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(int idExterno) {
		this.idExterno = idExterno;
	}

	public float getPreço() {
		return preço;
	}

	public void setPreço(float preço) {
		this.preço = preço;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public int getFornecedor() {
		return idFornecedor;
	}

	public void setFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
}