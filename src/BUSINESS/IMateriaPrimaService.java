package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;

public interface IMateriaPrimaService {

	int inserir(MateriaPrima materiaPrima) throws BusinessRuleException;

	void remover(int id) throws BusinessRuleException;

	int alterar(int id, MateriaPrima materiaPrima) throws BusinessRuleException;

	/*@ pure @*/ MateriaPrima procuraPeloId(int id);

	/*@ pure @*/ ArrayList<MateriaPrima> procuraTodos();

	void validarCadastro(MateriaPrima materiaPrimaid) throws BusinessRuleException;

}