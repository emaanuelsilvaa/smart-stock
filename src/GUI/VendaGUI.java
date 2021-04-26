package GUI;

import java.util.HashMap;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

import BUSINESS.IVendaService;
import BUSINESS.MateriaPrimaService;
import BUSINESS.VendaService;
import BUSINESS.IProdutoFinalService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.ValidarEspecificidadeVendaRemedio;
import BUSINESS.IClienteService;
import BUSINESS.IMateriaPrimaService;
import BUSINESS.CalcularFreteRemedio;
import BUSINESS.ClienteService;

import ENTITY.Venda;
import UTIL.BusinessRuleException;
import ENTITY.Especificidade;
import ENTITY.EspecificidadeAlimento;
import ENTITY.EspecificidadeRemedio;
import ENTITY.FreteRemedio;
import ENTITY.ProdutoFinal;
import ENTITY.Remedio;


public class VendaGUI {
	
	protected static IVendaService vendaService;
	protected static IProdutoFinalService produtoFinalService;
	protected static IClienteService clienteService;
	
	public VendaGUI () {
		 vendaService = VendaService.getInstance(new ValidarEspecificidadeVendaRemedio(), new CalcularFreteRemedio(), new FreteRemedio());
		 produtoFinalService = ProdutoFinalService.getInstance();
		 clienteService = ClienteService.getInstance();
	}
	
	public static void init(int a) {
		new VendaGUI();
		telaInicial(1);
	}
	
	private static void mostraTodosOSProdutosFinais() {
		ArrayList<ProdutoFinal> listaDeProdutosFinais = produtoFinalService.procuraTodos();
		if(listaDeProdutosFinais.isEmpty()) {
			System.out.println("Nenhum Remédio Cadastrado\n");
		}
		else {
			System.out.println("Remédios Cadastrados: ");
			for(ProdutoFinal produtoFinal : listaDeProdutosFinais) {
				System.out.printf("[%d] %s ", produtoFinal.getId(), produtoFinal.getNome());
			}
			System.out.println("");
		}
		
	}

