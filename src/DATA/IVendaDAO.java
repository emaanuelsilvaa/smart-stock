package DATA;

import java.util.ArrayList;

import ENTITY.Venda;

public interface IVendaDAO {

	int inserir(Venda venda);

	int remover(int id);

	ArrayList<Venda> procuraTodos();

	Venda procuraPeloId(int id);

	int pegaEIncremanetaId();

}