package BUSINESS;

import java.util.ArrayList;

import DATA.FornecedorDAO;
import DATA.IFornecedorDAO;
import ENTITY.Fornecedor;
import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;

public final class FornecedorService implements IFornecedorService {
	

	protected/*@ spec_public  @*/ IFornecedorDAO fornecedorDAO;
	private/*@ spec_public  @*/ static IFornecedorService instance;
	
	/*@ 
	  @ assignable fornecedorDAO; 
	  @ ensures fornecedorDAO != null; 
	  @*/
	public FornecedorService() {
		this.fornecedorDAO = new FornecedorDAO();
	}
	
	/*@ 
	  @ assignable instance;
	  @ ensures instance != null;
	  @*/
	public static IFornecedorService getInstance() {
		if(instance == null) {
			instance = new FornecedorService();
		}
		return instance;
	}

	
	/*@ also
	  @ requires fornecedor != null;
	  @ ensures fornecedorDAO != null; 
	  @*/
	@Override
	public int  inserir(Fornecedor fornecedor) throws BusinessRuleException {
		validarCadastro(fornecedor);
		if (this.fornecedorDAO.procuraPeloId(fornecedor.getId()) != null) {
			throw new BusinessRuleException("Tentou inserir um fornecedor já existente");
		}
		return this.fornecedorDAO.inserir(fornecedor);
	}

	
	/*@ also
	  @ requires  id > 0;
	  @ ensures \result == id; 
	  @*/
	@Override
	public int  remover(int id) throws BusinessRuleException {
		if (this.fornecedorDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir um fornecedor inexistente");
		}
		return this.fornecedorDAO.remover(id);
	}

	/*@ also
	  @ requires  id > 0;
	  @ requires fornecedor != null;
	  @ ensures \result == id; 
	  @*/
	@Override
	public int   alterar(int id, Fornecedor fornecedor) throws BusinessRuleException {
		validarCadastro(fornecedor);
		if (this.fornecedorDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		return this.fornecedorDAO.alterar(id, fornecedor);
	}

	
	/*@ also
	  @ ensures \result !=null;
	  @*/
	@Override
	public Fornecedor procuraPeloId(int id) {
		return this.fornecedorDAO.procuraPeloId(id);
	}

	
	/*@ also
	  @ ensures \result !=null;
	  @*/
	@Override
	public ArrayList<Fornecedor>  procuraTodos() {
		return this.fornecedorDAO.procuraTodos();
	}
	
	/*@  also
	  @ requires fornecedor != null;
	  @ ensures \result == 0 ; @*/
	@Override
	public int  validarCadastro(Fornecedor fornecedor) throws BusinessRuleException {
		ArrayList<String> erros = new ArrayList<String>();
		if(fornecedor == null) {
			erros.add("Tentou inserir um Fornecedor nulo");
		}
		if(fornecedor.getNome() == "" || fornecedor.getNome() == null ) {
			erros.add("Tentou inserir um Fornecedor com nome nulo");
		}
		// Validação de CNPJ (Padrão: 14 dígitos.)
		if(fornecedor.getCnpj().length() != 14) {
			erros.add("CNPJ deve possuir 14 dígitos");
		}
		if(fornecedor.getEndereco() == "" || fornecedor.getEndereco() == null) {
			erros.add("Tentou inserir um endereço nulo");
		}
		// Validação de Telefone (Padrão: DDD-8números ou DDD-9números. Exemplo: 89-33332222)
		if((fornecedor.getTelefone().length() != 11 && 
				fornecedor.getTelefone().length() != 12) ||
				fornecedor.getTelefone().toCharArray()[2] != '-') {
			erros.add("Telefone deve ter o padrão DDD-8números ou DDD-9números. Exemplo: 89-33332222");
		}
		if(fornecedor.getEmail().indexOf('@') == -1) {
			erros.add("Tentou inserir um email inválido");
		}
		if(fornecedor.getListaProdutos().isEmpty()) {
			erros.add("Tentou inserir uma lista de produtos vazia");
		} else {
			for(MateriaPrima mp : fornecedor.getListaProdutos()) {
				if(mp == null) {
					erros.add("Há MatériaPrima não cadastrada na lista de produtos");
					break;
				}
			}
		}
		if (erros.size() > 0) {
			throw new BusinessRuleException(erros);
		}
		
		return 0;
	}
	
}
