package BUSINESS;

import DATA.FornecedorDAO;

public class FornecedorService {
	protected FornecedorDAO fornecedores;
	
	// Construtores
	public FornecedorService() {
		
	}

	public FornecedorService(FornecedorDAO fornecedores) {
		super();
		this.fornecedores = fornecedores;
	}

	// Getters e Setters
	public FornecedorDAO getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(FornecedorDAO fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	// Regras de Negócio
	public void validarCadastroFornecedor(int idFornecedor) {
		this.fornecedores.validarCadastroFornecedor(idFornecedor);
	}
	
	public Fornecedor consultarFornecedorPeloId(int idFornecedor) {
		return this.fornecedores.consultarFornecedorPeloId(idFornecedor);
	}
	
	public void listarFornecedores() {
		this.fornecedores.listarFornecedores();
	}
	
}
