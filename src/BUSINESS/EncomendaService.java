package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import DATA.EncomendaDAO;
import DATA.IEncomendaDAO;
import ENTITY.Cliente;
import ENTITY.Encomenda;
import UTIL.BusinessRuleException;

public final class EncomendaService implements IEncomendaService {

	protected IEncomendaDAO encomendaDAO;
	protected IVendaService vendaService;
	protected IProdutoFinalService produtoFinalService;
	protected IProdutoFinalRealService produtoFinalRealService;
	protected IMateriaPrimaService materiaPrimaService;
	protected IClienteService clienteService;
	private static IEncomendaService instance;

	/*@ assginable encomendaDAO, vendaService, produtoFinalRealService, produtoFinalService,
	 @ 		materiaPrimaService, clienteService;
	 @ 
	 @  ensures encomendaDAO != null;
	 @  ensures vendaService != null;
	 @  ensures produtoFinalRealService != null;
	 @  ensures produtoFinalService != null;
	 @  ensures materiaPrimaService != null;
	 @  ensures clienteService != null;
	@*/
	private EncomendaService() {
		this.encomendaDAO = new EncomendaDAO();
		this.vendaService = VendaService.getInstance();
		this.produtoFinalRealService = ProdutoFinalRealService.getInstance();
		this.produtoFinalService = ProdutoFinalService.getInstance();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
		this.clienteService = ClienteService.getInstance();
	}

	/*@ requires instance == null; 
	 @  assignable instance;
	 @  ensures instance == \result;
	 @  also
	 @  requires instance != null;
	 @  assignable instance;
	 @  ensures instance == \old(instance);
	@*/
	public static IEncomendaService getInstance() {
		if (instance == null) {
			instance = new EncomendaService();
		}
		return instance;
	}

	@Override
	public ArrayList<Encomenda> procuraTodos() {
		return this.encomendaDAO.procuraTodos();
	}

	@Override
	public Encomenda procuraPeloId(int id) {
		return this.encomendaDAO.procuraPeloId(id);
	}

	@Override
	/*@ also
	 @ 	requires !listaProdutos.isEmpty();
	 @ 	requires clienteService.procuraPeloId(idCliente) != null;
	 @ 	ensures encomendaDAO.procuraTodos().get(encomendaDAO.procuraTodos().size()-1).getId() == \result;
	@*/
	public int realizarEncomenda(HashMap<Integer, Integer> listaProdutos, int idCliente, Date dataEntrega) {
		Date data = new Date();
		float valorDaEncomenda = 0;
		for (int id : listaProdutos.keySet()) {
			int quantidade = listaProdutos.get(id);
			valorDaEncomenda += produtoFinalService.procuraPeloId(id).getPreco() * quantidade;
		}
		
		Encomenda newEncomenda = new Encomenda(valorDaEncomenda, idCliente, listaProdutos, data, dataEntrega);
		this.encomendaDAO.inserir(newEncomenda);
		return newEncomenda.getId();
	}

	@Override
	/*@ requires encomendaDAO.procuraPeloId(id) != null;
	 @  ensures id == \result;
	 @  also
	 @  requires encomendaDAO.procuraPeloId(id) == null;
	 @  signals_only BusinessRuleException;
	@*/
	public int remover(int id) throws BusinessRuleException {
		if (this.encomendaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir uma encomenda inexistente");
		}
		return this.encomendaDAO.remover(id);
	}
	
	@Override
	/*@ requires encomendaDAO.procuraPeloId(id) != null;
	 @  ensures id == \result;
	 @  also
	 @  requires encomendaDAO.procuraPeloId(id) == null;
	 @  signals_only BusinessRuleException;
	@*/
	public int consumarEncomenda(int id) throws BusinessRuleException {
		this.vendaService.realizarVenda(encomendaDAO.procuraPeloId(id).getListaProdutos(), encomendaDAO.procuraPeloId(id).getIdCliente());
		remover(id);
		return id;
	}
	@Override
	/*@ also
	 @  requires \same;
	 @  requires encomendaDAO.procuraPeloId(id) == null;
	 @  signals_only BusinessRuleException;
	 @  also
	 @  requires encomendaDAO.procuraPeloId(id) != null;
	 @  ensures id == \result;
	@*/
	public int alterar(int id, Encomenda encomenda) throws BusinessRuleException {
		validarCadastro(encomenda);
		if (this.encomendaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		this.encomendaDAO.alterar(id, encomenda);
		return this.encomendaDAO.alterar(id, encomenda);
	}

	@Override
	/*@ also
	 @  requires \same;
	 @  requires encomendaDAO.procuraTodos().contains(encomenda);
	 @  signals_only BusinessRuleException;
	 @  also
	 @  requires !encomendaDAO.procuraTodos().contains(encomenda);
	 @  ensures \result == encomenda.getId();
	@*/
	public int inserir(Encomenda encomenda) throws BusinessRuleException {
		validarCadastro(encomenda);
		if (this.encomendaDAO.procuraPeloId(encomenda.getId()) != null) {
			throw new BusinessRuleException("Tentou inserir uma encomenda já existente");
		}
		return this.encomendaDAO.inserir(encomenda);
	}
	
	@Override
	/*@ also
	 @  requires \same;
	 @  ensures (\exists Cliente c; clienteService.procuraTodos().contains(c);
	 @  			encomenda.getIdCliente() == c.getId()
	 @  		);
	 @  ensures (\exists MateriaPrima m; materiaPrimaService.procuraTodos().contains(m);
	 @  			encomenda.getListaProdutos().keySet().contains( m.getId() )
	 @  		);
	@*/
	public int validarCadastro(Encomenda encomenda) throws BusinessRuleException {
		ArrayList<String> erros = new ArrayList<String>();
		if(encomenda == null) {
			erros.add("Tentou inserir uma Encomenda nula");
		}
		if(encomenda.getValor() <= 0) {
			erros.add("Tentou inserir um valor de encomenda nulo ou negativo");
		}
		if(encomenda.getIdCliente() < 1) {
			erros.add("Tentou inserir um id de Cliente inválido");
		} else {
			int aux = -1;
			for (Cliente c: clienteService.procuraTodos()) {
			    if (c.getId() == encomenda.getIdCliente()) {
			        aux = 0;
			    }
			}
			if(aux == -1) {
				erros.add("Cliente de ID " + encomenda.getIdCliente() + "não cadastrado");
			}
		}
		if(encomenda.getListaProdutos().isEmpty()) {
			erros.add("Tentou vender uma lista de produtos vazia");
		} else {
			for(int i : encomenda.getListaProdutos().keySet()) {
				if(this.materiaPrimaService.procuraPeloId(i) == null) {
					erros.add("MatériaPrima de ID " + i + "não cadastrada");
				}
			}
		}
		if (erros.size() > 0) {
			throw new BusinessRuleException(erros);
		}
		return 0;
	}

}
