package BUSINESS;

public class Fornecedor {
	protected int idFornecedor;
	protected String nome;
	protected String cnpj;
	protected String endereço;
	protected String telefone;
	protected String email;
	protected MateriaPrima[] listaProdutos;

	// Construtores
	public Fornecedor() {
		
	}
	
	public Fornecedor(int idFornecedor, String nome, String cnpj, String endereço, String telefone, String email,
			MateriaPrima[] listaProdutos) {
		super();
		this.idFornecedor = idFornecedor;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereço = endereço;
		this.telefone = telefone;
		this.email = email;
		this.listaProdutos = listaProdutos;
	}

	// Getters e Setters
	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MateriaPrima[] getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(MateriaPrima[] listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

}
