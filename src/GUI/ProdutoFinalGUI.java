package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.IProdutoFinalService;
import BUSINESS.IMateriaPrimaService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.MateriaPrimaService;
import ENTITY.MateriaPrima;
import ENTITY.ProdutoFinal;
import UTIL.BusinessRuleException;
import UTIL.Colors;

public class ProdutoFinalGUI {
	
	//IProdutoFinalService produtoFinalService;
	
	public ProdutoFinalGUI () {
		//this.produtoFinalService = ProdutoFinalService.getInstance();
	}
	
	public static void init(int a) {
		new ProdutoFinalGUI();
		telaInicial(1);
	}
	
	public static void telaCadastrar (int a) {
		IProdutoFinalService produtoFinalService = ProdutoFinalService.getInstance();
		IMateriaPrimaService materiaPrimaService = MateriaPrimaService.getInstance();
		
		int id = 0;
		String nome = new String ();
		float preco = 0;
		int qtdMinima = 0;
		int aux = -1;
		int auxMateriaPrima = 0;
		String nomeMateriaPrimaDaReceita = new String ();
		float qtdMateriaPrimaDaReceita = 0;
		int checadorDeContinuidade = 0;
		Boolean temMateriaPrimaCorrespondente = false;
		ArrayList <MateriaPrima> listaDeMateriasPrimas = new ArrayList <MateriaPrima> (); //Todas as matérias primas no estoque
		HashMap <Integer, Float> receita = new HashMap <Integer, Float> ();
		listaDeMateriasPrimas = materiaPrimaService.procuraTodos();
		
		System.out.println("===== Cadastrar Produto Final =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[String] Entre com o nome: ");
				nome = input.nextLine();
				System.out.print("[Float] Entre com o preço: ");
				preco = Float.parseFloat(input.nextLine());
				System.out.print("[Int] Entre com a quantidade mínima: ");
				qtdMinima = Integer.parseInt(input.nextLine());

				do {
					temMateriaPrimaCorrespondente = false;
					System.out.print("[String] Entre com o nome de uma Matéria Prima que compõe esse Produto Final: ");
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
			id = produtoFinalService.inserir(new ProdutoFinal(nome, preco, qtdMinima, receita)); 
			System.out.println("Produto Final Cadastrado com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Produto Final não cadastrado pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
	}
	
	public static void telaAlterar (int a) {
		
	}
	
	public static void telaConsultar (int a) {
		
	}
	
	public static void telaRemover (int id) {
		
	}
	
	public static void sair(int a) {
		System.out.println("Saindo do Menu Produto Final");

	}
	
	
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Cadastrar Produto Final");
		funcoes.put(2, "Alterar Produto Final");
		funcoes.put(3, "Consultar Produto Final");
		funcoes.put(4, "Remover Produto Final");

		funcoesPtr.put(0, ProdutoFinalGUI::sair);
		funcoesPtr.put(1, ProdutoFinalGUI::telaCadastrar);
		funcoesPtr.put(2, ProdutoFinalGUI::telaAlterar);
		funcoesPtr.put(3, ProdutoFinalGUI::telaConsultar);
		funcoesPtr.put(4, ProdutoFinalGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Produto Final =====");
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
