package GUI;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.Date;
import java.text.SimpleDateFormat;

import BUSINESS.IVendaService;
import BUSINESS.MateriaPrimaService;
import BUSINESS.VendaService;
import BUSINESS.IProdutoFinalService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.IClienteService;
import BUSINESS.IMateriaPrimaService;
import BUSINESS.ClienteService;
import BUSINESS.IEncomendaService;
import BUSINESS.EncomendaService;

import ENTITY.Venda;
import ENTITY.Encomenda;
import UTIL.BusinessRuleException;
import ENTITY.ProdutoFinal;

public class EncomendaGUI {
	
	public EncomendaGUI () {
		
	}
	
	public static void init(int a) {
		new EncomendaGUI();
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
		IEncomendaService encomendaService = EncomendaService.getInstance();
		IProdutoFinalService produtoFinalService = ProdutoFinalService.getInstance();
		IClienteService clienteService = ClienteService.getInstance();
		
		int id = 0;
		int aux = 0;
		int aux2 = 0;
		int idCliente = 0;
		int idProduto = 0;
		int qtdProduto = 0;
		int checadorDeContinuidade = 0;
		String dataASerConvertida = new String ();
		Date dataDeEntrega = new Date ();
		HashMap<Integer, Integer> listaDeProdutos = new HashMap<Integer, Integer> ();
		//ArrayList <ProdutoFinal> listaDeProdutosFinaisDisponiveis = produtoFinalService.procuraTodos();
		
		System.out.println("===== Realizar Encomenda =====");
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("[Int] Entre com o id do cliente da encomenda: ");
				idCliente = Integer.parseInt(input.nextLine());
				System.out.print("[dd/mm/aaaa] Entre a data de entrega da encomenda: ");
				dataASerConvertida = input.nextLine();
				dataDeEntrega = new SimpleDateFormat("dd/MM/yyyy").parse(dataASerConvertida);
				mostraTodosOSProdutosFinais();
				do {
					System.out.print("Dentre os produtos finais listados acima, selecione o ID de um produto: ");
					idProduto = Integer.parseInt(input.nextLine());
					System.out.print("Agora, selecione a quantidade desse produto na compra: ");
					qtdProduto = Integer.parseInt(input.nextLine());
					
					if(!listaDeProdutos.containsKey(idProduto)) {
						listaDeProdutos.put(idProduto, qtdProduto);
						System.out.print("Deseja inserir outro produto na compra? [1 - sim] [2 - não]: ");
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
		if(clienteService.procuraPeloId(idCliente) != null) {
			id = encomendaService.realizarEncomenda(listaDeProdutos, idCliente, dataDeEntrega);
			System.out.println("Encomenda Realizada com o ID " + id + " e preço: " + encomendaService.procuraPeloId(id).getValor() + 
							   " com entrega em: " + encomendaService.procuraPeloId(id).getDataEntrega());
		} 
		else
		{
			System.out.println("Cliente inválido\n");
		}
	
	}
	
	public static void telaAlterar(int a) {
		
	}
	
	private static void mostraTodasAsEncomendas() {
		IEncomendaService encomendaService = EncomendaService.getInstance();
		IClienteService clienteService = ClienteService.getInstance();
		
		ArrayList<Encomenda> listaDeEncomendas = encomendaService.procuraTodos();
		if(listaDeEncomendas.isEmpty()) {
			System.out.println("Nenhuma Encomenda realizada\n");
		}
		else {
			System.out.println("Encomendas Cadastradas: \n");
			for(Encomenda encomenda : listaDeEncomendas) {
				System.out.printf("[%d] Cliente: %s / Valor: %.2f\n", encomenda.getId(), clienteService.procuraPeloId(encomenda.getIdCliente()).getNome(), encomenda.getValor());
			}
		}
		
	}
	
	private static void mostraEncomendaDetalhada(int id) {
		IEncomendaService encomendaService = EncomendaService.getInstance();
		IClienteService clienteService = ClienteService.getInstance();
		IProdutoFinalService produtoFinalService = ProdutoFinalService.getInstance();
		Encomenda encomenda = encomendaService.procuraPeloId(id);
		
		if(encomenda == null) {
			System.out.println("Encomenda não encontrada\n");
		}
		
		else {
			System.out.printf("\nId: %d\n", encomenda.getId());
			System.out.printf("Cliente: %s\n", clienteService.procuraPeloId(encomenda.getIdCliente()).getNome());
			System.out.printf("Valor: %.2f\n", encomenda.getValor());
			System.out.println("Data de pedido: " + encomenda.getData());
			System.out.println("Data de entrega: " + encomenda.getDataEntrega());
			System.out.printf("Produtos inclusos na encomenda: \n");
			for(int produtoID : encomenda.getListaProdutos().keySet()) {
			
				System.out.println("Produto = [" + produtoFinalService.procuraPeloId(produtoID).getNome() 
								  + "] Quantidade = [" + encomenda.getListaProdutos().get(produtoID)+ "]");
			}
		}
	}
	
	public static void telaConsultar (int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Consultar Encomenda ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Encomendas realizadas");
			System.out.printf("[%d] %s \n", 2, "Ver uma Encomenda Detalhadamente");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostraTodasAsEncomendas();
					break;
				case 2:
					System.out.print("Digite o id da Encomenda: ");
					opt2 = Integer.parseInt(input.nextLine());
					mostraEncomendaDetalhada(opt2);
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
	
	public static void removerEncomenda(int id) {
		IEncomendaService encomendaService = EncomendaService.getInstance();
		try{
			encomendaService.remover(id);
			System.out.println("Encomenda de Id: "+ id + " foi removida");
		} catch (BusinessRuleException bre) {
			System.out.println(bre.getMessage());
		}
	}
	
	public static void telaRemover(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Remover Encomenda ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Encomendas Realizadas");
			System.out.printf("[%d] %s \n", 2, "Remover Encomenda");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostraTodasAsEncomendas();
					break;
				case 2:
					System.out.print("Digite o id da Encomenda: ");
					opt2 = Integer.parseInt(input.nextLine());
					removerEncomenda(opt2);
					break;
				default:
					throw new Exception("Valor Inválido");

				}
			} catch (Exception bre) {
				System.out.println("Digite um valor válido");
				bre.printStackTrace();
			}
		} while (opt != 0);
	}
	
	public static void sair(int a) {
		System.out.println("Saindo do Menu Encomenda");

	}
	
	public static void telaInicial(int a) {
		HashMap<Integer, String> funcoes = new HashMap<Integer, String>();
		HashMap<Integer, Consumer<Integer>> funcoesPtr = new HashMap<Integer, Consumer<Integer>>();

		int opt = -1;

		funcoes.put(0, "Voltar");
		funcoes.put(1, "Realizar Encomenda");
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
