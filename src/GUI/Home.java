package GUI;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class Home {

	public Home() {
		// TODO Auto-generated constructor stub
	}

	public static void telaDespedida(int a) {
		System.out.println("Obrigado por usar SmartFarmácia, até logo!");
	}

	public static void telaInicial(int a) {

		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		Scanner input = new Scanner(System.in);
		int opt = -1;

		funcoes.put(0, "sair");
		funcoes.put(1, "Gerenciar Clientes");
		funcoes.put(2, "Gerenciar Fornecedores");
		funcoes.put(3, "Gerenciar Matéria-Prima");
		funcoes.put(4, "Gerenciar Matéria-Prima Real");
		funcoes.put(5, "Gerenciar Remédios");
		funcoes.put(6, "Gerenciar Remédios em Estoque");
		funcoes.put(7, "Gerenciar Venda");
		funcoes.put(8, "Gerenciar Encomenda");
		funcoes.put(9, "Analisar Provisionamento");
		funcoes.put(10, "Analisar Lucro");

		funcoesPtr.put(0, Home::telaDespedida);
		funcoesPtr.put(1, ClienteGUI::init);
		funcoesPtr.put(2, FornecedorGUI::init);
		funcoesPtr.put(3, MateriaPrimaGUI::init);
		funcoesPtr.put(4, MateriaPrimaRealGUI::init);
		funcoesPtr.put(5, ProdutoFinalGUI::init);
		funcoesPtr.put(6, ProdutoFinalRealGUI::init);
		funcoesPtr.put(7, VendaGUI::init);
		funcoesPtr.put(8, EncomendaGUI::init);
		funcoesPtr.put(9, ReposicaoGUI::init);
		funcoesPtr.put(10, AnaliseLucroGUI::init);
		 do{
			System.out.println("===== Menu Principal =====");
			System.out.println("\nOperações disponíveis:\n");
			for (int i : funcoes.keySet()) {
				System.out.printf("[%d] %s \n", i, funcoes.get(i));
			}
			try {
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				System.out.println("");
				funcoesPtr.get(opt).accept(1);
			} catch (Exception e) {
				System.out.println("Digite um valor válido");
				opt = -1;

			}
			System.out.println("");
		} while (opt!=0);
	}

}
