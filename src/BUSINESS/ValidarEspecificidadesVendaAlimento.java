package BUSINESS;

import java.util.ArrayList;

import ENTITY.Especificidade;
import ENTITY.EspecificidadeAlimento;
import ENTITY.Venda;

public class ValidarEspecificidadesVendaAlimento implements EspecificidadeVendaStrategy {
	
	public ValidarEspecificidadesVendaAlimento() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> validarEspecificidades(Especificidade especificidade, Venda venda) {
		ArrayList<String> erros = new ArrayList<String>();
		boolean ehParaBuffetCadastrado = ((EspecificidadeAlimento) especificidade).isBuffetCadastrado();
		if(ehParaBuffetCadastrado) {
			float desconto = ((EspecificidadeAlimento) especificidade).getFatorDeDesconto();
			if(desconto < 0 || desconto > 1) {
				erros.add("Fator de desconto inválido");
			}
			else {
				float valor = venda.getValor();
				venda.setValor(valor * (1-desconto));
			}
		}
		return erros;
	}

}
