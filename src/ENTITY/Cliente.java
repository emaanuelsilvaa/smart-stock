package ENTITY;

public class Cliente {
	protected /*@ spec_public @*/ int id;
	protected /*@ spec_public @*/ String nome;
	protected /*@ spec_public @*/ String cpf;
	protected /*@ spec_public @*/ String endereco;
	protected /*@ spec_public @*/ String telefone;
	
	
	// Construtores
	public Cliente() {
		
	}
	
	
	public Cliente(int id, String nome, String cpf, String endereco, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Cliente(String nome, String cpf, String endereco, String telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	// Getters e Setters
	public /*@ pure @*/ int getId() {
		return id;
	}

	/*@ requires id > 0;
	 @ assignable this.id;
	 @ ensures this.id == id;
	 */
	public void setId(int id) {
		this.id = id;
	}

	public /*@ pure @*/ String getNome() {
		return nome;
	}

	
	/*@ requires nome != null && nome != "";
	 @ assignable this.nome;
	 @ ensures this.nome == nome;
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public /*@ pure @*/ String getCpf() {
		return cpf;
	}

	/*@ requires cpf != null && cpf != "";
	 @ assignable this.cpf;
	 @ ensures this.cpf == cpf;
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public /*@ pure @*/ String getEndereco() {
		return endereco;
	}

	/*@ requires endereco != null && endereco != "";
	 @ assignable this.endereco;
	 @ ensures this.endereco == endereco;
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public /*@ pure @*/ String getTelefone() {
		return telefone;
	}

	/*@ requires telefone != null && telefone != "";
	 @ assignable this.telefone;
	 @ ensures this.telefone == telefone;
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
