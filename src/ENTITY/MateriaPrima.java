package ENTITY;

public class MateriaPrima {
	protected /*@ spec_public @*/ int id;
	protected /*@ spec_public @*/ String nome;
	protected /*@ spec_public @*/ String tipo;
	protected /*@ spec_public @*/ boolean perecivel;
	protected /*@ spec_public @*/ String unMedida;
	protected /*@ spec_public @*/ float qntMinima;
	

	// Construtores
	public MateriaPrima() {
		
	}

	public MateriaPrima(int id, String nome, String tipo,
			boolean perecivel, String unMedida, float qntMinima) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.perecivel = perecivel;
		this.unMedida = unMedida;
		this.qntMinima = qntMinima;
	}
	

	public MateriaPrima(String nome, String tipo, boolean perecivel, String unMedida, float qntMinima) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.perecivel = perecivel;
		this.unMedida = unMedida;
		this.qntMinima = qntMinima;
	}

	// Getters e Setters
	/*@ 
	 @ ensures \result == id;
	 */
	public /*@ pure */ int getId() {
		return id;
	}

	/*@ requires id > 0;
	 @ assignable this.id;
	 @ ensures this.id == id;
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*@ 
	 @ ensures \result == nome;
	 */
	public /*@ pure */ String getNome() {
		return nome;
	}

	/*@ requires nome != null;
	 @ assignable this.nome;
	 @ ensures this.nome == nome;
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/*@ 
	 @ ensures \result == tipo;
	 */
	public /*@ pure */ String getTipo() {
		return tipo;
	}

	/*@ requires tipo != null;
	 @ assignable this.tipo;
	 @ ensures this.tipo == tipo;
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	/*@ 
	 @ ensures \result == perecivel;
	 */
	public /*@ pure */ boolean getPerecivel() {
		return perecivel;
	}

	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}

	
	/*@ 
	 @ ensures \result == unMedida;
	 */
	public /*@ pure */ String getUnMedida() {
		return unMedida;
	}

	/*@ requires unMedida != null;
	 @ assignable this.unMedida;
	 @ ensures this.unMedida == unMedida;
	 */
	public void setUnMedida(String unMedida) {
		this.unMedida = unMedida;
	}

	/*@ 
	 @ ensures \result == qntMinima;
	 */
	public /*@ pure */ float getQntMinima() {
		return qntMinima;
	}
	

	/*@ requires qntMinima > 0;
	 @ assignable this.qntMinima;
	 @ ensures this.qntMinima == qntMinima;
	 */
	public void setQntMinima(float qntMinima) {
		this.qntMinima = qntMinima;
	}

}
