package BUSINESS;

import java.util.ArrayList;
import java.util.Date;

import ENTITY.Especificidade;
import ENTITY.EspecificidadeBone;
import ENTITY.EspecificidadeRemedio;
import ENTITY.Venda;

public class ValidarEspecificidadesVendaBone implements EspecificidadeVendaStrategy {

	public ValidarEspecificidadesVendaBone() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> validarEspecificidades(Especificidade especificidade, Venda venda) {
		ArrayList<String> erros = new ArrayList<String>();
		Date dataAtual = new Date();
		float desconto = ((EspecificidadeBone) especificidade).getFatorDeDesconto();
		if (desconto < 0 || desconto > 1) {
			erros.add("Fator de desconto inválido");
		} else {
			float valor = venda.getValor();
			venda.setValor(valor * (1-desconto));
		}
		return erros;
	}

}
