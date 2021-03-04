package BUSINESS;
import java.util.ArrayList;

import DATA.ClienteDAO;
import DATA.IClienteDAO;
import ENTITY.Cliente;

public final class ClienteService implements IClienteService {
	
	private static IClienteService instance;
	protected IClienteDAO clienteDAO;
	
	// Construtores
	private ClienteService() {
		this.clienteDAO = new ClienteDAO();
	}

	public static IClienteService getInstance() {
		if (instance == null) {
			instance = new ClienteService();
		}  
		return instance;
	}

	@Override
	public int inserir(Cliente cliente) {
		if (this.clienteDAO.procuraPeloId(cliente.getId()) != null) {
			return -1;
		}
		this.clienteDAO.inserir(cliente);
		return -1;
	}

	@Override
	public int remover(int id) {
		if (this.clienteDAO.procuraPeloId(id) != null) {
			this.clienteDAO.remover(id);
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
	public Cliente procuraPeloId(int id) {
		return this.clienteDAO.procuraPeloId(id);
	}

	@Override
	public ArrayList<Cliente> procuraTodos() {
		return this.clienteDAO.procuraTodos();
	}

	@Override
	public int validarCadastro(int id) {
		// Checagem se cliente existe
		if(this.clienteDAO.procuraPeloId(id) == null) {
			return -1;
		}
		else {
			Cliente cliente = this.clienteDAO.procuraPeloId(id);
			// Validação de CPF (Padrão: 11 dígitos.)
			if(cliente.getCpf().length() != 11) {
				return -1;
			}
			
			// Validação de Telefone (Padrão: DDD-8números ou DDD-9números. Exemplo: 89-33332222)
			if((cliente.getTelefone().length() != 11 && 
			   cliente.getTelefone().length() != 12) ||
			   cliente.getTelefone().toCharArray()[2] != '-') {
				return -1;
			}
		}
		
		return 0;
	}
}
