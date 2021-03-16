package BUSINESS;
import java.util.ArrayList;

import DATA.ClienteDAO;
import DATA.IClienteDAO;
import ENTITY.Cliente;
import UTIL.BusinessRuleException;

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
	public int inserir(Cliente cliente) throws BusinessRuleException {
		validarCadastro(cliente);
		if (this.clienteDAO.procuraPeloId(cliente.getId()) != null) {
			throw new BusinessRuleException("Tentou inserir um cliente já existente");
		}
		cliente.setId(this.pegaEIncremanetaId());
		return this.clienteDAO.inserir(cliente);
	}

	@Override
	public int remover(int id) throws BusinessRuleException {
		if (this.clienteDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir um cliente inexistente");
		}
		return this.clienteDAO.remover(id);
	}
	
	@Override
	public int alterar(int id, Cliente cliente) throws BusinessRuleException {
		validarCadastro(cliente);
		if (this.clienteDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		return this.clienteDAO.alterar(id, cliente);
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
	public int validarCadastro(Cliente cliente) throws BusinessRuleException {
		ArrayList<String> erros = new ArrayList<String>();
		if(cliente == null) {
			erros.add("Tentou inserir um Cliente nulo");
		}
		if(cliente.getNome() == "" || cliente.getNome() == null ) {
			erros.add("Tentou inserir um Cliente com nome nulo");
		}
		// Validação de CPF (Padrão: 11 dígitos.)
		if(cliente.getCpf().length() != 11) {
			erros.add("CPF deve possuir 11 dígitos");
		}
		if(cliente.getEndereço() == "" || cliente.getEndereço() == null) {
			erros.add("Tentou inserir um endereço nulo");
		}
		// Validação de Telefone (Padrão: DDD-8números ou DDD-9números. Exemplo: 89-33332222)
		if((cliente.getTelefone().length() != 11 && 
				cliente.getTelefone().length() != 12) ||
				cliente.getTelefone().toCharArray()[2] != '-') {
			erros.add("Telefone deve ter o padrão DDD-8números ou DDD-9números. Exemplo: 89-33332222");
		}
		if (erros.size() > 0) {
			throw new BusinessRuleException(erros);
		}
		
		return 0;
	}
	
	
	@Override
	public int pegaEIncremanetaId() {
		return this.clienteDAO.pegaEIncremanetaId();
	}
}
