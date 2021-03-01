package BUSINESS;

import java.util.ArrayList;

import DATA.IProdutoFinalDAO;
import DATA.MateriaPrimaDAO;
import DATA.MateriaPrimaRealDAO;
import DATA.ProdutoFinalDAO;
import DATA.ProdutoFinalRealDAO;

public class EstoqueService {
	
	ProdutoFinalService produtoFinalService;
	MateriaPrimaService materiaPrimaService;
	ProdutoFinalRealService produtoFinalRealService;
	MateriaPrimaRealService materiaPrimaRealService;
	
	
	

	public EstoqueService() {
		super();
		this.materiaPrimaRealService = new MateriaPrimaRealService();
		this.materiaPrimaService = new MateriaPrimaService();
		this.produtoFinalRealService = new ProdutoFinalRealService();
		this.produtoFinalService = new ProdutoFinalService();
	}
	
	public EstoqueService(ProdutoFinalService produtoFinalService, MateriaPrimaService materiaPrimaService,
			ProdutoFinalRealService produtoFinalRealService, MateriaPrimaRealService materiaPrimaRealService) {
		super();
		this.produtoFinalService = produtoFinalService;
		this.materiaPrimaService = materiaPrimaService;
		this.produtoFinalRealService = produtoFinalRealService;
		this.materiaPrimaRealService = materiaPrimaRealService;
	}
	
	public int baixaProdutoFinal(int id, int quantidade) {
		produtoFinalRealService.alterarQuantidade(id, -1 * quantidade);
		return 0;
	}

	public int baixaMateriaPrima(int id, float quantidade) {
		materiaPrimaRealService.alterarQuantidade(id, -1 * quantidade);
		return 0;
	}
	public ArrayList<ProdutoFinalReal> procuraTodosProdutos(){
		return this.produtoFinalRealService.procuraTodos();
	}
	public ArrayList<MateriaPrimaReal> procuraTodasMaterias(){
		return this.materiaPrimaRealService.procuraTodos();
	}

	public boolean verificaDisponibilidadeProduto(int id, int quantidade) {
		int sum = 0;
		for (ProdutoFinalReal p: this.produtoFinalRealService.procuraPeloIdExterno(id)) {
			sum += p.getQuantidade();
		}
		if (sum >= quantidade) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean verificaDisponibilidadeMateriaPrima(int id, float quantidade) {
		float sum = 0;
		for(MateriaPrimaReal m: this.materiaPrimaRealService.procuraPeloIdExterno(id)) {
			sum += m.getQuantidade();
		}
		if (sum >= quantidade) {
			return true;
		} else {
			return false;
		}
	}
}
