package BUSINESS;

import java.util.Date;

public interface IAnaliseLucro {
	
	//@ public model instance float lucro;
	
	//@ ensures lucro > 0;
	public float analisarLucro(/*@ non_null @*/ Date dataInicio, /*@ non_null @*/Date dataFim);
	
}