package BUSINESS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ENTITY.Remedio;
import ENTITY.ProdutoFinal;
import UTIL.BusinessRuleException;

public class ValidarRemedio implements ValidarStrategy {
	
	protected IMateriaPrimaService materiaPrimaService;

	public ValidarRemedio() {
		// TODO Auto-generated constructor stub
		materiaPrimaService = MateriaPrimaService.getInstance();
	}

	@Override
	public void validarEspecificidades(ProdutoFinal produtoFinal) throws BusinessRuleException {
		// TODO Auto-generated method stub
		ArrayList<String> erros = new ArrayList<String>();
	    Set<String> tarjas = new HashSet<String>();
	    tarjas.add("sem tarja");
	    tarjas.add("amarela");
	    tarjas.add("vermelha");
	    tarjas.add("preta");
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
		if (((Remedio) produtoFinal).getTarja() == null || !tarjas.contains(((Remedio) produtoFinal).getTarja())) {
			erros.add("Tentou inserir uma tarja inválida");
		}
		if (erros.size()>0) {
			throw new BusinessRuleException(erros);
		}
	}
	
}
