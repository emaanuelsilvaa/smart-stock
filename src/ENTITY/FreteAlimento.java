package ENTITY;

public class FreteAlimento extends Frete {
	
	protected double valorPorUnidade;
	protected double taxaMinima;

	public FreteAlimento() {
		// TODO Auto-generated constructor stub
		this.taxaMinima = 8.5;
	}

	public double getValorPorUnidade() {
		return valorPorUnidade;
	}

	public void setvalorPorUnidade(double valorPorUnidade) {
		this.valorPorUnidade = valorPorUnidade;
	}

	public double getTaxaMinima() {
		return taxaMinima;
	}

	public void setTaxaMinima(double taxaMinima) {
		this.taxaMinima = taxaMinima;
	}

}
