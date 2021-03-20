package GUI;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.AnaliseLucro;
import BUSINESS.IAnaliseLucro;

public class AnaliseLucroGUI {
	static IAnaliseLucro analiseLucroService;
	
	public AnaliseLucroGUI() {
		analiseLucroService = AnaliseLucro.getInstance();
	}
	
	public static void init(int a) {
		new AnaliseLucroGUI();
		telaInicial(1);
	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Consultar Análise de Lucro");

		funcoesPtr.put(0, AnaliseLucroGUI::sair);
		funcoesPtr.put(1, AnaliseLucroGUI::mostrarAnaliseLucro);

		while (opt != 0) {
			System.out.println("===== Menu Análise de Lucro =====");
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
		System.out.println("Saindo do Menu Análise de Lucro");
	}
	
	public static void mostrarAnaliseLucro(int a) {
		Date dataInicio = new Date();
		Date dataFim = new Date();
		int aux = -1;
		float lucro;
		System.out.println("===== Análise de Lucro =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[String] Entre com a data inicial: (mês/dia/ano)");
				dataInicio = new Date(input.nextLine());
				System.out.print("[String] Entre com a data final: (mês/dia/ano)");
				dataFim = new Date(input.nextLine());
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		
		lucro = analiseLucroService.analisarLucro(dataInicio, dataFim);
		if(lucro == 0) {	
			System.out.println("Lucro nulo nas vendas entre as datas [" + dataInicio +
					"] e [" + dataFim + "]");
		} else if(lucro < 0) {
			System.out.println("Prejuízo de R$"+ (lucro*-1) +" nas vendas entre as datas [" + dataInicio +
					"] e [" + dataFim + "]");
		}
		else {
			System.out.println("Lucro de R$"+ lucro +" nas vendas entre as datas [" + dataInicio +
					"] e [" + dataFim + "]");
		}
	}
	
}
