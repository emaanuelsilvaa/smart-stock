package DATA;

import java.util.ArrayList;

import ENTITY.Venda;

public interface IVendaDAO {

	void inserir(Venda venda);

	boolean remover(int id);

	ArrayList<Venda> procuraTodos();

	Venda procuraPeloID(int id);

	//Cria um novo ID Baseado no ID do último elemento, sempre criando um ID novo {Solução temporária}
	int getNextID();

}