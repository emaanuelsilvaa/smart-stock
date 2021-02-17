import java.util.ArrayList;
import java.util.Date;

import BUSINESS.Cliente;
import BUSINESS.ClienteService;
import BUSINESS.EstoqueService;
import DATA.ClienteDAO;

import BUSINESS.MateriaPrima;
import BUSINESS.MateriaPrimaService;
import BUSINESS.ProdutoFinal;
import BUSINESS.ProdutoFinalService;
import BUSINESS.Venda;
import BUSINESS.VendaService;
import DATA.MateriaPrimaDAO;
import DATA.ProdutoFinalDAO;
import DATA.VendaDAO;
import BUSINESS.Fornecedor;
import BUSINESS.FornecedorService;
import DATA.FornecedorDAO;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.print("//=== Bem-vindo ao SmartStock! ===//\n\n");
		
		//==== Teste ClienteService ====//
		System.out.print("//==== Teste ClienteService ====//\n");
		
		System.out.print(">>> Criando o cliente 1...\n");
		Cliente cliente1 = new Cliente(1, "Maria", "123", "Rua Palmares, 180", "84-3332222");
		
		System.out.print(">>> Criando o cliente 2...\n");
		Cliente cliente2 = new Cliente(2, "João", "11122233344", "Rua dos Poetas, 255", "84-44445555");
		
		System.out.print(">>> Criando o cliente 3...\n");
		Cliente cliente3 = new Cliente(3, "Yago", "12538500460", "Rua Jacaranda, 255", "84-44445555");
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		ClienteService clienteService = new ClienteService(clienteDAO);
		clienteService.inserir(cliente1);
		clienteService.inserir(cliente2);
		clienteService.inserir(cliente3);
		
		ArrayList<Cliente> clientes = clienteService.procuraTodos();
		
		System.out.println("\n>>> Clientes cadastrados:");
		for(Cliente c : clientes) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Cliente ID=" + c.getId() + ": ");
			System.out.println("> Nome: " + c.getNome());
			System.out.println("> CPF: " + c.getCpf());
			System.out.println("> Endereço: " + c.getEndereço());
			System.out.println("> Telefone: " + c.getTelefone());
		}
		System.out.println("\n");
		
		System.out.print("//==============================//\n\n\n");
		//==============================//

		
		//==== Teste MateriaPrimaService ====//
		System.out.print("//==== Teste MateriaPrimaService ====//\n");

		Fornecedor fncd = new Fornecedor();
		Date d = new Date();
		
		System.out.print(">>> Criando a Matéria Prima 1...\n");
		MateriaPrima mp1 = new MateriaPrima(1, "batata", "alimento", 1.3F, fncd, true, 3, "kg", d);
		
		System.out.print(">>> Criando a Matéria Prima 2...\n");
		MateriaPrima mp2 = new MateriaPrima(2, "queijo", "alimento", 1.0F, fncd, true, 3, "kg", d);
		
		MateriaPrimaDAO materiaPrimaDAO = new MateriaPrimaDAO();
		
		MateriaPrimaService materiaPrimaService = new MateriaPrimaService(materiaPrimaDAO);
		materiaPrimaService.inserir(mp1);
		materiaPrimaService.inserir(mp2);
		
		ArrayList<MateriaPrima> materiasPrimas = materiaPrimaService.procuraTodos();
		
		System.out.println("\n>>> Matérias Primas cadastradas:");
		for(MateriaPrima m : materiasPrimas) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Matéria Prima ID=" + m.getId() + ": ");
			System.out.println("> Nome: " + m.getNome());
			System.out.println("> Tipo: " + m.getTipo());
			System.out.println("> Preço: " + m.getPreço());
			System.out.println("> Fornecedor: " + m.getFornecedor());
			System.out.println("> Perecivel: " + m.isPerecivel());
			System.out.println("> Quantidade: " + m.getQuantidade());
			System.out.println("> Unidade de Medida: " + m.getUnMedida());
			System.out.println("> Validade: " + m.getValidade());
		}
		System.out.println("\n");
		
		System.out.println(">>> Consulta nome matéria prima 1: " + materiaPrimaService.procuraPeloId(1).getNome() + "\n");
	
		System.out.print("//=================================//\n\n\n");
		//===================================//
		
		//===== Teste FornecedorService ====//
		System.out.print("//==== Teste FornecedorService ====//\n");

		System.out.print(">>> Criando o Fornecedor 1...\n");
		Fornecedor f1 = new Fornecedor(1, "Nordestão", "12345678912345", "Rua Teste, 12", "88-922221111", "email@email.com", materiasPrimas);

		System.out.print(">>> Criando o Fornecedor 2...\n");
		Fornecedor f2 = new Fornecedor(2, "Extra", "123", "Rua teste, 34", "81-99998888", "email@email.com", materiasPrimas);

		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		
		FornecedorService fornecedorService = new FornecedorService(fornecedorDAO);
		
		fornecedorService.inserir(f1);
		fornecedorService.inserir(f2);
		
		ArrayList<Fornecedor> fornecedores = fornecedorService.procuraTodos();

		System.out.println("\n>>> Fornecedores cadastrados:");
		for(Fornecedor f : fornecedores) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Fornecedor ID=" + f.getId() + ": ");
			System.out.println("> Nome: " + f.getNome());
			System.out.println("> CNPJ: " + f.getCnpj());
			System.out.println("> Endereço: " + f.getEndereço());
			System.out.println("> Telefone: " + f.getTelefone());
			System.out.println("> Email: " + f.getEmail());
			System.out.println("> Lista de Produtos: ");
			int contador = 0;
			for(MateriaPrima m : f.getListaProdutos()) {
				contador += 1;
				System.out.println("\t" + contador + ". " + m.getNome());
			}
		}
		System.out.println("\n");
		
		// Teste ProdutoFinal
		System.out.print("//========== Teste Produto Final ===========//\n\n\n");
		
		ArrayList<MateriaPrima> receita = new ArrayList<MateriaPrima>();		
		MateriaPrima r1 = new MateriaPrima(1, "Farinha de Trigo", "Alimento", 0.01f, fncd, true, 10, "gm", d);
		MateriaPrima r2 = new MateriaPrima(2, "Carne", "Alimento", 0.5f, fncd, true, 25, "gm", d);
		
		receita.add(r1);
		receita.add(r2);
		
		ProdutoFinal p1 = new ProdutoFinal(1, "Esfirra", 0.5f, receita, 50);
		ProdutoFinal p2 = new ProdutoFinal(2, "Empada", 1f, receita, 50);
		
		ProdutoFinalDAO produtoFinalDAO = new ProdutoFinalDAO();
		
		ProdutoFinalService produtoFinalService = new ProdutoFinalService(produtoFinalDAO);
		System.out.print(">>> Cadastrando Produtos");
		
		produtoFinalService.inserir(p1);
		produtoFinalService.inserir(p2);
		
		ArrayList<ProdutoFinal> produtos = new ArrayList<ProdutoFinal>();
		produtos = produtoFinalService.procuraTodos();
		
		for (ProdutoFinal p : produtos) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> ProdutoFInal ID=" + p.getId() + ": ");
			System.out.println("> Nome: " + p.getNome());
			System.out.println("> Preço: " + p.getPreço());
			System.out.println("> Receita: " + p.getListaMateriaPrima());
			System.out.println("> Unidades: " + p.getUnidades());
		}
		
		System.out.print("//========== Teste Venda ===========//\n\n\n");
		
		ArrayList<ProdutoFinal> listProdutos = new ArrayList<ProdutoFinal>();
		
		ProdutoFinal pro1 = new ProdutoFinal(1, "Esfirra", 0.5f, receita, 10);
		ProdutoFinal pro2 = new ProdutoFinal(2, "Empada", 1f, receita, 20);
		
		listProdutos.add(pro1);
		listProdutos.add(pro2);
		
		VendaDAO vendaDAO = new VendaDAO();
		
		EstoqueService estoqueService = new EstoqueService(produtoFinalDAO, materiaPrimaDAO);
		VendaService vendaService = new VendaService(vendaDAO, estoqueService);
		
		System.out.println(">>> Realizando Venda1");
		
		vendaService.realizarVenda(listProdutos, cliente3);
		
		ArrayList<ProdutoFinal> listProdutos2 = new ArrayList<ProdutoFinal>();
		
		ProdutoFinal pro3 = new ProdutoFinal(1, "Esfirra", 0.5f, receita, 13);
		ProdutoFinal pro4 = new ProdutoFinal(2, "Empada", 1f, receita, 30);
		
		listProdutos2.add(pro3);
		listProdutos2.add(pro4);
		
		System.out.println(">>> Realizando Venda2");
		vendaService.realizarVenda(listProdutos2, cliente1);
		
		ArrayList<Venda> vendas = new ArrayList<Venda>();
		
		vendas = vendaService.procuraTodos();
		
		for (Venda v : vendas) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Venda ID= " + v.getId());
			System.out.println("> Valor: " + v.getValor() + "R$");
			System.out.println("> Cliente: " + v.getCliente().getNome());
			System.out.println("> Data: " + v.getData());
			System.out.println("> Lista de Produtos: ");
			int contador = 0;
			for(ProdutoFinal p : v.getListProdutoFinal()) {
				contador += 1;
				System.out.println("\t" + contador + ". " + p.getNome() + ": "+ p.getUnidades() + " Unidades");
			}			
		}
		System.out.print("//========== Teste Estoque ===========//\n\n\n");
		
		ArrayList<ProdutoFinal> produtosEstoque = estoqueService.procuraTodosProdutos();
		ArrayList<MateriaPrima> materiaPrimaEstoque = estoqueService.procuraTodasMaterias();
		System.out.println("Produto Final");
		for (ProdutoFinal p : produtosEstoque) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Produto ID = "+ p.getId());
			System.out.println(">> Nome = "+ p.getNome());
			System.out.println(">> Quantidade = "+ p.getUnidades());
		}
		System.out.println("\nMateria Prima");
		for (MateriaPrima m : materiaPrimaEstoque) {
			System.out.println("- - - - - - - - - -");
			System.out.println(">> Materia Prima ID = "+ m.getId());
			System.out.println(">> Nome = "+ m.getNome());
			System.out.println(">> Quantidade = "+ m.getQuantidade() + m.getUnMedida());
		}
		//===================================//
	}

}
