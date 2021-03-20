package GUI;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Consumer;

import BUSINESS.IVendaService;
import BUSINESS.MateriaPrimaService;
import BUSINESS.VendaService;
import BUSINESS.IProdutoFinalService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.IClienteService;
import BUSINESS.IMateriaPrimaService;
import BUSINESS.ClienteService;

import ENTITY.Venda;
import UTIL.BusinessRuleException;
import ENTITY.ProdutoFinal;

public class EncomendaGUI {
	
	public EncomendaGUI () {
		
	}
	
	public static void init(int a) {
		new EncomendaGUI();
		telaInicial(1);
	}
	
	public static void telaCadastrar(int a) {
		
	}
	
	public static void telaAlterar(int a) {
		
	}
	
	public static void telaConsultar (int a) {
		
	}
	
	public static void telaRemover(int a) {
		
	}
	
	public static void sair(int a) {
		System.out.println("Saindo do Menu Encomenda");

	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Realizar Encomendad");
		funcoes.put(2, "Consultar Encomenda");
		funcoes.put(3, "Remover Encomenda");

		funcoesPtr.put(0, EncomendaGUI::sair);
		funcoesPtr.put(1, EncomendaGUI::telaCadastrar);
		funcoesPtr.put(2, EncomendaGUI::telaConsultar);
		funcoesPtr.put(3, EncomendaGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Encomenda =====");
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
