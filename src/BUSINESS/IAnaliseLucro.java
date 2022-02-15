package BUSINESS;

import java.util.Date;

public interface IAnaliseLucro {
	 // public model instance float lucro;

	// ensures lucro >= 0;
	// ensures \result == lucro;
	public float analisarLucro( Date dataInicio, Date dataFim);
	
}