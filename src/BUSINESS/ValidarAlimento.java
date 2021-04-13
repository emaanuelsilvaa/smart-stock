package BUSINESS;

import java.util.ArrayList;

import ENTITY.ProdutoFinal;
import ENTITY.Alimento;
import UTIL.BusinessRuleException;

public class ValidarAlimento implements ValidarStrategy {
	
	protected IMateriaPrimaService materiaPrimaService;
	
	public ValidarAlimento() {
		// TODO Auto-generated constructor stub
		materiaPrimaService = MateriaPrimaService.getInstance();
	}

	@Override
	public void validarEspecificidades(ProdutoFinal produtoFinal) throws BusinessRuleException {
		// TODO Auto-generated method stub
		ArrayList<String> erros = new ArrayList<String>();
		if(produtoFinal == null) {
			erros.add("Tentou inserir um Produto Final nulo");
		}
		if(produtoFinal.getNome() == "" || produtoFinal.getNome() == null ) {
			erros.add("Tentou inserir uma ProdutoFinal com nome nulo");
		}
		if(produtoFinal.getQntMinima() < 0) {
			erros.add("Tentou inserir uma quantidade mínima negativa");
		}
		if(produtoFinal.getPreco() < 0) {
			erros.add("Tentou inserir um preço negativo");
		}
		if(produtoFinal.getReceita().isEmpty()) {
			erros.add("Tentou inserir uma receita vazia");
		} else {
			for(int i : produtoFinal.getReceita().keySet()) {
				if(this.materiaPrimaService.procuraPeloId(i) == null) {
					erros.add("MatériaPrima de ID " + i + "não cadastrada");
				}
			}
		}
		if (((Alimento) produtoFinal).getCalorias() <= 0) {
			erros.add("Tentou inserir uma quantidade calórica inválida");
		}
		if (erros.size()>0) {
			throw new BusinessRuleException(erros);
		}
	}

}
