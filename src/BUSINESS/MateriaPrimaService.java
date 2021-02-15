package BUSINESS;

import DATA.MateriaPrimaDAO;

public class MateriaPrimaService {
	protected MateriaPrimaDAO materiasPrimas;
	
	// Construtores
	public MateriaPrimaService() {
		
	}

	public MateriaPrimaService(MateriaPrimaDAO materiasPrimas) {
		super();
		this.materiasPrimas = materiasPrimas;
	}

	// Getters e Setters
	public MateriaPrimaDAO getMateriasPrimas() {
		return materiasPrimas;
	}

	public void setMateriasPrimas(MateriaPrimaDAO materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}
	
	// Regras de Negócio
	public void validarCadastroMateriaPrima(int idMateriaPrima) {
		this.materiasPrimas.validarCadastroMateriaPrima(idMateriaPrima);
	}
	
	public MateriaPrima consultarMateriaPrimaPeloId(int idMateriaPrima) {
		return this.materiasPrimas.consultarMateriaPrimaPeloId(idMateriaPrima);
	}
	
	public void listarMateriasPrimas() {
		this.materiasPrimas.listarMateriasPrimas();
	}
}
