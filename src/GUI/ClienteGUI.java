package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import BUSINESS.ClienteService;
import BUSINESS.IClienteService;
import DATA.IClienteDAO;
import ENTITY.Cliente;
import UTIL.BusinessRuleException;

public class ClienteGUI implements IClienteGUI {
	
	 IClienteService clienteService;
	 IClienteGUI clienteGUI;
	
	public ClienteGUI() {
		// TODO Auto-generated constructor stub
		clienteService = ClienteService.getInstance();
	}

	@Override
	public void init(int a) {
		clienteGUI = new ClienteGUI();
		new ClienteGUI();
		telaInicial(1);
	}
	
	@Override
	public void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Cadastrar Cliente");
		funcoes.put(2, "Alterar Cliente");
		funcoes.put(3, "Consultar Cliente");
		funcoes.put(4, "Remover Cliente");

		funcoesPtr.put(0, clienteGUI::sair);
		funcoesPtr.put(1, clienteGUI::telaCadastrar);
		funcoesPtr.put(2, clienteGUI::telaAlterar);
		funcoesPtr.put(3, clienteGUI::telaConsultar);
		funcoesPtr.put(4, clienteGUI::telaRemover);

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
	
	@Override
	public void sair(int a) {
		System.out.println("Saindo do Menu Cliente");
	}
	
	@Override
	public void telaCadastrar(int a) {
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
			System.out.println("Cliente cadastrado com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Cliente não cadastrado pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
	}
	
	@Override
	public void telaAlterar(int a) {
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
			System.out.println("Cliente alterado com o ID " + id);

		} catch (BusinessRuleException bre) {
			System.out.println("Cliente não alterado pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
	}
	
	@Override
	public void clientesCadastrados() {
		ArrayList<Cliente> clientes = clienteService.procuraTodos();
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado");
		} else {
			System.out.println("\nClientes cadastrados: \n");
			for (Cliente c : clientes) {
				System.out.printf("[%d] %s \n", c.getId(), c.getNome());
			}
		}
	}
	
	@Override
	public void clienteDetalhado(int id) {
		Cliente c = clienteService.procuraPeloId(id);
		if (c == null) {
			System.out.println("Cliente não encontrado");
		} else {
			System.out.printf("\nId: %d\n", c.getId());
			System.out.printf("Nome: %s\n", c.getNome());
			System.out.printf("CPF: %s\n", c.getCpf());
			System.out.printf("Endereço: %s\n", c.getEndereço());
			System.out.printf("Telefone: %s\n", c.getTelefone());
		}
	}
	
	@Override
	public void telaConsultar(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Consultar Cliente ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Clientes cadastrados");
			System.out.printf("[%d] %s \n", 2, "Ver Cliente detalhado");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					clientesCadastrados();
					break;
				case 2:
					System.out.print("Digite o id do cliente: ");
					opt2 = Integer.parseInt(input.nextLine());
					clienteDetalhado(opt2);
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
	
	@Override
	public void removerCliente(int id) {
		try{
			clienteService.remover(id);
			System.out.println("Cliente de Id: "+ id + " removido");
		} catch (BusinessRuleException e) {
			System.out.println("Cliente não removido pelo(s) seguinte(s) motivo(s):");
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void telaRemover(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Remover Cliente ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Clientes cadastrados");
			System.out.printf("[%d] %s \n", 2, "Remover Cliente");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					clientesCadastrados();
					break;
				case 2:
					System.out.print("Digite o id do cliente: ");
					opt2 = Integer.parseInt(input.nextLine());
					removerCliente(opt2);
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

}
