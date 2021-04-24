package ENTITY;

public class EspecificidadeAlimento extends Especificidade {
	
	protected boolean buffetCadastrado; // informa se é para um buffet cadastrado
	protected float fatorDeDesconto; // caso seja, tem fator de desconto
	
	public EspecificidadeAlimento() {
		// TODO Auto-generated constructor stub
	}

	public boolean isBuffetCadastrado() {
		return buffetCadastrado;
	}

	public void setBuffetCadastrado(boolean buffetCadastrado) {
		this.buffetCadastrado = buffetCadastrado;
	}

	public float getFatorDeDesconto() {
		return fatorDeDesconto;
	}

	public void setFatorDeDesconto(float fatorDeDesconto) {
		this.fatorDeDesconto = fatorDeDesconto;
	}
	
}
