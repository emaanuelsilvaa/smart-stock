package ENTITY;

import java.util.Date;

public class MateriaPrimaReal{
    protected /*@ spec_public @*/ int id;
    protected /*@ spec_public @*/ int idExterno;
	protected /*@ spec_public @*/ float preco;
    protected /*@ spec_public @*/ Date validade;
    protected /*@ spec_public @*/ float quantidade;
	protected /*@ spec_public @*/ int idFornecedor;
	
	public MateriaPrimaReal() {
		super();
	}

	/*@ assignable \everything;
	 @  ensures this.id == id;
	 @	ensures this.idExterno == idExterno;
	 @ 	ensures this.preco == preco;
	 @	ensures this.validade == validade;
	 @	ensures this.quantidade == quantidade;
	 @	ensures this.idFornecedor == idFornecedor;
	@*/
	public MateriaPrimaReal(int id, int idExterno, float preco, Date validade, float quantidade,
			int idFornecedor) {
		super();
		this.id = id;
		this.idExterno = idExterno;
		this.preco= preco;
		this.validade = validade;
		this.quantidade = quantidade;
		this.idFornecedor = idFornecedor;
	}
	
	/*@ assignable \everything;
	 @	ensures this.idExterno == idExterno;
	 @ 	ensures this.preco == preco;
	 @	ensures this.validade == validade;
	 @	ensures this.quantidade == quantidade;
	 @	ensures this.idFornecedor == idFornecedor;
	@*/
	public MateriaPrimaReal(int idExterno, float preco, Date validade, float quantidade, int idFornecedor) {
		super();
		this.idExterno = idExterno;
		this.preco = preco;
		this.validade = validade;
		this.quantidade = quantidade;
		this.idFornecedor = idFornecedor;
	}

	//@ ensures this.id == \result;
	public /*@ pure @*/ int getId() {
		return id;
	}

	/*@ assignable this.id;
	 @  ensures this.id == id;
	@*/
	public void setId(int id) {
		this.id = id;
	}

	//@ ensures this.idExterno == \result;
	public /*@ pure @*/ int getIdExterno() {
		return idExterno;
	}

	/*@ assignable this.idExterno;
	 @  ensures this.idExterno == idExterno;
	@*/
	public void setIdExterno(int idExterno) {
		this.idExterno = idExterno;
	}

	//@ ensures this.preco == \result;
	public /*@ pure @*/ float getPreco() {
		return preco;
	}

	/*@ assignable this.preco;
	 @  ensures this.preco == preco;
	@*/
	public void setPreco(float preco) {
		this.preco = preco;
	}

	//@ ensures this.validade.compareTo(\result) == 0;
	public /*@ pure @*/ Date getValidade() {
		return validade;
	}

	/*@ assignable this.validade;
	 @  ensures this.validade.compareTo(validade) == 0;
	@*/
	public void setValidade(Date validade) {
		this.validade = validade;
	}

	//@ ensures this.quantidade == \result;
	public /*@ pure @*/ float getQuantidade() {
		return quantidade;
	}
	/*@ assignable this.quantidade;
	 @  ensures this.quantidade == quantidade;
	@*/
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	//@ ensures this.idFornecedor == \result;
	public /*@ pure @*/ int getFornecedor() {
		return idFornecedor;
	}

	/*@ assignable this.idFornecedor;
	 @  ensures this.idFornecedor == idFornecedor;
	@*/
	public void setFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
}