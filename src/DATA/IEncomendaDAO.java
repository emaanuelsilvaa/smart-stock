package DATA;

import java.util.ArrayList;

import ENTITY.Encomenda;

public interface IEncomendaDAO {

	int inserir(Encomenda encomenda);

	int remover(int id);

	ArrayList<Encomenda> procuraTodos();

	Encomenda procuraPeloId(int id);

	int alterar(int id, Encomenda encomenda);

	int pegaEIncremanetaId();

}