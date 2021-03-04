package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import DATA.EncomendaDAO;
import DATA.IEncomendaDAO;
import ENTITY.Encomenda;

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
		int newID = this.encomendaDAO.getNextID();
		this.encomendaDAO.inserir(new Encomenda(newID, valorDaEncomenda, idCliente, listaProdutos, data, dataEntrega));
		return 0;
	}

	@Override
	public boolean remover(int id) {
		return this.encomendaDAO.remover(id);
	}
	
	@Override
	public int cosumarEncomenda(Encomenda encomenda) {
		this.vendaService.realizarVenda(encomenda.getListaProdutos(), encomenda.getIdCliente());
		return 0;
	}
	@Override
	public int alterar(int id, Encomenda encomenda) {
		this.encomendaDAO.alterar(id, encomenda);
		return 0;
	}

	@Override
	public int inserir(Encomenda encomenda) {
		// TODO Auto-generated method stub
		return 0;
	}

}
