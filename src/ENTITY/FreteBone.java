package ENTITY;

public class FreteBone extends Frete{
	
	protected double valorPorUnidade;
	protected double taxaMinima;

	
	public FreteBone() {
		// TODO Auto-generated constructor stub
		this.taxaMinima = 10.50;
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
