package BUSINESS;

import java.util.ArrayList;

import DATA.IProdutoFinalDAO;
import DATA.ProdutoFinalDAO;
import ENTITY.MateriaPrima;
import ENTITY.ProdutoFinal;
import UTIL.BusinessRuleException;

public final class ProdutoFinalService implements IProdutoFinalService {
	protected IProdutoFinalDAO produtoFinalDAO;
	protected IMateriaPrimaService materiaPrimaService;
	private static IProdutoFinalService instance;

	private ProdutoFinalService() {
		// TODO Auto-generated constructor stub
		this.produtoFinalDAO = new ProdutoFinalDAO();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
	}
	public static IProdutoFinalService getInstance() {
		if(instance == null) {
			instance = new ProdutoFinalService();
		}
		return instance;
	}
	
	@Override
	public int inserir(ProdutoFinal produto) throws BusinessRuleException {
		this.validarCadastro(produto);
		produto.setId(this.produtoFinalDAO.pegaEIncremanetaId());
		return produtoFinalDAO.inserir(produto);
	}

	@Override
	public int remover(int id) throws BusinessRuleException {
		if (produtoFinalDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir uma produto final inexistente");
		}
		return this.produtoFinalDAO.remover(id);
	}
	
	@Override
	public int alterar(int id, ProdutoFinal produto) throws BusinessRuleException {
		validarCadastro(produto);
		if (produtoFinalDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		return produtoFinalDAO.alterar(id, produto);
	}

	@Override
	public ProdutoFinal procuraPeloId(int id) {
		return produtoFinalDAO.procuraPeloId(id);
	}

	@Override
	public ArrayList<ProdutoFinal> procuraTodos() {
		return produtoFinalDAO.procuraTodos();
	}
	
	@Override
	public int pegaEIncremanetaId() {
		return this.produtoFinalDAO.pegaEIncremanetaId();
	}
	
	@Override
	public void validarCadastro(ProdutoFinal produtoFinal) throws BusinessRuleException{
		ArrayList<String> erros = new ArrayList<String>();
		if(produtoFinal == null) {
			erros.add("Tentou inserir uma MateriaPrima nula");
		}
		if(produtoFinal.getNome() == "" || produtoFinal.getNome() == null ) {
			erros.add("Tentou inserir uma ProdutoFinal com nome nulo");
		}
		if(produtoFinal.getQntMinima() < 0) {
			erros.add("Tentou inserir uma quantidade mínima negativa");
		}
		if(produtoFinal.getPreco() < 0) {
			erros.add("Tentou inserir um preço negativo");
		}
		if(produtoFinal.getReceita().isEmpty()) {
			erros.add("Tentou inserir uma receita vazia");
		} else {
			for(int i : produtoFinal.getReceita().keySet()) {
				if(this.materiaPrimaService.procuraPeloId(i) == null) {
					erros.add("MatériaPrima de ID " + i + "não cadastrada");
				}
			}
		}
		if (erros.size()>0) {
			throw new BusinessRuleException(erros);
		}
	}
	

}
