package BUSINESS;

import java.util.ArrayList;

import ENTITY.Bone;
import ENTITY.ProdutoFinal;
import UTIL.BusinessRuleException;

public class ValidarBone implements ValidarStrategy {
	
	protected IMateriaPrimaService materiaPrimaService;

	public ValidarBone() {
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
		if (((Bone) produtoFinal).getCor() == null || ((Bone) produtoFinal).getCor() == "") {
			erros.add("Tentou inserir uma cor inválida");
		}
		if (((Bone) produtoFinal).getEstilo() == null || ((Bone) produtoFinal).getEstilo() == "") {
			erros.add("Tentou inserir um estilo inválido");
		}
		if (((Bone) produtoFinal).getFecho() == null || ((Bone) produtoFinal).getFecho() == "") {
			erros.add("Tentou inserir um fecho inválido");
		}
		if (erros.size()>0) {
			throw new BusinessRuleException(erros);
		}
	}

}
