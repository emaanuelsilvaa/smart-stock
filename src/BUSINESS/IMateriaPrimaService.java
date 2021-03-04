package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrima;

public interface IMateriaPrimaService {

	int inserir(MateriaPrima materiaPrima);

	int remover(int id);

	int alterar();

	MateriaPrima procuraPeloId(int id);

	ArrayList<MateriaPrima> procuraTodos();

	int validarCadastro(int id);

}