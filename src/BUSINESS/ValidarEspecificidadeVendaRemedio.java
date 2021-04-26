package BUSINESS;

import java.util.Date;
import java.util.ArrayList;

import ENTITY.Especificidade;
import ENTITY.EspecificidadeRemedio;
import ENTITY.Remedio;
import ENTITY.Venda;

public class ValidarEspecificidadeVendaRemedio implements EspecificidadeVendaStrategy {

	protected IProdutoFinalService produtoFinalService;

	public ValidarEspecificidadeVendaRemedio() {
		// TODO Auto-generated constructor stub
		this.produtoFinalService = ProdutoFinalService.getInstance();
	}

	@Override
	public ArrayList<String> validarEspecificidades(Especificidade especificidade, Venda venda) {
		ArrayList<String> erros = new ArrayList<String>();
		Date dataAtual = new Date();
		boolean necessitaReceita = false;
		for (int id : venda.getListaProdutos().keySet()) {
			if (!((Remedio) produtoFinalService.procuraPeloId(id)).getTarja().equals("sem tarja")
					&& !((Remedio) produtoFinalService.procuraPeloId(id)).getTarja().equals("amarela")) {
				necessitaReceita = true;
			}
		}
		if (necessitaReceita) {
			if (((EspecificidadeRemedio) especificidade).getData().before(dataAtual) && necessitaReceita) {
				erros.add("Data da receita vencida");
			}
			if (((EspecificidadeRemedio) especificidade).getCrm().isEmpty() && necessitaReceita) {
				erros.add("CRM inválido");
			}
		}
		return erros;
	}

}
