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
		int numeroDeBones = 0;
		for (int idBone : venda.getListaProdutos().keySet()) {
			valorFinal += venda.getListaProdutos().get(idBone) * multiplicador;
			numeroDeBones++;
		}
		
		if(numeroDeBones > 20 && numeroDeBones <= 100) {
			valorFinal -= valorFinal * (numeroDeBones / 100);
		}
		if(numeroDeBones > 100) {
			valorFinal = 0;
		}
		
		return valorFinal;
	};

}
