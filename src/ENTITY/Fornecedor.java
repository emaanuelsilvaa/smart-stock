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
	/*@ 
	 @ assignable \nothing;
	 @*/
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
	/*@ 
	 @ assignable \nothing;
	 @*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getCnpj() {
		return cnpj;
	}
	/*@ 
	 @ ensures cnpj !=null;
	 @*/
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getEndereco() {
		return endereco;
	}
	/*@ 
	 @ ensures endereco !=null;
	 @*/
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getTelefone() {
		return telefone;
	}
	/*@ 
	 @ ensures telefone !=null;
	 @*/
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getEmail() {
		return email;
	}
	/*@ 
	 @ ensures email !=null;
	 @*/
	public void setEmail(String email) {
		this.email = email;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public ArrayList<MateriaPrima> getListaProdutos() {
		return listaProdutos;
	}
	
	/*@ 
	 @ ensures listaProdutos !=null;
	 @*/
	public void setListaProdutos(ArrayList<MateriaPrima> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

}
