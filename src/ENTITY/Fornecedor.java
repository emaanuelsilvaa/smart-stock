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
	/*@ 
	 @ assignable id, nome, cnpj , endereco, telefone, email, listaProdutos;
	 @ ensures id!=null && nome!=null && cnpj !=null && endereco !=null && telefone !=null && email!=null && listaProdutos != null;
	 @*/
	public Fornecedor(/*@ non_null @*/int id, /*@ non_null @*/String nome, /*@ non_null @*/String cnpj, /*@ non_null @*/String endereco, /*@ non_null @*/String telefone, /*@ non_null @*/String email,
			/*@ non_null @*/ArrayList<MateriaPrima> listaProdutos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.listaProdutos = listaProdutos;
	}
	
	/*@ 
	 @ assignable  nome, cnpj , endereco, telefone, email, listaProdutos;
	 @ ensures  nome!=null && cnpj !=null && endereco !=null && telefone !=null && email!=null && listaProdutos != null;
	 @*/
	public Fornecedor(/*@ non_null @*/String nome,/*@ non_null @*/ String cnpj, /*@ non_null @*/String endereco, /*@ non_null @*/String telefone,/*@ non_null @*/ String email,
			/*@ non_null @*/ArrayList<MateriaPrima> listaProdutos) {
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

	public void setId(/*@ non_null @*/int id) {
		this.id= id;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getNome() {
		return nome;
	}

	public void setNome(/*@ non_null @*/String nome) {
		this.nome = nome;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getCnpj() {
		return cnpj;
	}
	/*@ 
	 @ assignable cnpj;
	 @ ensures cnpj !=null;
	 @*/
	public void setCnpj(/*@ non_null @*/String cnpj) {
		this.cnpj = cnpj;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getEndereco() {
		return endereco;
	}
	/*@ 
	 @ assignable endereco;
	 @ ensures endereco !=null;
	 @*/
	public void setEndereco(/*@ non_null @*/String endereco) {
		this.endereco = endereco;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getTelefone() {
		return telefone;
	}
	/*@ 
	 @ assignable telefone;
	 @ ensures telefone !=null;
	 @*/
	public void setTelefone(/*@ non_null @*/String telefone) {
		this.telefone = telefone;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public String getEmail() {
		return email;
	}
	/*@ 
	 @ assignable email;
	 @ ensures email !=null;
	 @*/
	public void setEmail(/*@ non_null @*/String email) {
		this.email = email;
	}
	/*@ 
	 @ assignable \nothing;
	 @*/
	public ArrayList<MateriaPrima> getListaProdutos() {
		return listaProdutos;
	}
	/*@ 
	 @ assignable listaProdutos;
	 @ ensures listaProdutos !=null;
	 @*/
	public void setListaProdutos(/*@ non_null @*/ArrayList<MateriaPrima> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

}
