package DATA;

import java.util.ArrayList;

import BUSINESS.ClienteService;
import ENTITY.Cliente;

public class ClienteDAO implements IClienteDAO {
	protected /*@ spec_public @*/ ArrayList<Cliente> clientes;
	protected /*@ spec_public @*/ int idSerial;
	
	// public invariant 0 < this.clientes.size();
	
	// public initially clientes.size() == 0;

	// Construtores
	
	/*@ assignable this.clientes, this.idSerial;
	 @  ensures clientes != null;
	@*/
	public ClienteDAO() {
		this.clientes = new ArrayList<Cliente>();
		this.idSerial = 1;
		
	}
	
	public ClienteDAO(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	/*@ also
	 @  ensures clientes != null;
	 @ ensures (\forall Cliente i; i != cliente ;clientes.contains(i)<==>\old( clientes.contains(i) ));
	@*/
	@Override
	public int inserir(Cliente cliente) {
		cliente.setId(this.pegaEIncremanetaId());
		this.clientes.add(cliente);
		return cliente.getId();
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
		return aux.getId();
	}

	/*@ also
	 @ requires id > 0 && cliente != null;
	 @  ensures clientes != null;
	 @ ensures clientes.size() == \old(clientes.size())-1;
   	 @ ensures (\exists int i; i<=0 && i < clientes.size() ; clientes.get(i).equals(cliente));
	@*/
	@Override
	public int alterar(int id, Cliente cliente) {
		for (Cliente c : this.clientes) {
			if (c.getId() == id) {
				c.setId(id);
				c.setNome(cliente.getNome());
				c.setCpf(cliente.getCpf());
				c.setEndereco(cliente.getEndereco());
				c.setTelefone(cliente.getTelefone());
				break;
			}
		}

		return id;
	}

	@Override
	public /*@ pure @*/ Cliente procuraPeloId(int id) {
		for (Cliente c : this.clientes) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public /*@ pure @*/ ArrayList<Cliente> procuraTodos(){
		return this.clientes;
	}
	
	@Override
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
}
