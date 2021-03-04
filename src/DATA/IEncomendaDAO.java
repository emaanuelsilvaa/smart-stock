package DATA;

import java.util.ArrayList;

import ENTITY.Encomenda;

public interface IEncomendaDAO {

	void inserir(Encomenda encomenda);

	boolean remover(int id);

	ArrayList<Encomenda> procuraTodos();

	Encomenda procuraPeloId(int id);

	int alterar(int id, Encomenda encomenda);

}