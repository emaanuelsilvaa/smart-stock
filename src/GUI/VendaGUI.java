package GUI;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Consumer;

public class VendaGUI {
	public VendaGUI () {
		
	}
	
	public static void init(int a) {
		new VendaGUI();
		telaInicial(1);
	}
	
	public static void telaCadastrar(int a) {
		
	}
	
	public static void telaConsultar(int a) {
		
	}
	
	public static void telaRemover(int a) {
		
	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Realizar Venda");
		funcoes.put(2, "Consultar Venda");
		funcoes.put(3, "Remover Venda");

		funcoesPtr.put(0, FornecedorGUI::sair);
		funcoesPtr.put(1, FornecedorGUI::telaCadastrar);
		funcoesPtr.put(2, FornecedorGUI::telaConsultar);
		funcoesPtr.put(3, FornecedorGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Venda =====");
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
