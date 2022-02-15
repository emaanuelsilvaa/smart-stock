package BUSINESS;

import java.util.Date;
import java.util.HashMap;

import ENTITY.ProdutoFinalReal;
import ENTITY.Venda;


public class AnaliseLucro implements IAnaliseLucro {
	
	private static /*@ spec_public @*/ IAnaliseLucro instance;
	protected /*@ spec_public @*/ IVendaService vendaService; 
	protected /*@ spec_public @*/ IProdutoFinalRealService produtoFinalRealService;
	protected /*@ spec_public @*/ IMateriaPrimaRealService materiaPrimaRealService;
		
	/*@ 
	 @ assignable vendaService, produtoFinalRealService, materiaPrimaRealService;
	 @ ensures vendaService != null;
	 @ ensures produtoFinalRealService != null && produtoFinalRealService instanceof ProdutoFinalRealService ;
	 @ ensures materiaPrimaRealService != null;
	 @*/
	private AnaliseLucro() {
		this.vendaService = VendaService.getInstance();
		this.produtoFinalRealService = ProdutoFinalRealService.getInstance();
		this.materiaPrimaRealService = MateriaPrimaRealService.getInstance();
	}
	
	
	/*@ 
	 @ assignable instance;
	 @ ensures instance != null;
	 @*/
	public static IAnaliseLucro getInstance() {
		if (instance == null) {
			instance = new AnaliseLucro();
		}
		return instance;
	}

	/** Calcula o lucro de vendas de um determinado periodo e Retorna o valor desse lucro  */
	/*@ also
	  @ requires dataInicio.compareTo(dataFim) > 0;	 
	  @ ensures \result ==  ( \forall int i; i < this.vendaService.procuraTodos().size() ;
	  @ 					this.vendaService.procuraTodos().get(i).getListaProdutosReais() != null ) &&
	  @						( \forall Iterator produtosReaisVendidos ; produtosReaisVendidos.hasNext() ;
	  @ 					produtoFinalRealService.procuraPeloId(produtosReaisVendidos.next()) != null );  
	  @ 
	  @*/
	public float analisarLucro(/*@ non_null @*/ Date dataInicio, /*@ non_null @*/Date dataFim) {
		float gastoMateriaPrima = 0;
		float ganhoVenda = 0;
		float precoMateriaInd = 0;
		float quantidadeMateriaInd = 0;
		
		
		for (Venda v : this.vendaService.procuraTodos() ) {
			// Checagem se a venda está no range solicitado
			if ((v.getData().after(dataInicio) && v.getData().before(dataFim)) || v.getData().equals(dataInicio) || v.getData().equals(dataFim)) {
				
				// Acrescentando valor desta venda
				ganhoVenda = ganhoVenda + v.getValor();	
								
				// Lista dos produtos reais vendidos nessa venda
				HashMap<Integer, Integer> produtosReaisVendidos = v.getListaProdutosReais();
				
				// Para cada produto desta venda
				for (Integer id_p : produtosReaisVendidos.keySet()) {
					
					ProdutoFinalReal produtoReal = produtoFinalRealService.procuraPeloId(id_p);

					// Recuperando receita <idMateriaPrimaReal, quantidade>
					HashMap<Integer, Float> receitaReal = produtoReal.getReceitaReal();
					
					// Para cada matéria prima da receita recuperada
					for (Integer id_m : receitaReal.keySet()) {
						precoMateriaInd = materiaPrimaRealService.procuraPeloId(id_m).getPreco();
						quantidadeMateriaInd = receitaReal.get(id_m);
//						
						gastoMateriaPrima = gastoMateriaPrima + (precoMateriaInd*quantidadeMateriaInd);
					}
				}
			}
		}

		float lucro = ganhoVenda - gastoMateriaPrima;
		
		return lucro;		
	}
}
