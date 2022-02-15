package BUSINESS;

import java.util.Date;

public interface IAnaliseLucro {
	
	public float analisarLucro(/*@ non_null @*/ Date dataInicio, /*@ non_null @*/Date dataFim);
	
}