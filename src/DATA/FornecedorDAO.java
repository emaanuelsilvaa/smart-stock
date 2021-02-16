package DATA;

import BUSINESS.Fornecedor;
import BUSINESS.MateriaPrima;

public class FornecedorDAO {
	protected Fornecedor[] fornecedores;
	
	// Construtores
	public FornecedorDAO() {
		
	}

	public FornecedorDAO(Fornecedor[] fornecedores) {
		super();
		this.fornecedores = fornecedores;
	}

	// Getters e Setters
	public Fornecedor[] getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedor[] fornecedores) {
		this.fornecedores = fornecedores;
	}

	// Regras de Negócio
	public void validarCadastroFornecedor(int idFornecedor) {
		// Checagem se Fornecedor existe
		Fornecedor fornecedor  = consultarFornecedorPeloId(idFornecedor);
		
		if(fornecedor == null) {
			System.out.println(">> Fornecedor de ID=" + idFornecedor + " não cadastrado.");
		}
		else {	
			// Validação de CNPJ (Padrão: 14 dígitos.)
			if(fornecedor.getCnpj().length() != 14) {
				System.out.println(">> CPF do fornecedor " + fornecedor.getIdFornecedor() + " cadastrado incorretamente. "
						+ "Insira novamente!");
			}
			
			// Validação de Telefone (Padrão: DDD-8números ou DDD-9números. Exemplo: 89-33332222)
			if((fornecedor.getTelefone().length() != 11 && 
				fornecedor.getTelefone().length() != 12) ||
				fornecedor.getTelefone().toCharArray()[2] != '-') {
				System.out.println(">> Telefone do fornecedor " + fornecedor.getIdFornecedor() + " cadastrado incorretamente. "
						+ "Insira novamente!");
			}
			
			// Validação de Email
			if(fornecedor.getEmail().indexOf('@') == -1) {
				System.out.println(">> Email do fornecedor " + fornecedor.getIdFornecedor() + " cadastrado incorretamente. "
						+ "Insira novamente!");
			}
		}
		
		System.out.println("\n");
	}
	
	public Fornecedor consultarFornecedorPeloId(int idFornecedor) {
		for(Fornecedor f : this.fornecedores) {
			if(f.getIdFornecedor() == idFornecedor) {
				Fornecedor fornecedor = f;
				return fornecedor;
			}
		}
		return null;
	}
	
	public void listarFornecedores() {
		System.out.println(">>> Fornecedores cadastrados:");
		
		for(Fornecedor f : this.fornecedores) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Fornecedor ID=" + f.getIdFornecedor() + ": ");
			System.out.println("> Nome: " + f.getNome());
			System.out.println("> CNPJ: " + f.getCnpj());
			System.out.println("> Endereço: " + f.getEndereço());
			System.out.println("> Telefone: " + f.getTelefone());
			System.out.println("> Email: " + f.getEmail());
			System.out.println("> Lista de Produtos: ");
			int contador = 0;
			for(MateriaPrima m : f.getListaProdutos()) {
				contador += 1;
				System.out.println("\t" + contador + ". " + m.getNome());
			}
		}
		
		System.out.println("\n");
	}
}
