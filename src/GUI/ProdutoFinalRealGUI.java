package GUI;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.IMateriaPrimaRealService;
import BUSINESS.IProdutoFinalRealService;
import BUSINESS.MateriaPrimaRealService;
import BUSINESS.ProdutoFinalRealService;
import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public class ProdutoFinalRealGUI {
	IProdutoFinalRealService produtoFinalRealService;
	
	public ProdutoFinalRealGUI() {
		produtoFinalRealService = ProdutoFinalRealService.getInstance();
	}
	
	public static void init(int a) {
		new ProdutoFinalRealGUI();
		telaInicial(1);
	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Cadastrar Produto Final Real");
		funcoes.put(2, "Alterar Produto Final Real");
		funcoes.put(3, "Consultar Produto Final Real");
		funcoes.put(4, "Remover Produto Final Real");

		funcoesPtr.put(0, ProdutoFinalRealGUI::sair);
		funcoesPtr.put(1, ProdutoFinalRealGUI::telaCadastrar);
		funcoesPtr.put(2, ProdutoFinalRealGUI::telaAlterar);
		funcoesPtr.put(3, ProdutoFinalRealGUI::telaConsultar);
		funcoesPtr.put(4, ProdutoFinalRealGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Produto Final Real =====");
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
		System.out.println("Saindo do Menu Produto Final Real");
	}
		
	public static void telaCadastrar(int a) {
		IProdutoFinalRealService produtoFinalRealService = ProdutoFinalRealService.getInstance();
		IMateriaPrimaRealService materiaPrimaRealService = MateriaPrimaRealService.getInstance();
		
		int id;
		int idExterno = 0;
		Date validade = new Date();
		int quantidade = 0;
		int aux = -1;
		int auxMateriaPrimaReal = 0;
		int idMateriaPrimaRealReceitaReal;
		float qtdMateriaPrimaRealReceitaReal = 0;
		int checadorDeContinuidade = 0;
		Boolean temMateriaPrimaRealCorrespondente;
		HashMap<Integer, Float> receitaReal = new HashMap<Integer, Float>();
		
		System.out.println("===== Cadastrar Produto Final =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o id do Produto Final genérico: ");
				idExterno = Integer.parseInt(input.nextLine());
				System.out.print("[Date] Entre com a validade: (mês/dia/ano)");
				validade = new Date(input.nextLine());
				System.out.print("[Int] Entre com a quantidade: ");
				quantidade = Integer.parseInt(input.nextLine());

				do {
					temMateriaPrimaRealCorrespondente = false;
					System.out.print("[Int] Entre com o id de uma Matéria Prima Real que compõe esse Produto Final Real: ");
					idMateriaPrimaRealReceitaReal = Integer.parseInt(input.nextLine());
					System.out.print("[Float] Entre com a quantidade dessa Matéria Prima Real na Receita Real: ");
					qtdMateriaPrimaRealReceitaReal = Float.parseFloat(input.nextLine());
					for(MateriaPrimaReal materiaPrimaReal : materiaPrimaRealService.procuraTodos()) {
						if(materiaPrimaReal.getId() == idMateriaPrimaRealReceitaReal) {
							temMateriaPrimaRealCorrespondente = true;
							receitaReal.put(idMateriaPrimaRealReceitaReal , qtdMateriaPrimaRealReceitaReal);
						}
					}
					
					if(temMateriaPrimaRealCorrespondente) {
						System.out.print("Deseja inserir outra Matéria Prima Real na Receita Real? \n[1] Sim \n[2] Não: \n");
						checadorDeContinuidade = Integer.parseInt(input.nextLine());
						if(checadorDeContinuidade == 1) {
							auxMateriaPrimaReal = 0;
						}
						else if (checadorDeContinuidade == 2) {
							auxMateriaPrimaReal = -1;
							aux = -1;
						}
						else {
							System.out.println("Você não digitou um valor válido, encerrando a inserção de matérias primas reais na receita real...\n");
							auxMateriaPrimaReal = -1;
							aux = -1;
						}
					}
					
					else {
						System.out.println("Matéria Prima Real inválida\n");
					}
				} while(auxMateriaPrimaReal != -1);
				
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		
		try {
			id = produtoFinalRealService.inserir(new ProdutoFinalReal(idExterno, validade, quantidade, receitaReal)); 
			System.out.println("Produto Final Real Cadastrado com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Produto Final Real não cadastrado pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
		
	}
	
	public static void telaAlterar(int a) {
		
	}
	
	public static void telaConsultar(int a) {
		
	}
	
	public static void telaRemover(int a) {
		
	}
}
