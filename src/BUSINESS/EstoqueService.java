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
	
	private EstoqueService() {
		super();
		this.materiaPrimaRealService = MateriaPrimaRealService.getInstance();
		this.materiaPrimaService =  MateriaPrimaService.getInstance();
		this.produtoFinalRealService =  ProdutoFinalRealService.getInstance();
		this.produtoFinalService =  ProdutoFinalService.getInstance();
	}
	
	public static IEstoqueService getInstance() {
		if(instance == null) {
			instance = new EstoqueService();
		}
		return instance;
	}
	
	@Override
	public int baixaProdutoFinal(int id, int quantidade) {
		produtoFinalRealService.alterarQuantidade(id, -1 * quantidade);
		return 0;
	}
	
	@Override
	public int reporProdutoFinal(int id, int quantidade) {
		this.produtoFinalRealService.alterarQuantidade(id, quantidade);
		return 0;
	}

	@Override
	public int baixaMateriaPrima(int id, float quantidade) throws BusinessRuleException {
		materiaPrimaRealService.alterarQuantidade(id, -1 * quantidade);
		return 0;
	}
	@Override
	public ArrayList<ProdutoFinalReal> procuraTodosProdutos(){
		return this.produtoFinalRealService.procuraTodos();
	}
	@Override
	public ArrayList<MateriaPrimaReal> procuraTodasMaterias(){
		return this.materiaPrimaRealService.procuraTodos();
	}

	@Override
	public int verificaDisponibilidadeProduto(int id, int quantidade) {
		int sum = 0;
		for (ProdutoFinalReal p: this.produtoFinalRealService.procuraPeloIdExterno(id)) {
			sum += p.getQuantidade();
		}
		return sum-quantidade;
	}

	@Override
	public float verificaDisponibilidadeMateriaPrima(int id, float quantidade) {
		float sum = 0;
		for(MateriaPrimaReal m: this.materiaPrimaRealService.procuraPeloIdExterno(id)) {
			sum += m.getQuantidade();
		}
		return sum-quantidade;
	}
}
