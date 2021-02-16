package BUSINESS;

import DATA.IProdutoFinalDAO;
import DATA.MateriaPrimaDAO;
import DATA.ProdutoFinalDAO;

public class EstoqueService {
	
	IProdutoFinalDAO produtoDAO = new ProdutoFinalDAO();
	MateriaPrimaDAO materia_primaDAO = new MateriaPrimaDAO();

	public EstoqueService() {
		// TODO Auto-generated constructor stub
	}

	public int baixaProdutoFinal(int id, int quantidade) {
		if (verificaDisponibilidadeProduto(id, quantidade)) {
			produtoDAO.alterarQuantidade(id, -1 * quantidade);
			return 0;
		} else {
			return -1;
		}
	}

	public int baixaMateriaPrima(int id, float quantidade) {
		if (verificaDisponibilidadeMateriaPrima(id, quantidade)) {
			// materia_primaDAO.alterarQuantidade(id, -1 * quantidade);
			return 0;
		} else {
			return -1;
		}
	}

	public boolean verificaDisponibilidadeProduto(int id, int quantidade) {
		if (produtoDAO.procuraPeloId(id).getUnidades() >= quantidade) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verificaDisponibilidadeMateriaPrima(int id, float quantidade) {
//		if(materia_primaDAO.procuraPeloId(id).getQuantidade() >= quantidade) {
//			return true;
//		} else {
//			return false;
//		}
		return true;
	}
}
