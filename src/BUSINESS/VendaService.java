package BUSINESS;

public class VendaService implements IVendaService {
	
	EstoqueService estoqueService = new EstoqueService();
	Venda vendaAtual = new Venda();
	
	public VendaService (Venda serviçoAtual) {
		vendaAtual = serviçoAtual;
	};
	
	public void realizarVenda () {
		
	};
	
}
