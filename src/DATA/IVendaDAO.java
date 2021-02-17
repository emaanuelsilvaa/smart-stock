package DATA;

import BUSINESS.Venda;

public interface IVendaDAO {
	
	
	 public void inserir(Venda venda);
	 public boolean remover(int id);
	 public Venda procuraPeloID(int id);
}
