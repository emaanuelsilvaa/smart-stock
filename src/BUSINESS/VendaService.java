package BUSINESS;

public class VendaService implements IVendaService {
	
	EstoqueService estoqueService = new EstoqueService();
	ProdutoFinal [] listaDeProdutos  = {};
	
	public VendaService (ProdutoFinal [] produtosSelecionados) {
		listaDeProdutos = produtosSelecionados;
	};
	
	public void realizarVenda () {
		
	};
	
}
