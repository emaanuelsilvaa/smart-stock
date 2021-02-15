package BUSINESS;
import DATA.ClienteDAO;

public class ClienteService {
	protected ClienteDAO clientes;
	
	// Construtores
	public ClienteService() {
		
	}
	
	public ClienteService(ClienteDAO clientes) {
		super();
		this.clientes = clientes;
	}
	
	// Getters e Setters
	public ClienteDAO getCliente() {
		return clientes;
	}

	public void setCliente(ClienteDAO clientes) {
		this.clientes = clientes;
	}

	// Regras de Negócio	
	public void validarCadastroCliente(int idCliente) {
		this.clientes.validarCadastroCliente(idCliente);
	}
	
	public Cliente consultarClientePeloId(int idCliente) {
		return this.clientes.consultarClientePeloId(idCliente);
	}
	
	public void listarClientes() {
		this.clientes.listarClientes();
	}
}
