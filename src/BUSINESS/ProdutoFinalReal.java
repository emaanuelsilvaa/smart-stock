package BUSINESS;

import java.util.Date;

public class ProdutoFinalReal implements Comparable<ProdutoFinalReal>{
	protected int id;
	protected int idExterno;
	protected Date validade;
	protected int quantidade;
	
	public ProdutoFinalReal() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoFinalReal(int id, int idExterno, Date validade, int quantidade) {
		super();
		this.id = id;
		this.idExterno = idExterno;
		this.validade = validade;
		this.quantidade = quantidade;
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

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int compareTo(ProdutoFinalReal o) {
		// TODO Auto-generated method stub
		if (this.getValidade() == null || o.getValidade() == null) {
			return 0;
		}
		return this.getValidade().compareTo(o.getValidade());
	}

}
