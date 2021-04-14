package BUSINESS;

import ENTITY.Frete;
import ENTITY.FreteRemedio;
import ENTITY.Venda;

public class CalcularFreteRemedio implements FreteStrategy {

	public CalcularFreteRemedio() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double calcularFrete (Frete frete, Venda venda) {
		((FreteRemedio) frete).setvalorPorUnidade(0.22);
		double valorFinal = 0;
		double multiplicador = ((FreteRemedio) frete).getValorPorUnidade();
		valorFinal += ((FreteRemedio) frete).getTaxaMinima();
		for (int idAlimento : venda.getListaProdutos().keySet()) {
			valorFinal += venda.getListaProdutos().get(idAlimento) * multiplicador;
		}
		return valorFinal;
	};

}
