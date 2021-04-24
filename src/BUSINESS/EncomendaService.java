package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import DATA.EncomendaDAO;
import DATA.IEncomendaDAO;
import ENTITY.Cliente;
import ENTITY.Encomenda;
import ENTITY.Especificidade;
import ENTITY.Frete;
import ENTITY.FreteAlimento;
import ENTITY.FreteBone;
import ENTITY.FreteRemedio;
import UTIL.BusinessRuleException;

public final class EncomendaService implements IEncomendaService {

	protected IEncomendaDAO encomendaDAO;
	protected IVendaService vendaService;
	protected IProdutoFinalService produtoFinalService;
	protected IProdutoFinalRealService produtoFinalRealService;
	protected IMateriaPrimaService materiaPrimaService;
	protected IClienteService clienteService;
	private static IEncomendaService instance;
	protected int typeInstance;
	private static EspecificidadeVendaStrategy especificidadeEncomenda;

	private EncomendaService() {
		// TODO Auto-generated constructor stub
		this.encomendaDAO = new EncomendaDAO();
		this.vendaService = VendaService.getInstance();
		this.produtoFinalRealService = ProdutoFinalRealService.getInstance();
		this.produtoFinalService = ProdutoFinalService.getInstance();
		this.materiaPrimaService = MateriaPrimaService.getInstance();
		this.clienteService = ClienteService.getInstance();
		this.typeInstance = 1;
	}

	public int getTypeInstance() {
		return typeInstance;
	}

	public void setTypeInstance(int typeInstance) {
		this.typeInstance = typeInstance;
	}



	public static IEncomendaService getInstance() {
		if (instance == null) {
			instance = new EncomendaService();
		}
		return instance;
	}

	public static IEncomendaService getInstance(EspecificidadeVendaStrategy especificidadeEncomendaStrategy) {
		if (instance == null) {
			instance = new EncomendaService();
		}
		especificidadeEncomenda = especificidadeEncomendaStrategy;
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
	public int realizarEncomenda(HashMap<Integer, Integer> listaProdutos, int idCliente, Date dataEntrega, Especificidade especificidade) throws BusinessRuleException {
		Date data = new Date();
		float valorDaEncomenda = 0;
		for (int id : listaProdutos.keySet()) {
			int quantidade = listaProdutos.get(id);
			valorDaEncomenda += produtoFinalService.procuraPeloId(id).getPreco() * quantidade;
		}
		
		Encomenda newEncomenda = new Encomenda(valorDaEncomenda, idCliente, listaProdutos, data, dataEntrega);
		
		FreteStrategy freteEncomenda = null;
		Frete tipoDeFrete = null;
		switch (this.typeInstance) {
		case 1:
			freteEncomenda = new CalcularFreteAlimento();
			tipoDeFrete = new FreteAlimento();
			break;
		case 2:
			freteEncomenda = new CalcularFreteBone();
			tipoDeFrete = new FreteBone();
			break;
		case 3:
			freteEncomenda = new CalcularFreteRemedio();
			tipoDeFrete = new FreteRemedio();
			break;
		default:
			throw new BusinessRuleException("Argumento de Framework inválido");
		}
		double frete = freteEncomenda.calcularFrete(tipoDeFrete, newEncomenda);
		newEncomenda.setFrete(frete);
		
		if (especificidadeEncomenda == null) {
			throw new BusinessRuleException("Tipo de Encomenda não detectado");
		}
		ArrayList<String> errosEspecificidade = especificidadeEncomenda.validarEspecificidades(especificidade, newEncomenda);
		
		validarCadastro(newEncomenda, errosEspecificidade);
		
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
		this.vendaService.realizarVenda(encomendaDAO.procuraPeloId(id).getListaProdutos(), encomendaDAO.procuraPeloId(id).getIdCliente(), encomendaDAO.procuraPeloId(id).getEspecificidade());
		remover(id);
		return id;
	}
	@Override
	public int alterar(int id, Encomenda encomenda, Especificidade especificidade) throws BusinessRuleException {
		ArrayList<String> errosEspecificidade = especificidadeEncomenda.validarEspecificidades(especificidade, encomenda);
		validarCadastro(encomenda, errosEspecificidade);
		if (this.encomendaDAO.procuraPeloId(id) == null) {
			throw new BusinessRuleException("ID inexistente");
		}
		this.encomendaDAO.alterar(id, encomenda);
		return this.encomendaDAO.alterar(id, encomenda);
	}

	@Override
	public int inserir(Encomenda encomenda) throws BusinessRuleException {
		if (this.encomendaDAO.procuraPeloId(encomenda.getId()) != null) {
			throw new BusinessRuleException("Tentou inserir uma encomenda já existente");
		}
		return this.encomendaDAO.inserir(encomenda);
	}
	
	@Override
	public int validarCadastro(Encomenda encomenda, ArrayList<String> errosEspecificidade) throws BusinessRuleException {
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
		erros.addAll(errosEspecificidade);
		if (erros.size() > 0) {
			throw new BusinessRuleException(erros);
		}
		return 0;
	}

}
