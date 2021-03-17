package DATA;
import java.util.ArrayList;

import ENTITY.Venda;

public class VendaDAO implements IVendaDAO {
	private ArrayList <Venda> vendas;
	protected int idSerial;
	
	public VendaDAO () {
		this.vendas = new ArrayList<Venda>();
		this.idSerial = 1;
	}
	
	
	@Override
	public int inserir(Venda venda) {
		venda.setId(this.pegaEIncremanetaId());
		this.vendas.add(venda);
		return venda.getId();
	}
	
	@Override
	public int remover(int id) {
		Venda aux = new Venda();
		for (Venda venda : this.vendas) {
			if(venda.getId() == id) {
				aux = venda;
				break;
			}
		}
		this.vendas.remove(aux);
		return aux.getId();
	}
	
	@Override
	public ArrayList<Venda> procuraTodos(){
		return this.vendas;
	}
	
	@Override
	public Venda procuraPeloId(int id) {
		for (Venda venda : this.vendas) {
			if (venda.getId() == id) {
				return venda;
			}
		}
		return null;
	};
	
	@Override
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
	
}
