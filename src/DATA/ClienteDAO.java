package DATA;

import java.util.ArrayList;
import java.lang.reflect.*;

import ENTITY.Cliente;

public class ClienteDAO implements IClienteDAO {
	protected ArrayList<Cliente> clientes;

	// Construtores
	public ClienteDAO() {
		this.clientes = new ArrayList<Cliente>();
	}
	
	public ClienteDAO(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	@Override
	public int inserir(Cliente cliente) {
		this.clientes.add(cliente);
		return 0;
	}

	@Override
	public int remover(int id) {
		Cliente aux = new Cliente();
		for (Cliente c: this.clientes) {
			if(c.getId() == id) {
				aux = c;
				break;
			}
		}
		this.clientes.remove(aux);
		return 0;
	}

	@Override
	public int alterar(int id, Cliente cliente) {
		for (Cliente c : this.clientes) {
			if (c.getId() == id) {
				c.setId(cliente.getId());
				c.setNome(cliente.getNome());
				c.setCpf(cliente.getCpf());
				c.setEndereço(cliente.getEndereço());
				c.setTelefone(cliente.getTelefone());
				break;
			}
		}
		return 0;
	}

	@Override
	public Cliente procuraPeloId(int id) {
		for (Cliente c : this.clientes) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Cliente> procuraTodos(){
		return this.clientes;
	}
}
