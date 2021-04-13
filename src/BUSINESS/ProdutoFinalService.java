package BUSINESS;

import java.util.ArrayList;

import DATA.IProdutoFinalDAO;
import DATA.ProdutoFinalDAO;
import ENTITY.Alimento;
import ENTITY.Bone;
import ENTITY.MateriaPrima;
import ENTITY.ProdutoFinal;
import ENTITY.Remedio;
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
	public void validarCadastro(ProdutoFinal produtoFinal) throws BusinessRuleException{
		ValidarStrategy validar = null;
		if(produtoFinal instanceof Alimento) {
			validar = new ValidarAlimento();
		}
		if(produtoFinal instanceof Bone) {
			validar = new ValidarBone();
		}
		if(produtoFinal instanceof Remedio) {
			validar = new ValidarRemedio();
		}
		if (validar == null) {
			throw new BusinessRuleException("Tipo de Produto Final não detectado");
		}
		validar.validarEspecificidades(produtoFinal);
	}
}
