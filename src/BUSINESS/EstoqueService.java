package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public final class EstoqueService implements IEstoqueService {
	
	/*@ spec_public @*/ IProdutoFinalService produtoFinalService;
	/*@ spec_public @*/ IMateriaPrimaService materiaPrimaService;
	/*@ spec_public @*/ IProdutoFinalRealService produtoFinalRealService;
	/*@ spec_public @*/ IMateriaPrimaRealService materiaPrimaRealService;
	private /*@ spec_public @*/ static IEstoqueService instance;
	
	/*@ ensures materiaPrimaRealService != null;
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
	/*@ also
	 @  requires \same;
	 @  requires produtoFinalRealService.procuraPeloId(id) != null;
	 @  ensures produtoFinalRealService.procuraPeloId(id).getQuantidade() ==
	 @  	\old(produtoFinalRealService.procuraPeloId(id).getQuantidade()) - quantidade;
	@*/
	public int baixaProdutoFinal(int id, int quantidade) {
		produtoFinalRealService.alterarQuantidade(id, -1 * quantidade);
		return 0;
	}
	
	@Override
	/*@ also
	 @  requires \same;
	 @  requires produtoFinalRealService.procuraPeloId(id) != null;
	 @  ensures produtoFinalRealService.procuraPeloId(id).getQuantidade() ==
	 @  	\old(produtoFinalRealService.procuraPeloId(id).getQuantidade()) + quantidade;
	@*/
	public int reporProdutoFinal(int id, int quantidade) {
		this.produtoFinalRealService.alterarQuantidade(id, quantidade);
		return 0;
	}

	@Override
	/*@ also
	 @  requires materiaPrimaRealService.procuraPeloId(id) != null;
	 @  ensures materiaPrimaRealService.procuraPeloId(id).getQuantidade() ==
	 @  	\old(materiaPrimaRealService.procuraPeloId(id).getQuantidade()) - quantidade;
	@*/
	public int baixaMateriaPrima(int id, float quantidade) throws BusinessRuleException {
		materiaPrimaRealService.alterarQuantidade(id, -1 * quantidade);
		return 0;
	}
	
	@Override
	//@ also ensures \result.equals(produtoFinalRealService.procuraTodos());
	public /*@ pure @*/ ArrayList<ProdutoFinalReal> procuraTodosProdutos(){
		return this.produtoFinalRealService.procuraTodos();
	}
	
	@Override
	//@ also ensures \result.equals(materiaPrimaRealService.procuraTodos());
	public /*@ pure @*/ ArrayList<MateriaPrimaReal> procuraTodasMaterias(){
		return this.materiaPrimaRealService.procuraTodos();
	}

	@Override
	public /*@ pure @*/ int verificaDisponibilidadeProduto(int id, int quantidade) {
		int sum = 0;
		for (ProdutoFinalReal p: this.produtoFinalRealService.procuraPeloIdExterno(id)) {
			sum += p.getQuantidade();
		}
		return sum-quantidade;
	}

	@Override
	public /*@ pure @*/ float verificaDisponibilidadeMateriaPrima(int id, float quantidade) {
		float sum = 0;
		for(MateriaPrimaReal m: this.materiaPrimaRealService.procuraPeloIdExterno(id)) {
			sum += m.getQuantidade();
		}
		return sum-quantidade;
	}
}
