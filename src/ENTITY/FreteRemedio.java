package ENTITY;

public class FreteRemedio extends Frete{
	
	protected double valorPorUnidade;
	protected double taxaMinima;


	public FreteRemedio() {
		// TODO Auto-generated constructor stub
		this.taxaMinima = 13.50;

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
