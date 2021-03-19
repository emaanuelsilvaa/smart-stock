package GUI;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Consumer;

import BUSINESS.IFornecedorService;
import BUSINESS.FornecedorService;
import BUSINESS.IMateriaPrimaService;
import BUSINESS.MateriaPrimaService;

import ENTITY.Fornecedor;
import ENTITY.MateriaPrima;
import UTIL.BusinessRuleException;
import UTIL.Colors;

public class FornecedorGUI {
	IFornecedorService fornecedorService;
	//IMateriaPrimaService materiaPrimaService;
	
	public FornecedorGUI () {
		//this.fornecedorService = FornecedorService.getInstance();
		//this.materiaPrimaService = MateriaPrimaService.getInstance();
	}
	
	public static void init(int a) {
		new FornecedorGUI();
		telaInicial(1);
	}
	
	
	
	// TODO [Leonandro] Refatorar (pelo menos quebrando em múltiplas funções para diminuir a complexidade da leitura)
	public static void telaCadastrar (int a) {
		
		IFornecedorService fornecedorService = FornecedorService.getInstance();
		IMateriaPrimaService materiaPrimaService = MateriaPrimaService.getInstance();
		int id = 0;
		int aux = 0;
		int auxMateriaPrima = 0;
		int checadorDeContinuidade = 0;
		Boolean temMateriaPrimaCorrespondente = false;
		String nome = new String();
		String cnpj = new String();
		String endereco = new String();
		String telefone = new String();
		String email = new String();
		ArrayList <String> listaDeMateriasPrimasInseridas = new ArrayList <String> (); // Nomes das matérias primas que vão ser inseridas no fornecedor
		ArrayList <MateriaPrima> listaDeMateriasPrimasASeremInseridas = new ArrayList <MateriaPrima> (); // Todas as matérias primas que vão ser inseridas no construtor do fornecedor
		ArrayList <MateriaPrima> listaDeMateriasPrimas = new ArrayList <MateriaPrima> (); //Todas as matérias primas no estoque
		listaDeMateriasPrimas = materiaPrimaService.procuraTodos();
		
		System.out.println("===== Cadastrar Fornecedor =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[String] Entre com o nome: ");
				nome = input.nextLine();
				System.out.print("[String] Entre com o cnpj: ");
				cnpj = input.nextLine();
				System.out.print("[String] Entre com o endereço ");
				endereco = input.nextLine();
				System.out.print("[String] Entre com o telefone: ");
				telefone = input.nextLine();
				System.out.print("[String] Entre com o email: ");
				email = input.nextLine();
				
				do {
					temMateriaPrimaCorrespondente = false;
					System.out.print("[String] Entre com o nome da  matéria prima que esse Fornecedor vende: ");
					listaDeMateriasPrimasInseridas.add(input.nextLine());
					for(MateriaPrima materiaPrima : listaDeMateriasPrimas) {
						if(materiaPrima.getNome().equalsIgnoreCase(listaDeMateriasPrimasInseridas.get(auxMateriaPrima))) {
							temMateriaPrimaCorrespondente = true;
							listaDeMateriasPrimasASeremInseridas.add(materiaPrima);
						}
					}
					
					if(temMateriaPrimaCorrespondente) {
						System.out.print("Deseja inserir outra matéria prima? [1 - sim] [2 - não]: ");
						checadorDeContinuidade = Integer.parseInt(input.nextLine());
						if(checadorDeContinuidade == 1) {
							auxMateriaPrima++;
						}
						else if (checadorDeContinuidade == 2) {
							auxMateriaPrima = -1;
							aux = -1;
						}
						else {
							System.out.println("Você não digitou um valor válido, encerrando a inserção de matéria prima...\n");
							auxMateriaPrima = -1;
							aux = -1;
						}
					}
					
					else {
						listaDeMateriasPrimasInseridas.remove(auxMateriaPrima);
						System.out.println("Matéria Prima inválida\n");
					}
					
				}while(auxMateriaPrima != -1);
				
				
				
			} catch(Exception e){
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
			}
			
		}while(aux != -1);
		
		try {
			id = fornecedorService.inserir(new Fornecedor(nome, cnpj, endereco, telefone, email, listaDeMateriasPrimasASeremInseridas));
			System.out.println("Fornecedor cadastrado com o ID " + id);
		}catch (BusinessRuleException bre) {
			System.out.println("Matéria Prima não cadastrada pelo(s) seguinte(s) motivo(s):");
			System.out.println(bre.getMessage());
		}
		
		
	}
	
	private static void mostraTodosOSFornecedores() {
		ArrayList<Fornecedor> listaDeFornecedores = new ArrayList <Fornecedor> ();
		IFornecedorService fornecedorService = FornecedorService.getInstance();
		listaDeFornecedores = fornecedorService.procuraTodos();
		if(listaDeFornecedores.isEmpty()) {
			System.out.println("Nenhum Fornecedor Cadastrado\n");
		}
		else {
			System.out.println("Fornecedores Cadastrados: \n");
			for(Fornecedor fornecedor : listaDeFornecedores) {
				System.out.printf("[%d] %s \n", fornecedor.getId(), fornecedor.getNome());
			}
		}
		
		
	}
	
	private static void mostraFornecedorDetalhado(int id) {
		IFornecedorService fornecedorService = FornecedorService.getInstance();
		Fornecedor fornecedor = fornecedorService.procuraPeloId(id);
		if(fornecedor == null) {
			System.out.println("Fornecedor não encontrado\n");
		}
		
		else {
			System.out.printf("\nId: %d\n", fornecedor.getId());
			System.out.printf("Nome: %s\n", fornecedor.getNome());
			System.out.printf("CNPJ: %s\n", fornecedor.getCnpj());
			System.out.printf("Endereço: %s\n", fornecedor.getEndereço());
			System.out.printf("Telefone: %s\n", fornecedor.getTelefone());
			System.out.printf("Email: %s\n", fornecedor.getEmail());
			System.out.printf("Matérias Primas que o Fornecedor dispõe: \n");
			for(MateriaPrima materiaPrima : fornecedor.getListaProdutos()) {
				System.out.println("[" + materiaPrima.getNome()+ "]");
			}
		}
	}
	
	public static void telaAlterar (int a) {
		
	}
	
	public static void telaConsultar (int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Consultar Fornecedor ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Fornecedores cadastrados");
			System.out.printf("[%d] %s \n", 2, "Ver um Fornecedor Detalhadamente");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostraTodosOSFornecedores();
					break;
				case 2:
					System.out.print("Digite o id do Fornecedor: ");
					opt2 = Integer.parseInt(input.nextLine());
					mostraFornecedorDetalhado(opt2);
					break;
				default:
					throw new Exception("Valor Inválido");

				}
			} catch (Exception e) {
				System.out.println(Colors.RED + "Digite um valor válido" + Colors.RESET);
				e.printStackTrace();
			}
		} while (opt != 0);

	}
	
	public static void telaRemover (int id) {
		
	}
	
	public static void sair(int a) {
		System.out.println("Saindo do Menu Fornecedor");

	}
	
	
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Cadastrar Fornecedor");
		funcoes.put(2, "Alterar Fornecedor");
		funcoes.put(3, "Consultar Fornecedor");
		funcoes.put(4, "Remover Fornecedor");

		funcoesPtr.put(0, FornecedorGUI::sair);
		funcoesPtr.put(1, FornecedorGUI::telaCadastrar);
		funcoesPtr.put(2, FornecedorGUI::telaAlterar);
		funcoesPtr.put(3, FornecedorGUI::telaConsultar);
		funcoesPtr.put(4, FornecedorGUI::telaRemover);

		while (opt != 0) {
			System.out.println("===== Menu Fornecedor =====");
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