	public static void telaCadastrar(int a) {
		int id = 0;
		int aux = 0;
		int aux2 = 0;
		int idCliente = 0;
		int idProduto = 0;
		int qtdProduto = 0;
		int checadorDeContinuidade = 0;
		boolean necessitaReceita = false;
		String crm = new String();
		Date data = new Date();
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
					System.out.print("Dentre os remédios listados acima, selecione o ID de um produto: ");
					idProduto = Integer.parseInt(input.nextLine());
					System.out.print("Agora, selecione a quantidade desse remédio na compra: ");
					qtdProduto = Integer.parseInt(input.nextLine());
					
					if(!listaDeProdutos.containsKey(idProduto)) {
						listaDeProdutos.put(idProduto, qtdProduto);
						if (!((Remedio) produtoFinalService.procuraPeloId(idProduto)).getTarja().equals("sem tarja") 
								&& !((Remedio) produtoFinalService.procuraPeloId(idProduto)).getTarja().equals("amarela")) {
							necessitaReceita = true;
						}
						System.out.print("Deseja inserir outro remédio na compra? [1 - sim] [2 - não]: ");
						checadorDeContinuidade = Integer.parseInt(input.nextLine());
						System.out.println();
						if(checadorDeContinuidade == 1) {
							aux2=0;
						}
						else if (checadorDeContinuidade == 2) {
							aux2 = -1;
						}
						else {
							System.out.println("Você não digitou um valor válido, encerrando a inserção de remédios na compra...\n");
							aux2 = -1;
						}
					}
					else {
						System.out.println("Você digitou o ID de um remédio que já constava na compra, encerrando a inserção de remédios na compra...\n");
						aux2 = -1;
					}
				}while(aux2 != -1);
				if (necessitaReceita) {
					System.out.print("[String] Entre com o CRM do médico da receita: ");
					crm = input.nextLine();
					System.out.print("[dd/mm/aaaa] Entre com a data de validade da receita: ");
					data = new SimpleDateFormat("dd/MM/yyyy").parse(input.nextLine());
				}
				aux = -1;
			} catch (Exception e) {
				System.out.println("\nErro de parâmetros, digite novamente seguindo os tipos\n");
				aux = 0;
			}

		}while (aux != -1);
		try {
			Especificidade especificidade;
			if (necessitaReceita) {
				especificidade = new EspecificidadeRemedio(crm, data);
			} else {
				especificidade = new EspecificidadeRemedio();
			}
			id = vendaService.realizarVenda(listaDeProdutos, idCliente, especificidade);
			System.out.println("Venda Realizada com o ID " + id + " e preço: " + vendaService.procuraPeloId(id).getValor()+" frete: "+ vendaService.procuraPeloId(id).getFrete());
		} catch (BusinessRuleException bre) {
			System.out.println("Venda não realizada pelo(s) seguinte(s) motivo(s): ");
			System.out.println(bre.getMessage());
		}
	
	}
	
	private static void mostraTodasAsVendas() {		
		ArrayList<Venda> listaDeVendas = vendaService.procuraTodos();
		if(listaDeVendas.isEmpty()) {
			System.out.println("Nenhuma Venda realizada\n");
		}
		else {
			System.out.println("Vendas Cadastradas: \n");
			for(Venda venda : listaDeVendas) {
				System.out.printf("[%d] Cliente: %s / Valor: %.2f\n", venda.getId(), clienteService.procuraPeloId(venda.getIdCliente()).getNome(), venda.getValor());
			}
		}
		
	}
	
	private static void mostraVendaDetalhada(int id) {
		Venda venda = vendaService.procuraPeloId(id);
		
		if(venda == null) {
			System.out.println("Venda não encontrada\n");
		}
		
		else {
			System.out.printf("\nId: %d\n", venda.getId());
			System.out.printf("Cliente: %s\n", clienteService.procuraPeloId(venda.getIdCliente()).getNome());
			System.out.printf("Valor: %.2f\n", venda.getValor());
			System.out.println("Data: " + venda.getData());
			System.out.println("Frete: " + venda.getFrete());
			System.out.printf("Remédios inclusos na venda: \n");
			for(int produtoID : venda.getListaProdutos().keySet()) {
			
				System.out.println("Remedio = [" + produtoFinalService.procuraPeloId(produtoID).getNome() 
								  + "] Quantidade = [" + venda.getListaProdutos().get(produtoID)+ "]");
			}
			System.out.println("Frete: " +  venda.getFrete());
		}
	}
	
	public static void telaConsultar(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Consultar Venda ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Vendas realizadas");
			System.out.printf("[%d] %s \n", 2, "Ver uma Venda Detalhadamente");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostraTodasAsVendas();
					break;
				case 2:
					System.out.print("Digite o id da Venda: ");
					opt2 = Integer.parseInt(input.nextLine());
					mostraVendaDetalhada(opt2);
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
	
	
	public static void removerVenda(int id) {
		try{
			vendaService.remover(id);
			System.out.println("Venda de Id: "+ id + " foi removida");
		} catch (BusinessRuleException bre) {
			System.out.println(bre.getMessage());
		}
	}
	public static void telaRemover(int a) {
		int opt = -1;
		int opt2 = -1;

		do {
			System.out.println("\n ===== Remover Venda ===== \n");
			System.out.printf("[%d] %s \n", 0, "Voltar");
			System.out.printf("[%d] %s \n", 1, "Ver Vendas Realizadas");
			System.out.printf("[%d] %s \n", 2, "Remover Venda");
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Digite: ");
				opt = Integer.parseInt(input.nextLine());
				switch (opt) {
				case 0:
					break;
				case 1:
					mostraTodasAsVendas();
					break;
				case 2:
					System.out.print("Digite o id da Venda: ");
					opt2 = Integer.parseInt(input.nextLine());
					removerVenda(opt2);
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
