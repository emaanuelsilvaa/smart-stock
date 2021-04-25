package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.IProdutoFinalService;
import BUSINESS.FornecedorService;
import BUSINESS.IFornecedorService;
import BUSINESS.IMateriaPrimaService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.ValidarAlimento;
import ENTITY.Alimento;
import BUSINESS.MateriaPrimaService;
import ENTITY.Fornecedor;
import ENTITY.MateriaPrima;
import ENTITY.ProdutoFinal;
import ENTITY.Remedio;
import UTIL.BusinessRuleException;
import UTIL.Colors;

public class ProdutoFinalGUI {
	
	protected static IProdutoFinalService produtoFinalService;
	protected static IMateriaPrimaService materiaPrimaService;
	
	public ProdutoFinalGUI () {
		produtoFinalService = ProdutoFinalService.getInstance(new ValidarAlimento());
		materiaPrimaService = MateriaPrimaService.getInstance();
		
	}
	
	public static void init(int a) {
		new ProdutoFinalGUI();
		telaInicial(1);
	}
	
	public static void telaCadastrar (int a) {		
		int id = 0;
		String nome = new String ();
		float preco = 0;
		int qtdMinima = 0;
		float calorias = 0;
		int aux = -1;
		int auxMateriaPrima = 0;
		String nomeMateriaPrimaDaReceita = new String ();
		float qtdMateriaPrimaDaReceita = 0;
		int checadorDeContinuidade = 0;
		Boolean temMateriaPrimaCorrespondente = false;
		ArrayList <MateriaPrima> listaDeMateriasPrimas = new ArrayList <MateriaPrima> (); //Todas as matérias primas no estoque
		HashMap <Integer, Float> receita = new HashMap <Integer, Float> ();
		listaDeMateriasPrimas = materiaPrimaService.procuraTodos();
		
		System.out.println("===== Cadastrar Alimento =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[String] Entre com o nome: ");
				nome = input.nextLine();
				System.out.print("[Float] Entre com o preço: ");
				preco = Float.parseFloat(input.nextLine());
				System.out.print("[Int] Entre com a quantidade mínima: ");
				qtdMinima = Integer.parseInt(input.nextLine());
				System.out.print("[Float] Entre com as calorias: ");
				calorias = Float.parseFloat(input.nextLine());

				do {
					temMateriaPrimaCorrespondente = false;
					System.out.print("[String] Entre com o nome de uma Matéria Prima que compõe esse Alimento: ");
					nomeMateriaPrimaDaReceita = (input.nextLine());
					System.out.print("[Float] Entre com a quantidade dessa Matéria Prima na Receita: ");
					qtdMateriaPrimaDaReceita = Float.parseFloat(input.nextLine());
					for(MateriaPrima materiaPrima : listaDeMateriasPrimas) {
						if(materiaPrima.getNome().equalsIgnoreCase(nomeMateriaPrimaDaReceita)) {
							temMateriaPrimaCorrespondente = true;
							receita.put(materiaPrima.getId(), qtdMateriaPrimaDaReceita);
						}
					}
					
					if(temMateriaPrimaCorrespondente) {
						System.out.print("Deseja inserir outra Matéria Prima na Receita? [1 - sim] [2 - não]: ");
						checadorDeContinuidade = Integer.parseInt(input.nextLine());
						if(checadorDeContinuidade == 1) {
							auxMateriaPrima=0;
						}
						else if (checadorDeContinuidade == 2) {
							auxMateriaPrima = -1;
							aux = -1;
						}
						else {
							System.out.println("Você não digitou um valor válido, encerrando a inserção de matérias primas na receita...\n");
							auxMateriaPrima = -1;
							aux = -1;
						}
					}
					
					else {
						System.out.println("Matéria Prima inválida\n");
					}
				}while(auxMateriaPrima != -1);
				
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		try {
			id = produtoFinalService.inserir(new Alimento(nome, preco, qtdMinima, receita, calorias)); 
			System.out.println("Alimento Cadastrado com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Alimento não cadastrado pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
	}
	
	private static void mostraTodosOSProdutosFinais() {
		ArrayList<ProdutoFinal> listaDeProdutosFinais = produtoFinalService.procuraTodos();
		if(listaDeProdutosFinais.isEmpty()) {
			System.out.println("Nenhum Alimento Cadastrado\n");
		}
		else {
			System.out.println("Alimentos Cadastrados: \n");
			for(ProdutoFinal produtoFinal : listaDeProdutosFinais) {
				System.out.printf("[%d] %s \n", produtoFinal.getId(), produtoFinal.getNome());
			}
		}
		
	}
	
	private static void mostraProdutoFinalDetalhado(int id) {
		ProdutoFinal produtoFinal = (Alimento) produtoFinalService.procuraPeloId(id);
		if(produtoFinal == null) {
			System.out.println("Alimento não encontrado\n");
		}
		
		else {
			System.out.printf("\nId: %d\n", produtoFinal.getId());
			System.out.printf("Nome: %s\n", produtoFinal.getNome());
			System.out.printf("Preço: %.2f\n", produtoFinal.getPreco());
			System.out.printf("Quantidade Mínima: %d\n", produtoFinal.getQntMinima());
			System.out.printf("Calorias: %.2f\n", ((Alimento) produtoFinal).getCalorias());
			System.out.printf("Receita referente a esse produtoFinal: \n");
			for(int materiaPrimaID : produtoFinal.getReceita().keySet()) {
			
				System.out.println("Matéria Prima = [" + materiaPrimaService.procuraPeloId(materiaPrimaID).getNome() 
								  + "] Quantidade = [" + produtoFinal.getReceita().get(materiaPrimaID)+ "]");
			}
		}
	}
	
	public static void telaAlterar (int a) {		
		int id = 0;
		int idASubstituir = 0;
		String nome = new String ();
		float preco = 0;
		int qtdMinima = 0;
		float calorias = 0;
		int aux = -1;
		int auxMateriaPrima = 0;
		String nomeMateriaPrimaDaReceita = new String ();
		float qtdMateriaPrimaDaReceita = 0;
		int checadorDeContinuidade = 0;
		Boolean temMateriaPrimaCorrespondente = false;
		ArrayList <MateriaPrima> listaDeMateriasPrimas = new ArrayList <MateriaPrima> (); //Todas as matérias primas no estoque
		HashMap <Integer, Float> receita = new HashMap <Integer, Float> ();
		listaDeMateriasPrimas = materiaPrimaService.procuraTodos();
		
		System.out.println("===== Alterar Alimento =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o Id do Alimento a ser alterado: ");
				idASubstituir = Integer.parseInt(input.nextLine());
				System.out.print("[String] Entre com o nome: ");
				nome = input.nextLine();
				System.out.print("[Float] Entre com o preço: ");
				preco = Float.parseFloat(input.nextLine());
				System.out.print("[Int] Entre com a quantidade mínima: ");
				qtdMinima = Integer.parseInt(input.nextLine());
				System.out.print("[Float] Entre com as calorias: ");
				calorias = Float.parseFloat(input.nextLine());
				
				do {
					temMateriaPrimaCorrespondente = false;
					System.out.print("[String] Entre com o nome de uma Matéria Prima que compõe esse Alimento: ");
					nomeMateriaPrimaDaReceita = (input.nextLine());
					System.out.print("[Float] Entre com a quantidade dessa Matéria Prima na Receita: ");
					qtdMateriaPrimaDaReceita = Float.parseFloat(input.nextLine());
					for(MateriaPrima materiaPrima : listaDeMateriasPrimas) {
						if(materiaPrima.getNome().equalsIgnoreCase(nomeMateriaPrimaDaReceita)) {
							temMateriaPrimaCorrespondente = true;
							receita.put(materiaPrima.getId(), qtdMateriaPrimaDaReceita);
						}
					}
					
					if(temMateriaPrimaCorrespondente) {
						System.out.print("Deseja inserir outra Matéria Prima na Receita? [1 - sim] [2 - não]: ");
						checadorDeContinuidade = Integer.parseInt(input.nextLine());
						if(checadorDeContinuidade == 1) {
							auxMateriaPrima=0;
						}
						else if (checadorDeContinuidade == 2) {
							auxMateriaPrima = -1;
							aux = -1;
						}
						else {
							System.out.println("Você não digitou um valor válido, encerrando a inserção de matérias primas na receita...\n");
							auxMateriaPrima = -1;
							aux = -1;
						}
					}
					
					else {
						System.out.println("Matéria Prima inválida\n");
					}
				}while(auxMateriaPrima != -1);
				
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		try {
			id = produtoFinalService.alterar(idASubstituir, new Alimento(nome, preco, qtdMinima, receita, calorias)); 
			System.out.println("Alimento alterado com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Alimento não alterado pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
	}
	
	public static void telaConsultar (int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Consultar Alimento ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Alimentos cadastrados");
			System.out.printf("[%d] %s \n", 2, "Ver um Alimento Detalhadamente");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostraTodosOSProdutosFinais();
					break;
				case 2:
					System.out.print("Digite o id do Alimento: ");
					opt2 = Integer.parseInt(input.nextLine());
					mostraProdutoFinalDetalhado(opt2);
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
	
	public static void removerProdutoFinal(int id) {
		try{
			produtoFinalService.remover(id);
			System.out.println("Alimento de Id: "+ id + " foi removido");
		} catch (BusinessRuleException bre) {
			System.out.println(bre.getMessage());
		}
	}
	
	public static void telaRemover (int id) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Remover Alimento ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Alimentos cadastrados");
			System.out.printf("[%d] %s \n", 2, "Remover Alimento");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostraTodosOSProdutosFinais();
					break;
				case 2:
					System.out.print("Digite o id do Alimento: ");
					opt2 = Integer.parseInt(input.nextLine());
					removerProdutoFinal(opt2);
					break;
				default:
					throw new Exception("Valor Inválido");

				}
			} catch (Exception bre) {
				System.out.println("Digite um valor válido");
				bre.printStackTrace();
			}
		} while (opt != 0);
	}
	
	public static void sair(int a) {
		System.out.println("Saindo do Menu Alimento");

	}
	
	
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Cadastrar Alimento");
		funcoes.put(2, "Alterar Alimento");
		funcoes.put(3, "Consultar Alimento");
		funcoes.put(4, "Remover Alimento");

		funcoesPtr.put(0, ProdutoFinalGUI::sair);
		funcoesPtr.put(1, ProdutoFinalGUI::telaCadastrar);
		funcoesPtr.put(2, ProdutoFinalGUI::telaAlterar);
		funcoesPtr.put(3, ProdutoFinalGUI::telaConsultar);
		funcoesPtr.put(4, ProdutoFinalGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Alimento =====");
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
