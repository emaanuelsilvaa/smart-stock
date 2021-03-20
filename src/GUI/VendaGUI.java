package GUI;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Consumer;

import BUSINESS.IVendaService;
import BUSINESS.VendaService;
import BUSINESS.IProdutoFinalService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.IClienteService;
import BUSINESS.ClienteService;

import ENTITY.Venda;
import UTIL.BusinessRuleException;
import ENTITY.ProdutoFinal;


public class VendaGUI {
	
	public VendaGUI () {
		
	}
	
	public static void init(int a) {
		new VendaGUI();
		telaInicial(1);
	}
	
	private static void mostraTodosOSProdutosFinais() {
		IProdutoFinalService produtoFinalService = ProdutoFinalService.getInstance();
		ArrayList<ProdutoFinal> listaDeProdutosFinais = produtoFinalService.procuraTodos();
		if(listaDeProdutosFinais.isEmpty()) {
			System.out.println("Nenhum Produto Final Cadastrado\n");
		}
		else {
			System.out.println("Produtos Finais Cadastrados: ");
			for(ProdutoFinal produtoFinal : listaDeProdutosFinais) {
				System.out.printf("[%d] %s ", produtoFinal.getId(), produtoFinal.getNome());
			}
			System.out.println("");
		}
		
	}

	public static void telaCadastrar(int a) {
		IVendaService vendaService = VendaService.getInstance();
		IProdutoFinalService produtoFinalService = ProdutoFinalService.getInstance();
		
		int id = 0;
		int aux = 0;
		int aux2 = 0;
		int idCliente = 0;
		int idProduto = 0;
		int qtdProduto = 0;
		int checadorDeContinuidade = 0;
		HashMap<Integer, Integer> listaDeProdutos = new HashMap<Integer, Integer> ();
		ArrayList <ProdutoFinal> listaDeProdutosFinaisDisponiveis = produtoFinalService.procuraTodos();
		
		System.out.println("===== Realizar Venda =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o id do cliente da compra: ");
				idCliente = Integer.parseInt(input.nextLine());
				mostraTodosOSProdutosFinais();
				do {
					System.out.print("Dentre os produtos finais listados acima, selecione o ID de um produto: ");
					idProduto = Integer.parseInt(input.nextLine());
					System.out.print("Agora, selecione a quantidade desse produto na compra: ");
					qtdProduto = Integer.parseInt(input.nextLine());
					
					if(!listaDeProdutos.containsKey(idProduto)) {
						listaDeProdutos.put(idProduto, qtdProduto);
						System.out.print("Deseja inserir outra Matéria Prima na Receita? [1 - sim] [2 - não]: ");
						checadorDeContinuidade = Integer.parseInt(input.nextLine());
						System.out.println();
						if(checadorDeContinuidade == 1) {
							aux2=0;
						}
						else if (checadorDeContinuidade == 2) {
							aux2 = -1;
						}
						else {
							System.out.println("Você não digitou um valor válido, encerrando a inserção de produtos na compra...\n");
							aux2 = -1;
						}
					}
					else {
						System.out.println("Você digitou o ID de um produto que já constava na compra, encerrando a inserção de produtos na compra...\n");
						aux2 = -1;
					}
					
				}while(aux2 != -1);
				
				aux = -1;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = 0;
			}

		}while (aux != -1);
		
		try {
			id = vendaService.realizarVenda(listaDeProdutos, idCliente);
			System.out.println("Venda Realizada com o ID " + id + " e preço: " + vendaService.procuraPeloId(id).getValor());
		} catch (BusinessRuleException bre) {
			System.out.println("Venda não realizada pelo(s) seguinte(s) motivo(s): ");
			System.out.println(bre.getMessage());
		}
	
	}
	
	private static void mostraTodosAsVendas() {
		IVendaService vendaService = VendaService.getInstance();
		IClienteService clienteService = ClienteService.getInstance();
		
		ArrayList<Venda> listaDeVendas = vendaService.procuraTodos();
		if(listaDeVendas.isEmpty()) {
			System.out.println("Nenhum Produto Final Cadastrado\n");
		}
		else {
			System.out.println("Vendas Cadastrados: \n");
			for(Venda venda : listaDeVendas) {
				System.out.printf("[%d] Cliente: %s / Valor: %.2f\n", venda.getId(), clienteService.procuraPeloId(venda.getIdCliente()), venda.getValor());
			}
		}
		
	}
	
	public static void telaConsultar(int a) {
		
	}
	
	public static void telaRemover(int a) {
		
	}
	
	public static void sair(int a) {
		System.out.println("Saindo do Menu Venda");

	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Realizar Venda");
		funcoes.put(2, "Consultar Venda");
		funcoes.put(3, "Remover Venda");

		funcoesPtr.put(0, VendaGUI::sair);
		funcoesPtr.put(1, VendaGUI::telaCadastrar);
		funcoesPtr.put(2, VendaGUI::telaConsultar);
		funcoesPtr.put(3, VendaGUI::telaRemover);

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
