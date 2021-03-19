package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.IMateriaPrimaService;
import BUSINESS.MateriaPrimaService;
import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;
import UTIL.Colors;

public class MateriaPrimaGUI {

	static IMateriaPrimaService materiaPrimaService;

	public MateriaPrimaGUI() {
		// TODO Auto-generated constructor stub
		materiaPrimaService = MateriaPrimaService.getInstance();
	}

	public static void init(int a) {
		new MateriaPrimaGUI();
		telaInicial(1);
	}

	public static void telaCadastrar(int a) {
		String nome = "";
		String tipo = "";
		boolean perecivel = true;
		String unMedida = "";
		float qntMinima = 0;
		int aux = -1;
		int id;
		System.out.println("===== Cadastrar Matéria-Prima =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[String] Entre com o nome: ");
				nome = input.nextLine();
				System.out.print("[String] Entre com o tipo: ");
				tipo = input.nextLine();
				System.out.print("[Boolean] É perecível? ");
				perecivel = Boolean.getBoolean(input.nextLine());
				System.out.print("[String] Entre com a unidade de medida: ");
				unMedida = input.nextLine();
				System.out.print("[Float] Entre com a quantidade mínima no estoque: ");
				qntMinima = Float.parseFloat(input.nextLine());
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		try {
			id = materiaPrimaService.inserir(new MateriaPrima(nome, tipo, perecivel, unMedida, qntMinima));
			System.out.println("Matéria Prima cadastrada com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Matéria Prima não cadastrada pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}

	}

	public static void telaAlterar(int a) {
		String nome = "";
		String tipo = "";
		boolean perecivel = true;
		String unMedida = "";
		float qntMinima = 0;
		int aux = -1;
		int id;
		int idASubstituir = 0;
		System.out.println("===== Alterar Matéria-Prima =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o ID da matéria-prima a ser alterada: ");
				idASubstituir = Integer.parseInt(input.nextLine());
				System.out.print("[String] Entre com o nome: ");
				nome = input.nextLine();
				System.out.print("[String] Entre com o tipo: ");
				tipo = input.nextLine();
				System.out.print("[Boolean] É perecível? ");
				perecivel = Boolean.getBoolean(input.nextLine());
				System.out.print("[String] Entre com a unidade de medida: ");
				unMedida = input.nextLine();
				System.out.print("[Float] Entre com a quantidade mínima no estoque: ");
				qntMinima = Float.parseFloat(input.nextLine());
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		try {
			id = materiaPrimaService.alterar(idASubstituir,new MateriaPrima(nome, tipo, perecivel, unMedida, qntMinima));
			System.out.println("Matéria Prima alterada com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out
					.println("Matéria Prima não alterada pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
	}

	public static void materiasPrimasCadastradas() {
		ArrayList<MateriaPrima> materiasPrimas = materiaPrimaService.procuraTodos();
		if (materiasPrimas.isEmpty()) {
			System.out.println("Nenhuma matéria-prima cadastrada");
		} else {
			System.out.println("\nMatérias-Primas cadastradas: \n");
			for (MateriaPrima m : materiasPrimas) {
				System.out.printf("[%d] %s \n", m.getId(), m.getNome());
			}
		}

	}

	public static void materiaPrimaDetalhada(int id) {
		MateriaPrima m = materiaPrimaService.procuraPeloId(id);
		if (m == null) {
			System.out.println("Matéria-Prima não encontrada");
		} else {
			System.out.printf("\nId: %d\n", m.getId());
			System.out.printf("Nome: %s\n", m.getNome());
			System.out.printf("Perecivel: %b\n", m.getPerecivel());
			System.out.printf("Quantidade minima: %f\n", m.getQntMinima());
			System.out.printf("Tipo: %s\n", m.getTipo());
			System.out.printf("Unidade de Medida: %s\n\n", m.getUnMedida());

		}
	}
	public static void removerMateriaPrima(int id) {
		try{
			materiaPrimaService.remover(id);
			System.out.println("Materia prima de Id: "+ id + " removida");
		} catch (BusinessRuleException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void telaConsultar(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Consultar Matéria-Prima ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Matérias-Primas cadastradas");
			System.out.printf("[%d] %s \n", 2, "Ver Matéria-Prima detalhada");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					materiasPrimasCadastradas();
					break;
				case 2:
					System.out.print("Digite o id da matéria-prima: ");
					opt2 = Integer.parseInt(input.nextLine());
					materiaPrimaDetalhada(opt2);
					break;
				default:
					throw new Exception("Aqui!");

				}
			} catch (Exception e) {
				System.out.println("Digite um valor válido");
				e.printStackTrace();
			}
		} while (opt != 0);

	}

	public static void telaRemover(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Remover Matéria-Prima ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Matérias-Primas cadastradas");
			System.out.printf("[%d] %s \n", 2, "Remover Matéria-Prima");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					materiasPrimasCadastradas();
					break;
				case 2:
					System.out.print("Digite o id da matéria-prima: ");
					opt2 = Integer.parseInt(input.nextLine());
					removerMateriaPrima(opt2);
					break;
				default:
					throw new Exception("Aqui!");

				}
			} catch (Exception e) {
				System.out.println("Digite um valor válido");
				e.printStackTrace();
			}
		} while (opt != 0);
		

	}

	public static void sair(int a) {
		System.out.println("Saindo do Menu Matéria-Prima");

	}

	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Cadastrar Matéria-Prima");
		funcoes.put(2, "Alterar Matéria-Prima");
		funcoes.put(3, "Consultar Matéria-Prima");
		funcoes.put(4, "Remover Matéria-Prima");

		funcoesPtr.put(0, MateriaPrimaGUI::sair);
		funcoesPtr.put(1, MateriaPrimaGUI::telaCadastrar);
		funcoesPtr.put(2, MateriaPrimaGUI::telaAlterar);
		funcoesPtr.put(3, MateriaPrimaGUI::telaConsultar);
		funcoesPtr.put(4, MateriaPrimaGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Matéria-Prima =====");
			System.out.println("\nOperações disponíveis:\n");
			for (int i : funcoes.keySet()) {
				System.out.printf("[%d] %s \n", i, funcoes.get(i));
			}
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				funcoesPtr.get(opt).accept(1);
			} catch (Exception e) {
				System.out.println("Digite um valor válido. Erro: " + e.getMessage());
				opt = -1;
			}
			System.out.println("");
		}
	}

}
