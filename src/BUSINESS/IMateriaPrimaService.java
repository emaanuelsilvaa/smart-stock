package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;

public interface IMateriaPrimaService {

	int inserir(MateriaPrima materiaPrima) throws BusinessRuleException;

	void remover(int id) throws BusinessRuleException;

	void alterar(int id, MateriaPrima materiaPrima) throws BusinessRuleException;

	MateriaPrima procuraPeloId(int id);

	ArrayList<MateriaPrima> procuraTodos();

	void validarCadastro(MateriaPrima materiaPrimaid) throws BusinessRuleException;

}