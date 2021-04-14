package BUSINESS;

import ENTITY.Frete;
import ENTITY.Venda;


public interface FreteStrategy {
	double calcularFrete(Frete frete, Venda venda);
}
