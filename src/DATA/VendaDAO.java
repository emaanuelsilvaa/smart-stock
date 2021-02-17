package DATA;
import java.util.ArrayList;
import BUSINESS.Venda;

public class VendaDAO implements IVendaDAO {
	
	private ArrayList <Venda> vendas;
	
	
	public VendaDAO () {
		this.vendas = new ArrayList<Venda>();
	};
	
	
	public void inserir(Venda venda) {
		
		this.vendas.add(venda);
	};
	
	
	public boolean remover(int id) {
		boolean temVendaCompativel = false;
		Venda aux = new Venda();
		for(Venda venda : this.vendas) {
			if(venda.getId() == id) {
				aux = venda;
				temVendaCompativel = true;
				break;
			}
		};
		
		if(temVendaCompativel) {
			this.vendas.remove(aux);
			return true;
		}
		
		else {
			return false;
		}
	};
	public ArrayList<Venda> procuraTodos(){
		return this.vendas;
	}
	
	public Venda procuraPeloID(int id) {
		boolean temVendaCompativel = false;
		Venda aux = new Venda();
		for(Venda venda : this.vendas) {
			if(venda.getId() == id) {
				aux = venda;
				temVendaCompativel = true;
				break;
			}
		};
		
		if(temVendaCompativel) {
			return aux;
		}
		
		else {
			return null;
		}
	};
	
	//Cria um novo ID Baseado no ID do último elemento, sempre criando um ID novo {Solução temporária}
	public int getNextID() {
		int length = this.vendas.size();
		if (this.vendas.isEmpty()) {
			return 1;
		}
		int nextID = this.vendas.get(length-1).getId() + 1;
		return nextID;
	}
	
}
