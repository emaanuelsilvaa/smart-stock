package BUSINESS;

import java.util.ArrayList;

import DATA.FornecedorDAO;
import DATA.IFornecedorDAO;

public class FornecedorService implements IFornecedorService {
	protected IFornecedorDAO fornecedorDAO;
	
	// Construtores
	public FornecedorService() {
		this.fornecedorDAO = new FornecedorDAO();
	}

	public FornecedorService(IFornecedorDAO fornecedorDAO) {
		super();
		this.fornecedorDAO = fornecedorDAO;
	}

	@Override
	public int inserir(Fornecedor fornecedor) {
		if (this.fornecedorDAO.procuraPeloId(fornecedor.getId()) != null) {
			return -1;
		}
		this.fornecedorDAO.inserir(fornecedor);
		return -1;
	}

	@Override
	public int remover(int id) {
		if (this.fornecedorDAO.procuraPeloId(id) != null) {
			this.fornecedorDAO.remover(id);
			return 0;
		}
		return -1;
	}

	@Override
	public int alterar() {
		// TODO things
		return 0;
	}

	@Override
	public Fornecedor procuraPeloId(int id) {
		return this.fornecedorDAO.procuraPeloId(id);
	}

	@Override
	public ArrayList<Fornecedor> procuraTodos() {
		return this.fornecedorDAO.procuraTodos();
	}
	
}
