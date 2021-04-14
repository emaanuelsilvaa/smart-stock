package BUSINESS;

import java.util.Date;
import java.util.ArrayList;

import ENTITY.Especificidade;
import ENTITY.EspecificidadeRemedio;
import ENTITY.Venda;

public class ValidarEspecificidadeVendaRemedio implements EspecificidadeVendaStrategy{

	public ValidarEspecificidadeVendaRemedio() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> validarEspecificidades(Especificidade especificidade, Venda venda){
		ArrayList<String> erros = new ArrayList<String>();
		Date dataAtual = new Date();
		if (((EspecificidadeRemedio) especificidade).getData().before(dataAtual)) {
			erros.add("Data da receita vencida");
		}
		if (((EspecificidadeRemedio) especificidade).getCrm().isEmpty()) {
			erros.add("CRM inválido");
		}
		
		return erros;		
	}

}
