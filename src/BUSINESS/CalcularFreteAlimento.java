package BUSINESS;

import ENTITY.Frete;
import ENTITY.Venda;
import ENTITY.FreteAlimento;

public class CalcularFreteAlimento implements FreteStrategy {

	public CalcularFreteAlimento() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double calcularFrete (Frete frete, Venda venda) {
		((FreteAlimento) frete).setvalorPorUnidade(0.10);
		double valorFinal = 0;
		double multiplicador = ((FreteAlimento) frete).getValorPorUnidade();
		valorFinal += ((FreteAlimento) frete).getTaxaMinima();
		for (int idAlimento : venda.getListaProdutos().keySet()) {
			valorFinal += venda.getListaProdutos().get(idAlimento) * multiplicador;
		}
		return valorFinal;
	};

}
