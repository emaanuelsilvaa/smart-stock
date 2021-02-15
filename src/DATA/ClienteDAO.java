package DATA;

import BUSINESS.Cliente;

public class ClienteDAO {
	protected Cliente[] clientes;
	
	// Construtores
	public ClienteDAO() {
		
	}
	
	public ClienteDAO(Cliente[] clientes) {
		super();
		this.clientes = clientes;
	}
	
	// Getters e Setters
	public Cliente[] getClienteDAO() {
		return clientes;
	}

	public void setClienteDAO(Cliente[] clientes) {
		this.clientes = clientes;
	}

	// Regras de Negócio	
	public void validarCadastroCliente(int idCliente) {
		// Checagem se cliente existe
		Cliente cliente = consultarClientePeloId(idCliente);
		
		if(cliente == null) {
			System.out.println(">> Cliente de ID=" + idCliente + " não cadastrado.");
		}
		else {	
			// Validação de CPF (Padrão: 11 dígitos.)
			if(cliente.getCpf().length() != 11) {
				System.out.println(">> CPF do cliente " + cliente.getIdCliente() + " cadastrado incorretamente. "
						+ "Insira novamente!");
			}
			
			// Validação de Telefone (Padrão: DDD-8números ou DDD-9números. Exemplo: 89-33332222)
			if((cliente.getTelefone().length() != 11 && 
			   cliente.getTelefone().length() != 12) ||
			   cliente.getTelefone().toCharArray()[2] != '-') {
				System.out.println(">> Telefone do cliente " + cliente.getIdCliente() + " cadastrado incorretamente. "
						+ "Insira novamente!");
			}
		}
		
		System.out.println("\n");
	}
	
	public Cliente consultarClientePeloId(int idCliente) {
		for(Cliente c : this.clientes) {
			if(c.getIdCliente() == idCliente) {
				Cliente cliente = c;
				return cliente;
			}
		}
		return null;
	}
	
	public void listarClientes() {
		
		System.out.println(">>> Clientes cadastrados:");
		
		for(Cliente c : this.clientes) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Cliente ID=" + c.getIdCliente() + ": ");
			System.out.println("> Nome: " + c.getNome());
			System.out.println("> CPF: " + c.getCpf());
			System.out.println("> Endereço: " + c.getEndereço());
			System.out.println("> Telefone: " + c.getTelefone());
		}
		
		System.out.println("\n");
	}
}
