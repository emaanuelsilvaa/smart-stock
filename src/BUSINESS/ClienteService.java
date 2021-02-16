package BUSINESS;
import java.util.ArrayList;

import DATA.ClienteDAO;

public class ClienteService implements IClienteService {
	protected ClienteDAO clienteDAO;
	
	// Construtores
	public ClienteService() {
		this.clienteDAO = new ClienteDAO();
	}

	public ClienteService(ClienteDAO clienteDAO) {
		super();
		this.clienteDAO = clienteDAO;
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

}
