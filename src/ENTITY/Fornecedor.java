package ENTITY;

import java.util.ArrayList;

public class Fornecedor {
	protected int id;
	protected String nome;
	protected String cnpj;
	protected String endereco;
	protected String telefone;
	protected String email;
	protected ArrayList<MateriaPrima> listaProdutos;

	// Construtores
	public Fornecedor() {
		
	}
	
	public Fornecedor(int id, String nome, String cnpj, String endereco, String telefone, String email,
			ArrayList<MateriaPrima> listaProdutos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.listaProdutos = listaProdutos;
	}
	
	public Fornecedor(String nome, String cnpj, String endereco, String telefone, String email,
			ArrayList<MateriaPrima> listaProdutos) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.listaProdutos = listaProdutos;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public ArrayList<MateriaPrima> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(ArrayList<MateriaPrima> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

}
