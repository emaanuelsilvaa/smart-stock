package BUSINESS;

import java.util.ArrayList;
import java.util.Date;

import DATA.IProdutoFinalRealDAO;
import DATA.ProdutoFinalRealDAO;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public final class ProdutoFinalRealService implements IProdutoFinalRealService {

	protected IProdutoFinalRealDAO produtoFinalRealDAO;
	protected IProdutoFinalService produtoFinalService;
	protected IMateriaPrimaRealService materiaPrimaRealService;
	private static IProdutoFinalRealService instance;

	public ProdutoFinalRealService() {
		// TODO Auto-generated constructor stub
		this.produtoFinalService = ProdutoFinalService.getInstance();
		this.produtoFinalRealDAO = new ProdutoFinalRealDAO();
		this.materiaPrimaRealService = MateriaPrimaRealService.getInstance();
	}

	public static IProdutoFinalRealService getInstance() {
		if (instance == null) {
			instance = new ProdutoFinalRealService();
		}
		return instance;
	}

	@Override
	public int inserir(ProdutoFinalReal produto) throws BusinessRuleException {
		this.validarCadastro(produto);
		return produtoFinalRealDAO.inserir(produto);
	}

	@Override
	public int remover(int id) throws BusinessRuleException {
		if (produtoFinalRealDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir um produto final real inexistente");
		}
		return produtoFinalRealDAO.remover(id);
	}

	@Override
	public int alterar(int id, ProdutoFinalReal produto) throws BusinessRuleException {
		this.validarCadastro(produto);
		if (produtoFinalRealDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou alterar um produto final real inexistente");
		}
		return produtoFinalRealDAO.alterar(id, produto);
	}

	@Override
	public int alterarQuantidade(int id, int unidades) {
		return this.produtoFinalRealDAO.alterarQuantidade(id, unidades);
	}

	@Override
	public ProdutoFinalReal procuraPeloId(int id) {
		return produtoFinalRealDAO.procuraPeloId(id);
	}

	@Override
	public ArrayList<ProdutoFinalReal> procuraPeloIdExterno(int id) {
		return produtoFinalRealDAO.procuraPeloIdExterno(id);
	}

	@Override
	public ArrayList<ProdutoFinalReal> procuraTodos() {
		return produtoFinalRealDAO.procuraTodos();
	}

	@Override
	public void validarCadastro(ProdutoFinalReal produtoFinalReal) throws BusinessRuleException {
		ArrayList<String> erros = new ArrayList<String>();
		if (produtoFinalReal == null) {
			erros.add("Tentou inserir uma ProdutoFinalReal nulo");
		}
		if (this.produtoFinalService.procuraPeloId(produtoFinalReal.getIdExterno()) == null) {
			erros.add("Tentou inserir um tipo válido de produto final");
		}
		if (produtoFinalReal.getQuantidade() < 0) {
			erros.add("Tentou inserir uma quantidade mínima negativa");
		}
		if (produtoFinalReal.getValidade().before(new Date())) {
			erros.add("Tentou inserir uma data fora da validade");
		}
		if (produtoFinalReal.getReceitaReal().isEmpty()) {
			erros.add("Tentou inserir uma receita vazia");
		} else {
			for (int i : produtoFinalReal.getReceitaReal().keySet()) {
				if (this.materiaPrimaRealService.procuraPeloId(i) == null) {
					erros.add("MatériaPrimaReal de ID " + i + "não cadastrada");
				}
			}
		}
		if (erros.size() > 0) {
			throw new BusinessRuleException(erros);
		}
	}
}
