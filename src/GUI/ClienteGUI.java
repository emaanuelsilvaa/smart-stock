package GUI;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.ClienteService;
import BUSINESS.IClienteService;
import ENTITY.Cliente;
import UTIL.BusinessRuleException;
import UTIL.Colors;

public class ClienteGUI {
	
	static IClienteService clienteService;
	
	public ClienteGUI() {
		// TODO Auto-generated constructor stub
		clienteService = ClienteService.getInstance();
	}

	public static void init(int a) {
		new ClienteGUI();
		telaInicial(1);
	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Cadastrar Cliente");
		funcoes.put(2, "Alterar Cliente");
		funcoes.put(3, "Consultar Cliente");
		funcoes.put(4, "Remover Cliente");

		funcoesPtr.put(0, ClienteGUI::sair);
		funcoesPtr.put(1, ClienteGUI::telaCadastrar);
		funcoesPtr.put(2, ClienteGUI::telaAlterar);
		funcoesPtr.put(3, ClienteGUI::telaConsultar);
		funcoesPtr.put(4, ClienteGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Cliente =====");
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
		System.out.println("Saindo do Menu Cliente");
	}
	
	public static void telaCadastrar(int a) {
		String nome = "";
		String cpf = "";
		String endereço = "";
		String telefone = "";
		int aux = -1;
		int id;
		System.out.println("===== Cadastrar Cliente =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[String] Entre com o nome: ");
				nome = input.nextLine();
				System.out.print("[String] Entre com o cpf: ");
				cpf = input.nextLine();
				System.out.print("[String] Entre com o endereço: ");
				endereço = input.nextLine();
				System.out.print("[String] Entre com o telefone: ");
				telefone = input.nextLine();
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		try {
			id = clienteService.inserir(new Cliente(nome, cpf, endereço, telefone));
			System.out.println(Colors.GREEN + "Cliente cadastrado com o ID " + id + Colors.RESET);

		} catch (BusinessRuleException bre) {
			System.out.println(Colors.RED + "Cliente não cadastrado pelo(s) seguinte(s) motivo(s):" + Colors.RESET);
			System.out.println(bre.getMessage());
		}
	}
	
	public static void telaAlterar(int a) {
		String nome = "";
		String cpf = "";
		String endereço = "";
		String telefone = "";
		int aux = -1;
		int id;
		int idASubstituir = 0;
		System.out.println("===== Alterar Cliente =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o ID do Cliente a ser alterado: ");
				idASubstituir = Integer.parseInt(input.nextLine());
				System.out.print("[String] Entre com o nome: ");
				nome = input.nextLine();
				System.out.print("[String] Entre com o cpf: ");
				cpf = input.nextLine();
				System.out.print("[String] Entre com o endereço: ");
				endereço = input.nextLine();
				System.out.print("[String] Entre com o telefone: ");
				telefone = input.nextLine();
				aux = 0;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = -1;
			}
		} while (aux != 0);
		try {
			id = clienteService.alterar(idASubstituir, new Cliente(nome, cpf, endereço, telefone));
			System.out.println(Colors.GREEN + "Cliente alterado com o ID " + id + Colors.RESET);

		} catch (BusinessRuleException bre) {
			System.out.println(Colors.RED + "Cliente não alterado pelo(s) seguinte(s) motivo(s):" + Colors.RESET);
			System.out.println(bre.getMessage());
		}
	}
	
	public static void telaConsultar(int a) {
		
	}
	
	public static void telaRemover(int a) {
		
	}

}
