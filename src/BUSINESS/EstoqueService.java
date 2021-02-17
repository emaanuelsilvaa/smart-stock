package BUSINESS;

import DATA.IProdutoFinalDAO;
import DATA.MateriaPrimaDAO;
import DATA.ProdutoFinalDAO;

public class EstoqueService {
	
	ProdutoFinalDAO produtoDAO;
	MateriaPrimaDAO materiaPrimaDAO;

	public EstoqueService() {
		// TODO Auto-generated constructor stub
		produtoDAO = new ProdutoFinalDAO();
		materiaPrimaDAO = new MateriaPrimaDAO();
	}
	public EstoqueService(ProdutoFinalDAO produtoDAO, MateriaPrimaDAO materiaPrimaDAO) {
		this.produtoDAO = produtoDAO;
		this.materiaPrimaDAO = materiaPrimaDAO;
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
			materiaPrimaDAO.alterarQuantidade(id, -1 * quantidade);
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
		if(materiaPrimaDAO.procuraPeloId(id).getQuantidade() >= quantidade) {
			return true;
		} else {
			return false;
		}
	}
}
