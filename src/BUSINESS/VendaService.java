package BUSINESS;

import java.util.ArrayList;

public class VendaService implements IVendaService {
	
	EstoqueService estoqueService = new EstoqueService();
	Venda vendaAtual = new Venda();
	
	public VendaService (Venda serviçoAtual) {
		vendaAtual = serviçoAtual;
	};
	
	public boolean realizarVenda () {
		boolean temMateriaPrimaSuficiente = true;
		ArrayList <ProdutoFinal> listaDeProdutos = vendaAtual.getListProdutoFinal();
		
		for(int i=0; i<listaDeProdutos.size(); i++) {
			if(!estoqueService.verificaDisponibilidadeProduto(listaDeProdutos.get(i).getId(), listaDeProdutos.get(i).getUnidades())) {
				temMateriaPrimaSuficiente = false;
			};
			
		};
		
		if(temMateriaPrimaSuficiente) {
			
			for(int i=0; i<listaDeProdutos.size(); i++) {
				estoqueService.baixaProdutoFinal(listaDeProdutos.get(i).getId(), listaDeProdutos.get(i).getUnidades());
			}
			
			return true;
		}
		
		else {
			return false;
		}
		
	};
	
}
