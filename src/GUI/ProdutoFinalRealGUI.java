package GUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.IMateriaPrimaRealService;
import BUSINESS.IProdutoFinalRealService;
import BUSINESS.IProdutoFinalService;
import BUSINESS.MateriaPrimaRealService;
import BUSINESS.ProdutoFinalRealService;
import BUSINESS.ProdutoFinalService;
import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinalReal;
import UTIL.BusinessRuleException;

public class ProdutoFinalRealGUI {
	protected static IProdutoFinalRealService produtoFinalRealService;
	protected static IMateriaPrimaRealService materiaPrimaRealService;
	protected static IProdutoFinalService produtoFinalService;

	
	
	public ProdutoFinalRealGUI() {
		produtoFinalRealService = ProdutoFinalRealService.getInstance();
		materiaPrimaRealService = MateriaPrimaRealService.getInstance();
		produtoFinalService = ProdutoFinalService.getInstance();
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
		funcoes.put(1, "Cadastrar Alimento em Estoque");
		funcoes.put(2, "Alterar Alimento em Estoque");
		funcoes.put(3, "Consultar Alimento em Estoque");
		funcoes.put(4, "Remover Alimento em Estoque");

		funcoesPtr.put(0, ProdutoFinalRealGUI::sair);
		funcoesPtr.put(1, ProdutoFinalRealGUI::telaCadastrar);
		funcoesPtr.put(2, ProdutoFinalRealGUI::telaAlterar);
		funcoesPtr.put(3, ProdutoFinalRealGUI::telaConsultar);
		funcoesPtr.put(4, ProdutoFinalRealGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Alimento em Estoque =====");
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
		System.out.println("Saindo do Menu Alimento em Estoque");
	}
		
	public static void telaCadastrar(int a) {		
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
		
		System.out.println("===== Cadastrar Alimento em Estoque =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o id do Alimento genérico: ");
				idExterno = Integer.parseInt(input.nextLine());
				System.out.print("[Date] Entre com a validade: (mês/dia/ano)");
				validade = new Date(input.nextLine());
				System.out.print("[Int] Entre com a quantidade: ");
				quantidade = Integer.parseInt(input.nextLine());

				do {
					temMateriaPrimaRealCorrespondente = false;
					System.out.print("[Int] Entre com o id de uma Matéria Prima Real que compõe esse Alimento em Estoque: ");
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
			System.out.println("Alimento em Estoque Cadastrado com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Alimento em Estoque não cadastrado pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
		
	}
	
	public static void telaAlterar(int a) {
		int id;
		int idASubstituir = 0;
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
		
		System.out.println("===== Alterar Alimento em Estoque =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o Id do Alimento em Estoque a ser alterado: ");
				idASubstituir = Integer.parseInt(input.nextLine());
				System.out.print("[Int] Entre com o id do Alimento genérico: ");
				idExterno = Integer.parseInt(input.nextLine());
				System.out.print("[Date] Entre com a validade: (mês/dia/ano)");
				validade = new Date(input.nextLine());
				System.out.print("[Int] Entre com a quantidade: ");
				quantidade = Integer.parseInt(input.nextLine());

				do {
					temMateriaPrimaRealCorrespondente = false;
					System.out.print("[Int] Entre com o id de uma Matéria Prima Real que compõe esse Alimento em Estoque: ");
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
			id = produtoFinalRealService.alterar(idASubstituir, new ProdutoFinalReal(idExterno, validade, quantidade, receitaReal)); 
			System.out.println("Alimento em Estoque alterado com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Alimento em Estoque não alterado pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
	}
	
	public static void telaConsultar(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Consultar Alimento em Estoque ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Alimentos em Estoque cadastrados");
			System.out.printf("[%d] %s \n", 2, "Ver um Alimento em Estoque Detalhadamente");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostrarTodosProdutosFinaisReais();
					break;
				case 2:
					System.out.print("Digite o id do Alimento em Estoque: ");
					opt2 = Integer.parseInt(input.nextLine());
					mostrarProdutoFinalRealDetalhado(opt2);
					break;
				default:
					throw new Exception("Valor Inválido");

				}
			} catch (Exception e) {
				System.out.println("Digite um valor válido");
				e.printStackTrace();
			}
		} while (opt != 0);
	}
	
	public static void removerProdutoFinalReal(int id) {
		try{
			produtoFinalRealService.remover(id);
			System.out.println("Alimento em Estoque de Id: "+ id + " foi removido");
		} catch (BusinessRuleException bre) {
			System.out.println(bre.getMessage());
		}
	}
	
	public static void mostrarProdutoFinalRealDetalhado(int id) {
		ProdutoFinalReal produtoFinalReal = produtoFinalRealService.procuraPeloId(id);
		if(produtoFinalReal == null) {
			System.out.println("Alimento em Estoque não encontrado\n");
		}
		
		else {
			System.out.printf("\nId: %d\n", produtoFinalReal.getId());
			System.out.printf("Id Alimento Genérico: %s\n", produtoFinalReal.getIdExterno());
			System.out.printf("Validade: " + produtoFinalReal.getValidade() + "\n");
			System.out.printf("Quantidade: %d\n", produtoFinalReal.getQuantidade());
			System.out.printf("Receita real referente a esse Alimento em Estoque: \n");
			for(int materiaPrimaRealID : produtoFinalReal.getReceitaReal().keySet()) {
				System.out.println("ID Matéria Prima Real = [" + materiaPrimaRealID
								  + "] Quantidade = [" + produtoFinalReal.getReceitaReal().get(materiaPrimaRealID)+ "]");
			}
		}
	}
	
	public static void mostrarTodosProdutosFinaisReais() {
		ArrayList<ProdutoFinalReal> listaProdutosFinaisReais = produtoFinalRealService.procuraTodos();
		if(listaProdutosFinaisReais.isEmpty()) {
			System.out.println("Nenhum Alimento em Estoque Cadastrado\n");
		}
		else {
			System.out.println("Alimentos em Estoque Cadastrados: \n");
			for(ProdutoFinalReal produtoFinal : listaProdutosFinaisReais) {
				System.out.printf("[%d] %s: %d. Vence em %s \n", produtoFinal.getId(), produtoFinalService.procuraPeloId(produtoFinal.getIdExterno()).getNome(), produtoFinal.getQuantidade(), produtoFinal.getValidade());
			}
		}
	}
	
	public static void telaRemover(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Remover Alimento em Estoque ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Alimentos em Estoque cadastrados");
			System.out.printf("[%d] %s \n", 2, "Remover Alimento em Estoque");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostrarTodosProdutosFinaisReais();
					break;
				case 2:
					System.out.print("Digite o id do Alimento em Estoque: ");
					opt2 = Integer.parseInt(input.nextLine());
					removerProdutoFinalReal(opt2);
					break;
				default:
					throw new Exception("Valor Inválido");

				}
			} catch (Exception bre) {
				System.out.println("Digite um valor válido: ");
				bre.printStackTrace();
			}
		} while (opt != 0);
	}
}
