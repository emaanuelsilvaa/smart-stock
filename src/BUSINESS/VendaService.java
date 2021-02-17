package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import DATA.VendaDAO;

public class VendaService implements IVendaService {
	
	protected VendaDAO vendaDAO;
	protected EstoqueService estoqueService;
	
	public VendaService () {
		this.vendaDAO = new VendaDAO();
		this.estoqueService = new EstoqueService();
	};
	
	public boolean realizarVenda (ArrayList <ProdutoFinal> listaDeProdutos, Cliente comprador) {
		Date data = new Date();
		boolean temMateriaPrimaSuficiente = true;
		float valorDaVenda = 0;
		
		for(int i=0; i<listaDeProdutos.size(); i++) {
			if(!estoqueService.verificaDisponibilidadeProduto(listaDeProdutos.get(i).getId(), listaDeProdutos.get(i).getUnidades())) {
				temMateriaPrimaSuficiente = false;
			};
			valorDaVenda += listaDeProdutos.get(i).getPreço();
			
		};
		
		if(temMateriaPrimaSuficiente) {
			
			for(int i=0; i<listaDeProdutos.size(); i++) {
				estoqueService.baixaProdutoFinal(listaDeProdutos.get(i).getId(), listaDeProdutos.get(i).getUnidades());
			}
			
			//Gerando um ID automaticamente para a nova venda
			int newID = vendaDAO.getNextID();
			this.vendaDAO.inserir(new Venda(newID, valorDaVenda, comprador, listaDeProdutos, data));
			return true;
		}
		
		else {
			return false;
		}
		
	};
	
}
