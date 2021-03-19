package DATA;

import java.util.ArrayList;

import ENTITY.Encomenda;

public class EncomendaDAO implements IEncomendaDAO {
	private ArrayList<Encomenda> encomendas;
	protected int idSerial;
	
	public EncomendaDAO() {
		// TODO Auto-generated constructor stub
		this.encomendas = new ArrayList<Encomenda>();
		this.idSerial = 1;
	}
	
	@Override
	public int inserir(Encomenda encomenda) {
		encomenda.setId(this.pegaEIncremanetaId());
		this.encomendas.add(encomenda);
		return encomenda.getId();
	}
	
	@Override
	public int remover(int id) {
		Encomenda aux = new Encomenda();
		for (Encomenda e : encomendas) {
			if(e.getId() == id) {
				aux = e;
				break;
			}
		}
		this.encomendas.remove(aux);
		return aux.getId();
	}
	
	@Override
	public ArrayList<Encomenda> procuraTodos(){
		return this.encomendas;
	}
	
	@Override
	public Encomenda procuraPeloId(int id) {
		for(Encomenda e : this.encomendas) {
			if(e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	@Override
	public int alterar(int id, Encomenda encomenda) {
		for (Encomenda e : this.encomendas) {
			if(e.getId() == id) {
				e.setId(encomenda.getId());
				e.setIdCliente(encomenda.getIdCliente());
				e.setListaProdutos(encomenda.getListaProdutos());
				e.setValor(encomenda.getValor());
				e.setData(encomenda.getData());
				e.setDataEntrega(encomenda.getDataEntrega());
				break;
			}
		}
		return id;
	}
	
	@Override
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
}
