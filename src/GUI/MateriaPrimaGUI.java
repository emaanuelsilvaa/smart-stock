package GUI;


import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.IMateriaPrimaService;
import BUSINESS.MateriaPrimaService;
import ENTITY.MateriaPrima;
import UTIL.Colors;

public class MateriaPrimaGUI {
	IMateriaPrimaService materiaPrimaService;

	public  MateriaPrimaGUI() {
		// TODO Auto-generated constructor stub
		this.materiaPrimaService = MateriaPrimaService.getInstance();
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
			System.out.println(Colors.GREEN + "Matéria Prima cadastrada com o ID " + id + Colors.RESET);

		} catch (BusinessRuleException bre) {
			System.out
					.println(Colors.RED + "Matéria Prima não cadastrada pelo(s) seguinte(s) motivo(s):" + Colors.RESET);
			System.out.println(bre.getMessage());
		}

	}
	public static void telaAlterar(int a) {
		
	}
	public static void telaConsultar(int a) {
		
	}
	public static void telaRemover(int a) {
		
	}
	public static void sair(int a) {
		System.out.println("Saindo do Menu Matéria-Prima");
	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes= new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr= new HashMap<Integer, Consumer<Integer>>();
			
		Scanner input = new Scanner(System.in);
		int opt = -1;
		
		funcoes.put(0, "voltar");
		funcoes.put(1, "Cadastrar Matéria-Prima");
		funcoes.put(2, "Alterar Matéria-Prima");
		funcoes.put(3, "Consultar Matéria-Prima");
		funcoes.put(4, "Remover Matéria-Prima");
		
		funcoesPtr.put(0, MateriaPrimaGUI::sair);
		funcoesPtr.put(1, MateriaPrimaGUI::telaCadastrar);
		funcoesPtr.put(2, MateriaPrimaGUI::telaAlterar);
		funcoesPtr.put(3, MateriaPrimaGUI::telaConsultar);
		funcoesPtr.put(4, MateriaPrimaGUI::telaRemover);
		
		
		while(opt != 0) {
			System.out.println("===== Menu Matéria-Prima =====");
			System.out.println("\nOperações disponíveis:\n");
			for(int i : funcoes.keySet()) {
				System.out.printf("[%d] %s \n", i, funcoes.get(i));
			}
			try {
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				funcoesPtr.get(opt).accept(1);
			} catch (Exception e){
				System.out.println("Digite um valor válido");
				opt = -1;
			}
			System.out.println("");
		}
	}

}
