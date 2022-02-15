package BUSINESS;
import java.util.ArrayList;

import DATA.ClienteDAO;
import DATA.IClienteDAO;
import ENTITY.Cliente;
import UTIL.BusinessRuleException;

public final class ClienteService implements IClienteService {
	
	private static /*@ spec_public non_null @*/ IClienteService instance;
	protected /*@ spec_public non_null @*/ IClienteDAO clienteDAO;
	



	
	//@ public invariant clienteDAO != null;
	
	/* Construtores */
	/*@ 
	 @ assignable this.clienteDAO;
	 @ ensures this.clienteDAO != null && clienteDAO instanceof ClienteDAO;
	 @*/
	private ClienteService() {
		this.clienteDAO = new ClienteDAO();
	}
	
	/*@ 
	 @ assignable instance;
	 @ ensures \result == instance;
	 @ ensures instance instanceof ClienteService;
	 @*/
	public static IClienteService getInstance() {
		if (instance == null) {
			instance = new ClienteService();
		}  
		return instance;
	}
	
	/** Valida informações de cadastro e isere um cliente no sistema  */
  /*@ also
    @ requires cliente != null;
	@ assignable cliente, clienteDAO;
	@ ensures clienteDAO.procuraTodos().size() == \old(clienteDAO.procuraTodos().size())+1;
	@ also
	@ public exceptional_behavior
	@ 	requires this.clienteDAO.procuraPeloId(cliente.getId()) != null;
	@ 	assignable clienteDAO;
	@ 	signals_only BusinessRuleException;
	@ 	signals (BusinessRuleException e)
	@ 		this.clienteDAO.procuraPeloId(cliente.getId()) == null;
	@*/
	@Override
	public int inserir(Cliente cliente) throws BusinessRuleException {
		validarCadastro(cliente);
		if (this.clienteDAO.procuraPeloId(cliente.getId()) != null) {
			throw new BusinessRuleException("Tentou inserir um cliente jÃ¡ existente");
		}
		
		return this.clienteDAO.inserir(cliente);
	}
	
	/** Remove um cliente do sistema  */
  /*@ also 
    @ requires id > 0;
	@ assignable clienteDAO, id;
	@ ensures clienteDAO.procuraTodos().size() == \old(clienteDAO.procuraTodos().size())-1;
	@ also
	@ public exceptional_behavior
	@ 	requires this.clienteDAO.procuraPeloId(id) != null;
	@ 	assignable clienteDAO;
	@ 	signals_only BusinessRuleException;
	@ 	signals (BusinessRuleException e)
	@ 		this.clienteDAO.procuraPeloId(id) == null;
	@*/
	@Override
	public int remover(int id) throws BusinessRuleException {
		if (this.clienteDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir um cliente inexistente");
		}
		return this.clienteDAO.remover(id);
	}
	
	/** Altera informações cadastrais de um cliente especifico  */
  /*@ also 
    @requires id  > 0;
	@ assignable clienteDAO;
	@ ensures clienteDAO.procuraPeloId(id) == \old(clienteDAO.procuraTodos().size())-1;
	@ also
	@ public exceptional_behavior
	@ 	requires this.clienteDAO.procuraPeloId(id) != null;
	@   requires id < 0;
	@ 	assignable clienteDAO;
	@ 	signals_only BusinessRuleException;
	@ 	signals (BusinessRuleException e)
	@ 		this.clienteDAO.procuraPeloId(id) == null;
	@*/
	@Override
	public int alterar(int id, Cliente cliente) throws BusinessRuleException {
		validarCadastro(cliente);
		if (this.clienteDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		return this.clienteDAO.alterar(id, cliente);
	}

	/** Recupera informações de um cliente especifico */
	/*@ also 
	@requires id > 0;
	@ ensures clienteDAO.procuraPeloId(id) == \old(clienteDAO.procuraTodos().size())-1;
	@*/
	@Override
	public /*@ pure @*/ Cliente procuraPeloId(int id) {
		return this.clienteDAO.procuraPeloId(id);
	}

	/** Recupera Uma lista de todos os clientes  */
	@Override
	public /*@ pure @*/ ArrayList<Cliente> procuraTodos() {
		return this.clienteDAO.procuraTodos();
	}

	
	/** Valida informações cadastrais de um cliente */ 
  /*@ also
    @ requires cliente != null;
	@ also
	@ 	public exceptional_behavior
	@ 		requires this.clienteDAO.procuraPeloId(cliente.getId()) != null;
	@ 		signals_only BusinessRuleException;
	@ also
    @   public exceptional_behavior
    @   	requires cliente.getCpf().length() != 11;
    @   	signals_only BusinessRuleException;
    @ also
    @	public exceptional_behavior
    @	  	requires cliente.getNome() == "" || cliente.getNome() == null;
    @   	signals_only BusinessRuleException;
    @ also
    @   public exceptional_behavior
    @   	requires cliente.getEndereco() == "" || cliente.getEndereco() == null;
    @   	signals_only BusinessRuleException;
	@*/
	@Override
	public int validarCadastro(Cliente cliente) throws BusinessRuleException {
		ArrayList<String> erros = new ArrayList<String>();
		if(cliente == null) {
			erros.add("Tentou inserir um Cliente nulo");
		}
		if(cliente.getNome() == "" || cliente.getNome() == null ) {
			erros.add("Tentou inserir um Cliente com nome nulo");
		}
		// ValidaÃ§Ã£o de CPF (PadrÃ£o: 11 dÃ­gitos.)
		if(cliente.getCpf().length() != 11) {
			erros.add("CPF deve possuir 11 dÃ­gitos");
		}
		if(cliente.getEndereco() == "" || cliente.getEndereco() == null) {
			erros.add("Tentou inserir um endereÃ§o nulo");
		}
		// ValidaÃ§Ã£o de Telefone (PadrÃ£o: DDD-8nÃºmeros ou DDD-9nÃºmeros. Exemplo: 89-33332222)
		if((cliente.getTelefone().length() != 11 && 
				cliente.getTelefone().length() != 12) ||
				cliente.getTelefone().toCharArray()[2] != '-') {
			erros.add("Telefone deve ter o padrÃ£o DDD-8nÃºmeros ou DDD-9nÃºmeros. Exemplo: 89-33332222");
		}
		if (erros.size() > 0) {
			throw new BusinessRuleException(erros);
		}
		
		return 0;
	}
	
}
