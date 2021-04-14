package BUSINESS;

import ENTITY.Frete;
import ENTITY.FreteBone;
import ENTITY.Venda;

public class CalcularFreteBone implements FreteStrategy {

	public CalcularFreteBone() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double calcularFrete (Frete frete, Venda venda) {
		((FreteBone) frete).setvalorPorUnidade(0.15);
		double valorFinal = 0;
		double multiplicador = ((FreteBone) frete).getValorPorUnidade();
		valorFinal += ((FreteBone) frete).getTaxaMinima();
		for (int idAlimento : venda.getListaProdutos().keySet()) {
			valorFinal += venda.getListaProdutos().get(idAlimento) * multiplicador;
		}
		return valorFinal;
	};

}
