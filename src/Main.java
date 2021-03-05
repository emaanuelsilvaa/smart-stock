import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import BUSINESS.ClienteService;
import BUSINESS.EncomendaService;
import BUSINESS.EstoqueService;
import DATA.ClienteDAO;
import BUSINESS.MateriaPrimaRealService;
import BUSINESS.MateriaPrimaService;
import BUSINESS.ProdutoFinalRealService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.RelatorioService;
import BUSINESS.VendaService;
import DATA.MateriaPrimaDAO;
import DATA.MateriaPrimaRealDAO;
import DATA.ProdutoFinalDAO;
import DATA.ProdutoFinalRealDAO;
import DATA.VendaDAO;
import ENTITY.Cliente;
import ENTITY.Encomenda;
import ENTITY.Fornecedor;
import ENTITY.MateriaPrima;
import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;
import ENTITY.Venda;
import BUSINESS.FornecedorService;
import BUSINESS.IClienteService;
import BUSINESS.IEncomendaService;
import BUSINESS.IEstoqueService;
import BUSINESS.IFornecedorService;
import BUSINESS.IMateriaPrimaRealService;
import BUSINESS.IMateriaPrimaService;
import BUSINESS.IProdutoFinalRealService;
import BUSINESS.IProdutoFinalService;
import BUSINESS.IVendaService;
import DATA.FornecedorDAO;
import BUSINESS.RelatorioService;
import BUSINESS.IRelatorioService;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		// Singleton
		IClienteService clienteService = ClienteService.getInstance();
		IFornecedorService fornecedorService = FornecedorService.getInstance();
		IMateriaPrimaService materiaPrimaService = MateriaPrimaService.getInstance();
		IMateriaPrimaRealService materiaPrimaRealService = MateriaPrimaRealService.getInstance();
		IProdutoFinalService produtoFinalService = ProdutoFinalService.getInstance();
		IProdutoFinalRealService produtoFinalRealService = ProdutoFinalRealService.getInstance();
		IVendaService vendaService = VendaService.getInstance();
		IEstoqueService estoqueService = EstoqueService.getInstance();
		IEncomendaService encomendaService = EncomendaService.getInstance();
		IRelatorioService relatorioService = RelatorioService.getInstance();

		System.out.print("//=== Bem-vindo ao SmartStock! ===//\n\n");

		// ==== Teste ClienteService ====//
		System.out.print("//==== Teste ClienteService ====//\n");

		System.out.print(">>> Criando o cliente 1...\n");
		Cliente cliente1 = new Cliente(1, "Maria", "123", "Rua Palmares, 180", "84-3332222");

		System.out.print(">>> Criando o cliente 2...\n");
		Cliente cliente2 = new Cliente(2, "João", "11122233344", "Rua dos Poetas, 255", "84-44445555");

		System.out.print(">>> Criando o cliente 3...\n");
		Cliente cliente3 = new Cliente(3, "Yago", "12538500460", "Rua Jacaranda, 255", "84-44445555");

		clienteService.inserir(cliente1);
		clienteService.inserir(cliente2);
		clienteService.inserir(cliente3);
		
		Cliente cliente2_1 = new Cliente(2, "João", "11122233344", "Rua dos Eucaliptos, 199", "84-44445555");
		clienteService.alterar(2, cliente2_1);

		ArrayList<Cliente> clientes = clienteService.procuraTodos();

		System.out.println("\n>>> Clientes cadastrados:");
		for (Cliente c : clientes) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Cliente ID=" + c.getId() + ": ");
			System.out.println("> Nome: " + c.getNome());
			System.out.println("> CPF: " + c.getCpf());
			System.out.println("> Endereço: " + c.getEndereço());
			System.out.println("> Telefone: " + c.getTelefone());
		}
		System.out.println("\n");

		System.out.print("//==============================//\n\n\n");
		// ==============================//

		// ==== Teste MateriaPrimaService ====//
		System.out.print("//==== Teste MateriaPrimaService ====//\n");

		Fornecedor fncd = new Fornecedor();
		Date d = new Date();

		System.out.print(">>> Criando a Matéria Prima 1...\n");
		MateriaPrima mp1 = new MateriaPrima(1, "Frango", "alimento", true, "kg", (float) 4.5);

		System.out.print(">>> Criando a Matéria Prima 2...\n");
		MateriaPrima mp2 = new MateriaPrima(2, "Carne", "alimento", true, "kg", (float) 3.0);

		System.out.print(">>> Criando a Matéria Prima 3...\n");
		MateriaPrima mp3 = new MateriaPrima(3, "Farinha de Trigo", "alimento", true, "kg", (float) 2.0);

		materiaPrimaService.inserir(mp1);
		materiaPrimaService.inserir(mp2);
		materiaPrimaService.inserir(mp3);
		
		MateriaPrima mp1_1 = new MateriaPrima(1, "Linguiça", "alimento", true, "kg", (float) 1.5);
		materiaPrimaService.alterar(1, mp1_1);

		ArrayList<MateriaPrima> materiasPrimas = materiaPrimaService.procuraTodos();

		System.out.println("\n>>> Matérias Primas cadastradas:");
		for (MateriaPrima m : materiasPrimas) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Matéria Prima ID=" + m.getId() + ": ");
			System.out.println("> Nome: " + m.getNome());
			System.out.println("> Tipo: " + m.getTipo());
			System.out.println("> Perecivel: " + m.isPerecivel());
			System.out.println("> Unidade de Medida: " + m.getUnMedida());
			System.out.println("> Quantidade mínima: " + m.getQntMinima());
		}
		System.out.println("\n");

		System.out.println(">>> Consulta nome matéria prima 1: " + materiaPrimaService.procuraPeloId(1).getNome() + "\n");

		System.out.print("//=================================//\n\n\n");
		// ===================================//
		MateriaPrimaReal mr1 = new MateriaPrimaReal(1, 1, 2.5f, new Date(), 50, 1);
		MateriaPrimaReal mr2 = new MateriaPrimaReal(2, 2, 2.5f, new Date(), 50, 1);
		materiaPrimaRealService.inserir(mr1);
		materiaPrimaRealService.inserir(mr2);
		// ===== Teste FornecedorService ====//
		System.out.print("//==== Teste FornecedorService ====//\n");

		System.out.print(">>> Criando o Fornecedor 1...\n");
		Fornecedor f1 = new Fornecedor(1, "Nordestão", "12345678912345", "Rua Teste, 12", "88-922221111",
				"email@email.com", materiasPrimas);

		System.out.print(">>> Criando o Fornecedor 2...\n");
		Fornecedor f2 = new Fornecedor(2, "Extra", "123", "Rua teste, 34", "81-99998888", "email@email.com",
				materiasPrimas);

		fornecedorService.inserir(f1);
		fornecedorService.inserir(f2);
		
		Fornecedor f2_1 = new Fornecedor(2, "Supershow", "123", "Rua teste, 34", "81-99998888", "email@email.com",
				materiasPrimas);
		fornecedorService.alterar(2, f2_1);

		ArrayList<Fornecedor> fornecedores = fornecedorService.procuraTodos();

		System.out.println("\n>>> Fornecedores cadastrados:");
		for (Fornecedor f : fornecedores) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Fornecedor ID=" + f.getId() + ": ");
			System.out.println("> Nome: " + f.getNome());
			System.out.println("> CNPJ: " + f.getCnpj());
			System.out.println("> Endereço: " + f.getEndereço());
			System.out.println("> Telefone: " + f.getTelefone());
			System.out.println("> Email: " + f.getEmail());
			System.out.println("> Lista de Produtos: ");
			int contador = 0;
			for (MateriaPrima m : f.getListaProdutos()) {
				contador += 1;
				System.out.println("\t" + contador + ". " + m.getNome());
			}
		}
		System.out.println("\n");
		// Teste ProdutoFinal
		System.out.print("//========== Teste Produto Final ===========//\n\n\n");

		HashMap<Integer, Float> receita = new HashMap<Integer, Float>();
		receita.put(3, 0.1f);
		receita.put(2, 0.5f);
		ProdutoFinal p1 = new ProdutoFinal(1, "Esfirra", 0.5f, 3, receita);

		HashMap<Integer, Float> receita1 = new HashMap<Integer, Float>();
		receita1.put(3, 0.1f);
		receita1.put(1, 0.5f);
		ProdutoFinal p2 = new ProdutoFinal(2, "Coxinha", 0.75f, 6, receita1);

		produtoFinalService.inserir(p1);
		produtoFinalService.inserir(p2);

		ProdutoFinal p2_1 = new ProdutoFinal(2, "Empada", 0.75f, 6, receita1);
		produtoFinalService.alterar(2, p2_1);
		
		ProdutoFinal p3 = new ProdutoFinal(3, "Coxinha", 0.75f, 8, receita1);
		produtoFinalService.inserir(p3);

		System.out.print(">>> Cadastrando Produtos");

		ArrayList<ProdutoFinal> produtos = new ArrayList<ProdutoFinal>();
		produtos = produtoFinalService.procuraTodos();

		System.out.println("\n>>> Produtos cadastrados:");
		for (ProdutoFinal p : produtos) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> ProdutoFinal ID=" + p.getId() + ": ");
			System.out.println("> Nome: " + p.getNome());
			System.out.println("> Preço: " + p.getPreco());
			System.out.println("> Quantidade Mínima: " + p.getQntMinima());
			System.out.println("> Receita: ");
			int contador = 0;
			for (int h : p.getReceita().keySet()) {
				contador += 1;
				System.out.println("\t" + contador + ". " + materiaPrimaService.procuraPeloId(h).getNome() + ": "
						+ p.getReceita().get(h) + materiaPrimaService.procuraPeloId(h).getUnMedida());
			}

		}
		// Teste Produto Final Real
		System.out.print("//========== Teste Produto Final Real ===========//\n\n\n");
		Date data = new Date();
		data.setDate(15);
		ProdutoFinalReal pr1 = new ProdutoFinalReal(1, 2, data, 50);
		data.setDate(10);
		ProdutoFinalReal pr2 = new ProdutoFinalReal(2, 2, data, 20);
		data.setDate(15);
		ProdutoFinalReal pr3 = new ProdutoFinalReal(3, 1, data, 50);
		data.setDate(10);
		ProdutoFinalReal pr4 = new ProdutoFinalReal(4, 1, data, 20);
		produtoFinalRealService.inserir(pr1);
		produtoFinalRealService.inserir(pr2);
		produtoFinalRealService.inserir(pr3);
		produtoFinalRealService.inserir(pr4);
		
		data.setDate(10);
		ProdutoFinalReal pr4_1 = new ProdutoFinalReal(4, 1, data, 22);
		produtoFinalRealService.alterar(4, pr4_1);

		ArrayList<ProdutoFinalReal> produtosReais = produtoFinalRealService.procuraTodos();
		System.out.println("\n>>> Produtos Reais cadastrados:");
		for (ProdutoFinalReal p : produtosReais) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> ProdutoFinalReal ID=" + p.getId() + ": ");
			System.out.println("> Nome: " + produtoFinalService.procuraPeloId(p.getIdExterno()).getNome());
			System.out.println("> Validade: " + p.getValidade());
			System.out.println("> Quantidade: " + p.getQuantidade());
		}
		
		System.out.print("\n\n//========== Teste Encomenda ===========//\n\n");
		HashMap<Integer, Integer> listProdutos3 = new HashMap<Integer, Integer>();
		listProdutos3.put(1, 10);
		listProdutos3.put(2, 30);
		Date data3 = new Date();
		try {
			data3 = new SimpleDateFormat("yyyyMMdd").parse("20210320");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		HashMap<Integer, Integer> listProdutos4 = new HashMap<Integer, Integer>();
		listProdutos4.put(1, 10);
		listProdutos4.put(2, 20);
		listProdutos4.put(3, 50);
		
		Date data4 = new Date();
		try {
			data4 = new SimpleDateFormat("yyyyMMdd").parse("20210401");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		encomendaService.realizarEncomenda(listProdutos3, 1, data3);
		encomendaService.realizarEncomenda(listProdutos4, 2, data4);
		
		ArrayList<Encomenda> encomendas = encomendaService.procuraTodos();
		for (Encomenda e : encomendas) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Encomenda ID= " + e.getId());
			System.out.println("> Valor: " + e.getValor() + "R$");
			System.out.println("> Cliente: " + clienteService.procuraPeloId(e.getIdCliente()).getNome());
			System.out.println("> Data: " + e.getData());
			System.out.println("> Data Entrega: " + e.getDataEntrega());
			System.out.println("> Lista de Produtos: ");
			int contador = 0;
			for (Integer p : e.getListaProdutos().keySet()) {
				contador += 1;
				System.out.println("\t" + contador + ". " + produtoFinalService.procuraPeloId(p).getNome() + ": "
						+ e.getListaProdutos().get(p) + " Unidades");
			}
		}
		encomendaService.cosumarEncomenda(1);
		
		System.out.print("\n\n\n//====== Teste Lista Reposição Produto ======//\n");
		
		HashMap<Integer, Integer> listaReposicaoProduto = relatorioService.listarReposicaoProduto(data3, data4);
		
		System.out.println(">>> Lista de produtos necessários para reposição de acordo \ncom as encomendas entre as datas: \n"+data3+" e "+data4+":\n");
		for(int id : listaReposicaoProduto.keySet()) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Produto ID = " + id);
			System.out.println(">> Quantidade necessária = " + listaReposicaoProduto.get(id));
		}
		
		System.out.print("\n\n\n//====== Teste Lista Reposição Matéria Prima ======//\n");
		
		HashMap<Integer, Float> listaReposicaoMateriaPrima = relatorioService.listarReposicaoMateriaPrima(data3, data4);
		
		System.out.println(">>> Lista de matérias primas necessários para reposição de acordo \ncom as encomendas entre as datas: \n"+data3+" e "+data4+":\n");
		for(int id : listaReposicaoMateriaPrima.keySet()) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Matéria Prima ID = " + id);
			System.out.println(">> Quantidade necessária = " + listaReposicaoMateriaPrima.get(id));
		}
		
		System.out.print("//========== Teste Venda ===========//\n\n\n");

		HashMap<Integer, Integer> listProdutos1 = new HashMap<Integer, Integer>();
		listProdutos1.put(1, 10);
		listProdutos1.put(2, 30);
		
		System.out.println(">>> Realizando Venda1");
		
		vendaService.realizarVenda(listProdutos1, 1);
		

		HashMap<Integer, Integer> listProdutos2 = new HashMap<Integer, Integer>();

		listProdutos2.put(2, 10);
		listProdutos2.put(1, 30);

		System.out.println(">>> Realizando Venda2");
		vendaService.realizarVenda(listProdutos2, 2);

		ArrayList<Venda> vendas = new ArrayList<Venda>();

		vendas = vendaService.procuraTodos();

		for (Venda v : vendas) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Venda ID= " + v.getId());
			System.out.println("> Valor: " + v.getValor() + "R$");
			System.out.println("> Cliente: " + clienteService.procuraPeloId(v.getIdCliente()).getNome());
			System.out.println("> Data: " + v.getData());
			System.out.println("> Lista de Produtos: ");
			int contador = 0;
			for (Integer p : v.getListaProdutos().keySet()) {
				contador += 1;
				System.out.println("\t" + contador + ". " + produtoFinalService.procuraPeloId(p).getNome() + ": "
						+ v.getListaProdutos().get(p) + " Unidades");
			}
		}
		
		vendaService.cancelarVenda(1);
		vendas = vendaService.procuraTodos();
		
		
		System.out.println("******* Após Cancelar a venda 1 *******");
		
		for (Venda v : vendas) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Venda ID= " + v.getId());
			System.out.println("> Valor: " + v.getValor() + "R$");
			System.out.println("> Cliente: " + clienteService.procuraPeloId(v.getIdCliente()).getNome());
			System.out.println("> Data: " + v.getData());
			System.out.println("> Lista de Produtos: ");
			int contador = 0;
			for (Integer p : v.getListaProdutos().keySet()) {
				contador += 1;
				System.out.println("\t" + contador + ". " + produtoFinalService.procuraPeloId(p).getNome() + ": "
						+ v.getListaProdutos().get(p) + " Unidades");
			}
		}
		
		System.out.print("//========== Teste Estoque ===========//\n\n\n");

		ArrayList<ProdutoFinalReal> produtosEstoque = estoqueService.procuraTodosProdutos();
		ArrayList<MateriaPrimaReal> materiaPrimaEstoque = estoqueService.procuraTodasMaterias();
		System.out.println("Produto Final");
		for (ProdutoFinalReal p : produtosEstoque) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Produto ID = " + p.getId());
			System.out.println(">> Nome = " + produtoFinalService.procuraPeloId(p.getIdExterno()).getNome());
			System.out.println(">> Quantidade = " + p.getQuantidade());
		}
		System.out.println("\nMateria Prima");
		for (MateriaPrimaReal m : materiaPrimaEstoque) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Materia Prima ID = " + m.getId());
			System.out.println(">> Nome = " + materiaPrimaService.procuraPeloId(m.getIdExterno()).getNome());
			System.out.println(">> Quantidade = " + m.getQuantidade() + materiaPrimaService.procuraPeloId(m.getId()).getUnMedida());
		}
		// ===================================//
	}

}
