package BUSINESS;

import java.util.ArrayList;

import ENTITY.MateriaPrimaReal;

public interface IMateriaPrimaRealService {

	int inserir(MateriaPrimaReal materiaPrimaReal);

	int remover(int id);

	int alterar(int id, MateriaPrimaReal materiaPrimaReal);

	int alterarQuantidade(int id, float quantidade);

	MateriaPrimaReal procuraPeloId(int id);

	ArrayList<MateriaPrimaReal> procuraPeloIdExterno(int id);

	ArrayList<MateriaPrimaReal> procuraTodos();

	int validarCadastro(int id);

}