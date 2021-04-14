package BUSINESS;

import ENTITY.Frete;
import ENTITY.FreteRemedio;
import ENTITY.Venda;
import ENTITY.Remedio;


public class CalcularFreteRemedio implements FreteStrategy {
	protected IProdutoFinalService produtoFinalService;

	public CalcularFreteRemedio() {
		// TODO Auto-generated constructor stub
		this.produtoFinalService = ProdutoFinalService.getInstance();
	}
	
	@Override
	public double calcularFrete (Frete frete, Venda venda) {
		((FreteRemedio) frete).setvalorPorUnidade(0.22);
		double valorFinal = 0;
		double multiplicador = ((FreteRemedio) frete).getValorPorUnidade();
		Remedio remedioAtual = null;
		boolean temRemedioTarjado = false;
		valorFinal += ((FreteRemedio) frete).getTaxaMinima();
		for (int idRemedio : venda.getListaProdutos().keySet()) {
			remedioAtual = (Remedio) this.produtoFinalService.procuraPeloId(idRemedio);
			valorFinal += venda.getListaProdutos().get(idRemedio) * multiplicador;
			if(remedioAtual.getTarja().equalsIgnoreCase("preta")) {
				temRemedioTarjado = true;
			}
		}
		
		if(temRemedioTarjado) {
			valorFinal += (valorFinal*0.10);
		}
		return valorFinal;
	};

}
