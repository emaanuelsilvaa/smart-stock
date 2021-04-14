package BUSINESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ENTITY.Encomenda;
import ENTITY.Especificidade;
import UTIL.BusinessRuleException;

public interface IEncomendaService {

	ArrayList<Encomenda> procuraTodos();

	Encomenda procuraPeloId(int id);

	int inserir(Encomenda encomenda) throws BusinessRuleException;

	int remover(int id) throws BusinessRuleException;

	int consumarEncomenda(int id) throws BusinessRuleException;

	int alterar(int id, Encomenda encomenda) throws BusinessRuleException;

	int realizarEncomenda(HashMap<Integer, Integer> listaProdutos, int idCliente, Date dataEntrega, Especificidade especificidade) throws BusinessRuleException;

	int validarCadastro(Encomenda encomenda) throws BusinessRuleException;

}