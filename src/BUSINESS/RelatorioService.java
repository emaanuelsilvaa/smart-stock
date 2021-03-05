package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;

public final class RelatorioService {
	protected IEncomendaService encomendaService;
	protected IEstoqueService estoqueService;
	private static RelatorioService instance;

	private RelatorioService() {
		this.encomendaService = EncomendaService.getInstance();
		this.estoqueService = EstoqueService.getInstance();
	}

	public static RelatorioService getInstence() {
		if (instance == null) {
			instance = new RelatorioService();
		}
		return instance;
	}

	public HashMap<Integer, Integer> listarReposicaoProduto(Date dataInicio, Date dataFim) {
		// Função que retorna a lista de produtos necessário para atender as encomendas
		// a partir de um intervalo de tempo
		HashMap<Integer, Integer> qntProduto = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> qntProdutoFaltante = new HashMap<Integer, Integer>();
		for (Encomenda e : encomendaService.procuraTodos()) {
			if (e.getDataEntrega().after(dataInicio) && e.getDataEntrega().before(dataFim)) {
				for (int id : e.getListaProdutos().keySet()) {
					if (!qntProduto.containsKey(id)) {
						qntProduto.put(id, e.getListaProdutos().get(id));
					} else {
						int aux = qntProduto.get(id) + e.getListaProdutos().get(id);
						qntProduto.replace(id, aux); // incrementando a lista de produtos necessários
					}
				}
			}
		}
		for (int id : qntProduto.keySet()) {
			int disponibilidade = estoqueService.verificaDisponibilidadeProduto(id, qntProduto.get(id));
			if (disponibilidade < 0) {
				qntProdutoFaltante.put(id, disponibilidade);
			}
		}
		return qntProdutoFaltante;
	}

}
