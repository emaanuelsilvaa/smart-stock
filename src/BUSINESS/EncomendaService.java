package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import DATA.EncomendaDAO;
import DATA.IEncomendaDAO;
import ENTITY.Encomenda;
import UTIL.BusinessRuleException;

public final class EncomendaService implements IEncomendaService {

	protected IEncomendaDAO encomendaDAO;
	protected IVendaService vendaService;
	protected IProdutoFinalService produtoFinalService;
	protected IProdutoFinalRealService produtoFinalRealService;
	private static IEncomendaService instance;

	private EncomendaService() {
		// TODO Auto-generated constructor stub
		this.encomendaDAO = new EncomendaDAO();
		this.vendaService = VendaService.getInstance();
		this.produtoFinalRealService = ProdutoFinalRealService.getInstance();
		this.produtoFinalService = ProdutoFinalService.getInstance();
	}

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
	public int remover(int id) throws BusinessRuleException {
		if (this.encomendaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("Tentou excluir uma encomenda inexistente");
		}
		return this.encomendaDAO.remover(id);
	}
	
	@Override
	public int consumarEncomenda(int id) throws BusinessRuleException {
		this.vendaService.realizarVenda(encomendaDAO.procuraPeloId(id).getListaProdutos(), encomendaDAO.procuraPeloId(id).getIdCliente());
		return id;
	}
	@Override
	public int alterar(int id, Encomenda encomenda) throws BusinessRuleException {
		validarCadastro(encomenda);
		if (this.encomendaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		this.encomendaDAO.alterar(id, encomenda);
		return this.encomendaDAO.alterar(id, encomenda);
	}

	@Override
	public int inserir(Encomenda encomenda) throws BusinessRuleException {
		validarCadastro(encomenda);
		if (this.encomendaDAO.procuraPeloId(encomenda.getId()) != null) {
			throw new BusinessRuleException("Tentou inserir uma encomenda já existente");
		}
		return this.encomendaDAO.inserir(encomenda);
	}
	
	@Override
	public int validarCadastro(Encomenda encomenda) throws BusinessRuleException {
		ArrayList<String> erros = new ArrayList<String>();
		if(encomenda == null) {
			erros.add("Tentou inserir uma Encomenda nulo");
		}
		if (erros.size() > 0) {
			throw new BusinessRuleException(erros);
		}
		
		return 0;
	}

}
