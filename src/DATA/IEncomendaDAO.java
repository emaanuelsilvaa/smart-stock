package DATA;

import java.util.ArrayList;

import ENTITY.Encomenda;

public interface IEncomendaDAO {

	/*@ requires encomenda != null;
	 @  ensures encomenda.getId() == \result;
	@*/
	int inserir(Encomenda encomenda);

	/*@ requires id > 0;
	 @  ensures \result == id;
	@*/
	int remover(int id);

	/*@ pure @*/ ArrayList<Encomenda> procuraTodos();

	/*@ pure @*/ Encomenda procuraPeloId(int id);

	/*@ requires id > 0;
	 @  requires encomenda != null;
	 @  ensures \result == id;
	@*/
	int alterar(int id, Encomenda encomenda);

	int pegaEIncremanetaId();

}