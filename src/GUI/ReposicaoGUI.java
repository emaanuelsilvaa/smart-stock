package GUI;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.IMateriaPrimaService;
import BUSINESS.IProdutoFinalService;
import BUSINESS.IRelatorioService;
import BUSINESS.MateriaPrimaService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.RelatorioService;
import UTIL.BusinessRuleException;

public class ReposicaoGUI {
	
	static IRelatorioService relatorioService;
	static IProdutoFinalService produtoFinalService;
	static IMateriaPrimaService materiaPrimaService;
	
	public ReposicaoGUI() {
		// TODO Auto-generated constructor stub
		relatorioService = RelatorioService.getInstance();
		produtoFinalService = ProdutoFinalService.getInstance();
		materiaPrimaService = MateriaPrimaService.getInstance();
	}
	public static void init(int a) {
		new ReposicaoGUI();
		telaInicial(1);
	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Lista de Reposição de Produto Final");
		funcoes.put(2, "Lista de Reposição de Matéria-Prima");

		funcoesPtr.put(0, ReposicaoGUI::sair);
		funcoesPtr.put(1, ReposicaoGUI::listaReposicaoProduto);
		funcoesPtr.put(2, ReposicaoGUI::listaReposicaoMateriaPrima);


		while (opt != 0) {
			System.out.println("===== Menu Reposição =====");
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
	
	public static void sair(int a) {
		System.out.println("Saindo do Menu Relatório");

	}
	public static void mostrarListaReposicaoProduto(Date data1, Date data2) {
		try {
			HashMap<Integer, Integer> produtosFaltantes = relatorioService.listarReposicaoProduto(data1, data2);
			if (produtosFaltantes.isEmpty()) {
				System.out.println("Nenhum Produto Final faltará!");
			} else {
				System.out.println(" Produtos Finais que faltarão: \n");
				for(int id : produtosFaltantes.keySet()) {
					System.out.printf("%s: %d\n", produtoFinalService.procuraPeloId(id).getNome(), produtosFaltantes.get(id));
				}
			}
		} catch (BusinessRuleException bre) {
			System.out.println("Lista de Produtos Finais não encotrado pelo seguinte motivo:");
			System.out.println(bre.getMessage());
		}
	}
	public static void mostrarListaReposicaoMateriaPrima(Date data1, Date data2) {
		try {
			HashMap<Integer, Float> materiasFaltantes = relatorioService.listarReposicaoMateriaPrima(data1, data2);
			if (materiasFaltantes.isEmpty()) {
				System.out.println("Nenhuma Matéria-Prima faltará!");
			} else {
				System.out.println("Matérias-Primas que faltarão: \n");
				for(int id : materiasFaltantes.keySet()) {
					System.out.printf("%s: %f %s\n", materiaPrimaService.procuraPeloId(id).getNome(), materiasFaltantes.get(id), materiaPrimaService.procuraPeloId(id).getUnMedida());
				}
			}
		} catch (BusinessRuleException bre) {
			System.out.println("Lista de Produtos Finais não encotrado pelo seguinte motivo:");
			System.out.println(bre.getMessage());
		}
	}
	public static void listaReposicaoMateriaPrima(int a) {
		int opt = -1;
		Date data1 = new Date();
		Date data2 = new Date();
		
		do {
			System.out.println("\n ===== Menu de análise de MateriaPrima ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Matérias-Primas que faltarão");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch(opt) {
				case 0:
					break;
				case 1:
					System.out.print("[Date] Data inicial? (mês/dia/ano) ");
					data1 = new Date(input.nextLine());
					System.out.print("[Date] Data final? (mês/dia/ano) ");
					data2 = new Date(input.nextLine());
					mostrarListaReposicaoMateriaPrima(data1, data2);
					break;
				default:
					System.out.println("Digite uma opção válida");
					break;
				}
			} catch(NumberFormatException n) {
				System.out.println("Digite uma opção válida!");
			}
			catch(Exception e) {
				System.out.println("Digite uma data válida!");
			} 
		} while (opt != 0);
	}
	public static void listaReposicaoProduto(int a) {
		int opt = -1;
		Date data1 = new Date();
		Date data2 = new Date();
		
		do {
			System.out.println("\n ===== Menu de análise de Produtos Finais ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Produtos Finais que faltarão");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch(opt) {
				case 0:
					break;
				case 1:
					System.out.print("[Date] Data inicial? (mês/dia/ano) ");
					data1 = new Date(input.nextLine());
					System.out.print("[Date] Data final? (mês/dia/ano) ");
					data2 = new Date(input.nextLine());
					mostrarListaReposicaoProduto(data1, data2);
					break;
				default:
					System.out.println("Digite uma opção válida");
					break;
				}
			} catch(NumberFormatException n) {
				System.out.println("Digite uma opção válida!");
			} catch(Exception e) {
				System.out.println("Digite uma data válida!");
			} 
		} while (opt != 0);
	}

}
