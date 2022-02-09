package BUSINESS;

import java.util.Date;
import java.util.HashMap;

import ENTITY.ProdutoFinalReal;
import ENTITY.Venda;

public class AnaliseLucro implements IAnaliseLucro {
	private static IAnaliseLucro instance;
	protected IVendaService vendaService;
	protected IProdutoFinalRealService produtoFinalRealService;
	protected IMateriaPrimaRealService materiaPrimaRealService;
		
	private AnaliseLucro() {
		this.vendaService = VendaService.getInstance();
		this.produtoFinalRealService = ProdutoFinalRealService.getInstance();
		this.materiaPrimaRealService = MateriaPrimaRealService.getInstance();
	}
	
	public static IAnaliseLucro getInstance() {
		if (instance == null) {
			instance = new AnaliseLucro();
		}
		return instance;
	}
	
	public float analisarLucro(Date dataInicio, Date dataFim) {
		float gastoMateriaPrima = 0;
		float ganhoVenda = 0;
		float precoMateriaInd = 0;
		float quantidadeMateriaInd = 0;
		
		for (Venda v : this.vendaService.procuraTodos()) {
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
