package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public final class EstoqueService implements IEstoqueService {
	
	IProdutoFinalService produtoFinalService;
	IMateriaPrimaService materiaPrimaService;
	IProdutoFinalRealService produtoFinalRealService;
	IMateriaPrimaRealService materiaPrimaRealService;
	private static IEstoqueService instance;
	
	/*@ assginable materiaPrimaRealService, MateriaPrimaService, ProdutoFinalRealService, produtoFinalService;
	 @ 
	 @  ensures materiaPrimaRealService != null;
	 @  ensures produtoFinalRealService != null;
	 @  ensures produtoFinalService != null;
	 @  ensures materiaPrimaService != null;
	@*/
	private EstoqueService() {
		super();
		this.materiaPrimaRealService = MateriaPrimaRealService.getInstance();
		this.materiaPrimaService =  MateriaPrimaService.getInstance();
		this.produtoFinalRealService =  ProdutoFinalRealService.getInstance();
		this.produtoFinalService =  ProdutoFinalService.getInstance();
	}
	
	/*@ requires instance == null; 
	 @  assignable instance;
	 @  ensures instance == \result;
	 @  also
	 @  requires instance != null;
	 @  assignable instance;
	 @  ensures instance == \old(instance);
	@*/
	public static IEstoqueService getInstance() {
		if(instance == null) {
			instance = new EstoqueService();
		}
		return instance;
	}
	
	@Override
	/*@ requires procuraTodosProdutos().get(id) != null;
	 @  ensures procuraTodosProdutos().get(id).getQuantidade() ==
	 @  	\old(procuraTodosProdutos().get(id).getQuantidade()) + (-1 * quantidade); 
	@*/
	public int baixaProdutoFinal(int id, int quantidade) {
		produtoFinalRealService.alterarQuantidade(id, -1 * quantidade);
		return 0;
	}
	
	/*@ requires procuraTodosProdutos().get(id) != null;
	 @  ensures procuraTodosProdutos().get(id).getQuantidade() ==
	 @  	\old(procuraTodosProdutos().get(id).getQuantidade()) + quantidade; 
	@*/
	@Override
	public int reporProdutoFinal(int id, int quantidade) {
		this.produtoFinalRealService.alterarQuantidade(id, quantidade);
		return 0;
	}

	@Override
	/*@ also
	 @  requires quantidade >= 0;
	 @  requires procuraTodasMaterias().get(id) != null;
	 @  ensures procuraTodasMaterias().get(id).getQuantidade() ==
	 @  	\old(procuraTodasMaterias().get(id).getQuantidade()) + (-1 * quantidade);
	@*/
	public int baixaMateriaPrima(int id, float quantidade) throws BusinessRuleException {
		materiaPrimaRealService.alterarQuantidade(id, -1 * quantidade);
		return 0;
	}
	
	@Override
	public /*@ pure @*/ ArrayList<ProdutoFinalReal> procuraTodosProdutos(){
		return this.produtoFinalRealService.procuraTodos();
	}
	
	@Override
	public /*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodasMaterias(){
		return this.materiaPrimaRealService.procuraTodos();
	}

	@Override
	
	
	/*@ public model int sump = 0;
	 @ 	requires (\exists ProdutoFinalReal p;
	 @ 				produtoFinalRealService.procuraPeloIdExterno(id).contains(p)
	 @ 			 );
	 @ 	ensures (\forall int i; i < produtoFinalRealService.procuraPeloIdExterno(id).size();
	 @ 				sump == \old(sump) += produtoFinalRealService.procuraPeloIdExterno(id).get(i)
	 @ 			);
	 @  ensures \result == (sump - quantidade);
	@*/
	public int verificaDisponibilidadeProduto(int id, int quantidade) {
		int sum = 0;
		for (ProdutoFinalReal p: this.produtoFinalRealService.procuraPeloIdExterno(id)) {
			sum += p.getQuantidade();
		}
		return sum-quantidade;
	}

	/*@ public model int summ = 0;
	 @ 	requires (\exists MateriaPrimaReal m;
	 @ 				materiaPrimaRealService.procuraPeloIdExterno(id).contains(m)
	 @ 			 );
	 @ 	ensures (\forall int i; i < materiaPrimaRealService.procuraPeloIdExterno(id).size();
	 @ 				summ == \old(summ) += materiaPrimaRealService.procuraPeloIdExterno(id).get(i)
	 @ 			);
	 @  ensures \result == (summ - quantidade);
	@*/
	@Override
	public float verificaDisponibilidadeMateriaPrima(int id, float quantidade) {
		float sum = 0;
		for(MateriaPrimaReal m: this.materiaPrimaRealService.procuraPeloIdExterno(id)) {
			sum += m.getQuantidade();
		}
		return sum-quantidade;
	}
}
