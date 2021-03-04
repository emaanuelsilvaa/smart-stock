package BUSINESS;

import java.util.ArrayList;

import DATA.FornecedorDAO;
import DATA.IFornecedorDAO;
import ENTITY.Fornecedor;

public final class FornecedorService implements IFornecedorService {
	
	protected IFornecedorDAO fornecedorDAO;
	private static IFornecedorService instance;
	
	public FornecedorService() {
		this.fornecedorDAO = new FornecedorDAO();
	}
	public static IFornecedorService getInstance() {
		if(instance == null) {
			instance = new FornecedorService();
		}
		return instance;
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
	public int alterar(int id, Fornecedor fornecedor) {
		if (this.fornecedorDAO.procuraPeloId(id) != null) {
			this.fornecedorDAO.alterar(id, fornecedor);
			return 0;
		}
		return -1;
	}

	@Override
	public Fornecedor procuraPeloId(int id) {
		return this.fornecedorDAO.procuraPeloId(id);
	}

	@Override
	public ArrayList<Fornecedor> procuraTodos() {
		return this.fornecedorDAO.procuraTodos();
	}
	
	@Override
	public int validarCadastro(int id) {
		// Checagem se Fornecedor existe
		if(this.fornecedorDAO.procuraPeloId(id) == null) {
			return -1;
		}
		else {
			Fornecedor fornecedor = this.fornecedorDAO.procuraPeloId(id);
			// Validação de CNPJ (Padrão: 14 dígitos.)
			if(fornecedor.getCnpj().length() != 14) {
				return -1;
			}
			
			// Validação de Telefone (Padrão: DDD-8números ou DDD-9números. Exemplo: 89-33332222)
			if((fornecedor.getTelefone().length() != 11 && 
				fornecedor.getTelefone().length() != 12) ||
				fornecedor.getTelefone().toCharArray()[2] != '-') {
				return -1;
			}
			
			// Validação de Email
			if(fornecedor.getEmail().indexOf('@') == -1) {
				return -1;
			}
		}
		
		return 0;
	}
	
}
