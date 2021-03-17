package DATA;

import java.util.ArrayList;

import ENTITY.Encomenda;

public class EncomendaDAO implements IEncomendaDAO {
	private ArrayList<Encomenda> encomendas;
	
	public EncomendaDAO() {
		// TODO Auto-generated constructor stub
		this.encomendas = new ArrayList<Encomenda>();
	}
	@Override
	public void inserir(Encomenda encomenda) {
		this.encomendas.add(encomenda);
	}
	@Override
	public boolean remover(int id) {
		Encomenda aux = new Encomenda();
		for (Encomenda e : encomendas) {
			if(e.getId() == id) {
				aux = e;
				break;
			}
		}
		this.encomendas.remove(aux);
		return true;
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
				return 0;
			}
		}
		return -1;
	}
	public int getNextID() {
		int length = this.encomendas.size();
		if (this.encomendas.isEmpty()) {
			return 1;
		}
		int nextID = this.encomendas.get(length-1).getId() + 1;
		return nextID;
	}
}
