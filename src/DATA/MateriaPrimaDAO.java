package DATA;

import BUSINESS.MateriaPrima;

public class MateriaPrimaDAO {
	protected MateriaPrima[] materiasPrimas;
	
	// Construtores
	public MateriaPrimaDAO() {
		
	}

	public MateriaPrimaDAO(MateriaPrima[] materiasPrimas) {
		super();
		this.materiasPrimas = materiasPrimas;
	}

	// Getters e Setters
	public MateriaPrima[] getMateriasPrimasDAO() {
		return this.materiasPrimas;
	}

	public void setMateriasPrimasDAO(MateriaPrima[] materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}
	
	// Regras de Negócio
	public void validarCadastroMateriaPrima(int idMateriaPrima) {
		// Checagem se matéria prima existe
		MateriaPrima materiaPrima= consultarMateriaPrimaPeloId(idMateriaPrima);
		
		if(materiaPrima== null) {
			System.out.println(">> Matéria Prima de ID=" + idMateriaPrima + " não cadastrada.");
		}
		else {	
			// Validação de Tipo (Padrão: Alimento ou Embalagem.)
			if(materiaPrima.getTipo().toLowerCase() != "alimento" &&
			   materiaPrima.getTipo().toLowerCase() != "embalagem") {
				System.out.println(">> Tipo da Matéria Prima " + materiaPrima.getIdMateriaPrima() + " cadastrado incorretamente. "
						+ "Insira novamente!");
			}
		}
		
		System.out.println("\n");
	}
	
	public MateriaPrima consultarMateriaPrimaPeloId(int idMateriaPrima) {
		for(MateriaPrima m : this.materiasPrimas) {
			if(m.getIdMateriaPrima() == idMateriaPrima) {
				MateriaPrima materiaPrima = m;
				return materiaPrima;
			}
		}
		return null;
	}
	
	public void listarMateriasPrimas() {
		
		System.out.println(">>> Matérias primas cadastradas:");
		
		for(MateriaPrima m : this.materiasPrimas) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Materia Prima ID=" + m.getIdMateriaPrima() + ": ");
			System.out.println("> Nome: " + m.getNome());
			System.out.println("> Tipo: " + m.getTipo());
			System.out.println("> Preço: " + m.getPreço());
			System.out.println("> Fornecedor: " + m.getFornecedor());
			System.out.println("> Perecivel: " + m.isPerecivel());
			System.out.println("> Quantidade: " + m.getQuantidade());
			System.out.println("> Unidade de Medida: " + m.getUnMedida());
			System.out.println("> Validade: " + m.getValidade());
		}
		
		System.out.println("\n");
	}
}
