package BUSINESS;

import java.util.ArrayList;

import DATA.IMateriaPrimaDAO;
import DATA.MateriaPrimaDAO;
import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;

public final class MateriaPrimaService implements IMateriaPrimaService {
	
	protected IMateriaPrimaDAO materiaPrimaDAO;
	private static IMateriaPrimaService instance;
	// Construtores
	private MateriaPrimaService() {
		this.materiaPrimaDAO = new MateriaPrimaDAO();
	}

	public static IMateriaPrimaService getInstance() {
		if(instance == null) {
			instance = new MateriaPrimaService();
		}
		return instance;
	}
	
	@Override
	public int inserir(MateriaPrima materiaPrima) throws BusinessRuleException {
		validarCadastro(materiaPrima);
		materiaPrima.setId(this.pegaEIncremanetaId());
		return this.materiaPrimaDAO.inserir(materiaPrima);
	}

	@Override
	public void remover(int id) throws BusinessRuleException {
		if(this.materiaPrimaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir uma matéria-prima inexistente");
		}
		this.materiaPrimaDAO.remover(id);	
	}
	
	@Override
	public int alterar(int id, MateriaPrima materiaPrima) throws BusinessRuleException {
		validarCadastro(materiaPrima);
		if (this.materiaPrimaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		return this.materiaPrimaDAO.alterar(id, materiaPrima);
	}

	@Override
	public MateriaPrima procuraPeloId(int id) {
		return this.materiaPrimaDAO.procuraPeloId(id);
	}

	@Override
	public ArrayList<MateriaPrima> procuraTodos() {
		return this.materiaPrimaDAO.procuraTodos();
	}
	
	@Override
	public void validarCadastro(MateriaPrima materiaPrima) throws BusinessRuleException{
		ArrayList<String> erros = new ArrayList<String>();
		if(materiaPrima == null) {
			erros.add("Tentou inserir uma MateriaPrima nula");
		}
		if(materiaPrima.getNome() == "" || materiaPrima.getNome() == null ) {
			erros.add("Tentou inserir uma MateriaPrima com nome nulo");
		}
		if(materiaPrima.getQntMinima() < 0) {
			erros.add("Tentou inserir uma quantidade mínima negativa");
		}
		if(materiaPrima.getTipo() == "" ||  materiaPrima.getTipo() == null) {
			erros.add("Tentou inserir uma tipo nulo");
		}
		if(materiaPrima.getUnMedida() == "" || materiaPrima.getUnMedida() == null) {
			erros.add("Tentou inserir uma quantidade de medida nula");
		}
		if (erros.size()>0) {
			throw new BusinessRuleException(erros);
		}
	}
	
	public int pegaEIncremanetaId() {
		return this.materiaPrimaDAO.pegaEIncremanetaId();
	}
}
