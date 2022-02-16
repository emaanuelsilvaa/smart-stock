package DATA;

import java.util.ArrayList;

import ENTITY.Encomenda;

public class EncomendaDAO implements IEncomendaDAO {
	private /*@ spec_public @*/ ArrayList<Encomenda> encomendas;
	protected /*@ spec_public @*/ int idSerial;
	
	//@ public invariant idSerial > 0;
	
	/*@ assignable encomendas, idSerial;
	 @  ensures encomendas != null;
	 @  ensures idSerial == 1; 
	@*/
	public EncomendaDAO() {
		// TODO Auto-generated constructor stub
		this.encomendas = new ArrayList<Encomenda>();
		this.idSerial = 1;
	}
	
	@Override
	/*@ also
	 @  requires \same;
	 @  ensures encomenda.getId() == \old(idSerial)+1;
	 @  ensures encomendas.contains(encomenda);
	@*/
	public int inserir(Encomenda encomenda) {
		encomenda.setId(this.pegaEIncremanetaId());
		this.encomendas.add(encomenda);
		return encomenda.getId();
	}
	
	@Override
	/*@ also
	 @  requires encomendas.get(id) != null;
	 @  ensures (encomendas.get(id) == null) || (encomendas.get(id) == \old(encomendas.get(id+1)));
	@*/
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
	//@ also ensures \result.equals(encomendas);
	public /*@ pure @*/ ArrayList<Encomenda> procuraTodos(){
		return this.encomendas;
	}
	
	@Override
	/*@ also
	 @  requires id > 0;
	 @  requires encomendas.get(id) != null;
	 @  ensures encomendas.get(id) == \result;
	 @  also
	 @  requires encomendas.get(id) == null;
	 @  ensures \result == null;
	@*/
	public /*@ pure @*/ Encomenda procuraPeloId(int id) {
		for(Encomenda e : this.encomendas) {
			if(e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	@Override
	/*@ also
	 @  requires encomendas.get(id) != null;
	 @  ensures encomendas.contains(encomenda);
	@*/
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
	//@ also ensures idSerial == \old(idSerial) + 1;
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
}
