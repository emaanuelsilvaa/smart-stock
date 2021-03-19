package GUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.IMateriaPrimaRealService;
import BUSINESS.IMateriaPrimaService;
import BUSINESS.MateriaPrimaRealService;
import BUSINESS.MateriaPrimaService;
import ENTITY.MateriaPrima;
import ENTITY.MateriaPrimaReal;
import UTIL.BusinessRuleException;
import UTIL.Colors;

public class MateriaPrimaRealGUI {
	
	static IMateriaPrimaRealService materiaPrimaRealService;
	static IMateriaPrimaService materiaPrimaService;
	
	public MateriaPrimaRealGUI() {
		// TODO Auto-generated constructor stub
		materiaPrimaRealService = MateriaPrimaRealService.getInstance();
		materiaPrimaService = MateriaPrimaService.getInstance();

	}
	
	public static void init(int a) {
		new MateriaPrimaRealGUI();
		telaInicial(1);
		
	}
	public static void telaAlterar(int a) {
		int idExterno = 0;
		float preco = 0;
		Date validade = new Date();
		float quantidade = 0;
		int idFornecedor = 0;
		int aux = -1;
		int id;
		int idASubstituir = 0;
		System.out.println("===== Alterar Matéria-Prima =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o ID da matéria-prima a ser alterada: ");
				idASubstituir = Integer.parseInt(input.nextLine());
				System.out.print("[Int] Entre com o ID da Matéria-Prima Genérica: ");
				idExterno = Integer.parseInt(input.nextLine());
				System.out.print("[Float] Entre com o preço: ");
				preco = Float.parseFloat(input.nextLine());
				System.out.print("[Date] Data de Validade? (mês/dia/ano) ");
				validade = new Date(input.nextLine());
				System.out.print("[Float] Entre com a quantidade: ");
				quantidade = Float.parseFloat(input.nextLine());
				System.out.print("[Int] Entre com o id do Fornecedor: ");
				idFornecedor = Integer.parseInt(input.nextLine());
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		try {
			id = materiaPrimaRealService.alterar(idASubstituir,new MateriaPrimaReal(idExterno, preco, validade, quantidade, idFornecedor));
			System.out.println("Matéria-Prima em Estoque alterada com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out
					.println("Matéria Prima em Estoque não alterada pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
	}
	
	public static void materiaPrimaRealDetalhada(int id) {
		MateriaPrimaReal m = materiaPrimaRealService.procuraPeloId(id);
		if (m == null) {
			System.out.println("Matéria-Prima não encontrada");
		} else {
			System.out.printf("\nId: %d\n", m.getId());
			System.out.printf("Nome: %s\n", materiaPrimaService.procuraPeloId(m.getIdExterno()).getNome());
			System.out.printf("Preço: %f\n", m.getPreço());
			System.out.printf("Quantidade: %f %s\n", m.getQuantidade(), materiaPrimaService.procuraPeloId(m.getIdExterno()).getUnMedida());
			System.out.printf("Validade: %s\n", m.getValidade());
		}
	}
	
	public static void telaConsultar(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Consultar Matéria-Prima em Estoque ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Matérias-Primas em estoque cadastradas");
			System.out.printf("[%d] %s \n", 2, "Ver Matéria-Prima em estoque detalhada");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					materiasPrimasReaisCadastradas();
					break;
				case 2:
					System.out.print("Digite o id da matéria-prima em estoque: ");
					opt2 = Integer.parseInt(input.nextLine());
					materiaPrimaRealDetalhada(opt2);
					break;
				default:
					throw new Exception();

				}
			} catch (Exception e) {
				System.out.println("Digite um valor válido");
				e.printStackTrace();
			}
		} while (opt != 0);

	}
	
	public static void telaCadastrar(int a) {
		int aux = -1;
		int id;
		int idExterno = 0;
		float preco = 0;
		Date validade = new Date();
		float quantidade = 0;
		int idFornecedor = 0;
		System.out.println("===== Cadastrar Matéria-Prima em Estoque =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o ID da Matéria-Prima Genérica: ");
				idExterno = Integer.parseInt(input.nextLine());
				System.out.print("[Float] Entre com o preço: ");
				preco = Float.parseFloat(input.nextLine());
				System.out.print("[Date] Data de Validade? (mês/dia/ano) ");
				validade = new Date(input.nextLine());
				System.out.print("[Float] Entre com a quantidade: ");
				quantidade = Float.parseFloat(input.nextLine());
				System.out.print("[Int] Entre com o id do Fornecedor: ");
				idFornecedor = Integer.parseInt(input.nextLine());
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		try {
			id = materiaPrimaRealService.inserir(new MateriaPrimaReal(idExterno, preco, validade, quantidade, idFornecedor));
			System.out.println("Matéria Prima em Estoque cadastrada com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Matéria Prima não cadastrada pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}

	}
	
	public static void removerMateriaPrimaReal(int id) {
		try{
			materiaPrimaRealService.remover(id);
			System.out.println("Materia prima em Estoque de Id: "+ id + " removida");
		} catch (BusinessRuleException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void materiasPrimasReaisCadastradas() {
		ArrayList<MateriaPrimaReal> materiasPrimasReais = materiaPrimaRealService.procuraTodos();
		if (materiasPrimasReais.isEmpty()) {
			System.out.println("Nenhuma matéria-prima em Estoque cadastrada");
		} else {
			System.out.println("\nMatérias-Primas em Estoque cadastradas: \n");
			for (MateriaPrimaReal m : materiasPrimasReais) {
				System.out.printf("[%d] %s: %f %s \n", m.getId(), materiaPrimaService.procuraPeloId(m.getIdExterno()).getNome(), m.getQuantidade(), materiaPrimaService.procuraPeloId(m.getIdExterno()).getUnMedida());
			}
		}

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
					materiasPrimasReaisCadastradas();
					break;
				case 2:
					System.out.print("Digite o id da matéria-prima: ");
					opt2 = Integer.parseInt(input.nextLine());
					removerMateriaPrimaReal(opt2);
					break;
				default:
					throw new Exception();

				}
			} catch (Exception e) {
				System.out.println("Digite um valor válido");
			}
		} while (opt != 0);
		

	}

	
	public static void sair(int a) {
		System.out.println("Saindo do Menu Matéria-Prima no Estoque");

	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Cadastrar Matéria-Prima no Estoque");
		funcoes.put(2, "Alterar Matéria-Prima no Estoque");
		funcoes.put(3, "Consultar Matéria-Prima no Estoque");
		funcoes.put(4, "Remover Matéria-Prima no Estoque");

		funcoesPtr.put(0, MateriaPrimaRealGUI::sair);
		funcoesPtr.put(1, MateriaPrimaRealGUI::telaCadastrar);
		funcoesPtr.put(2, MateriaPrimaRealGUI::telaAlterar);
		funcoesPtr.put(3, MateriaPrimaRealGUI::telaConsultar);
		funcoesPtr.put(4, MateriaPrimaRealGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Matéria-Prima no Estoque =====");
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
