package ENTITY;

import java.util.Date;
import java.util.HashMap;

public class ProdutoFinalReal implements Comparable<ProdutoFinalReal>{
	protected int id;
	protected int idExterno;
	protected Date validade;
	protected int quantidade;
	protected HashMap<Integer, Float> receitaReal; // <idMateriaPrimaReal, quantidade>
	
	public ProdutoFinalReal() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoFinalReal(int id, int idExterno, Date validade, int quantidade, HashMap<Integer, Float> receitaReal) {
		super();
		this.id = id;
		this.idExterno = idExterno;
		this.validade = validade;
		this.quantidade = quantidade;
		this.receitaReal= receitaReal;
	}
	
	public ProdutoFinalReal(int idExterno, Date validade, int quantidade, HashMap<Integer, Float> receitaReal) {
		super();
		this.idExterno = idExterno;
		this.validade = validade;
		this.quantidade = quantidade;
		this.receitaReal= receitaReal;
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

	public HashMap<Integer, Float> getReceitaReal() {
		return receitaReal;
	}

	public void setReceitaReal(HashMap<Integer, Float> receitaReal) {
		this.receitaReal = receitaReal;
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
