import java.util.ArrayList;
import java.util.Date;

import BUSINESS.Cliente;
import BUSINESS.ClienteService;
import DATA.ClienteDAO;

import BUSINESS.MateriaPrima;
import BUSINESS.MateriaPrimaService;
import DATA.MateriaPrimaDAO;

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
		
		System.out.print(">>> Cadastrando o cliente 1...\n");
		Cliente cliente1 = new Cliente(1, "Maria", "123", "Rua Palmares, 180", "84-3332222");
		
		System.out.print(">>> Cadastrando o cliente 2...\n");
		Cliente cliente2 = new Cliente(2, "João", "11122233344", "Rua dos Poetas, 255", "84-44445555");
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.inserir(cliente1);
		
		ClienteService clienteService = new ClienteService(clienteDAO);
		clienteService.inserir(cliente2);
		clienteService.remover(cliente2.getId());
		
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
		
		System.out.println(">>> Consulta nome cliente 1: " + clienteService.procuraPeloId(1).getNome() + "\n");
		
		System.out.print("//==============================//\n\n\n");
		//==============================//

		
		//==== Teste MateriaPrimaService ====//
		System.out.print("//==== Teste MateriaPrimaService ====//\n");

		Fornecedor f = new Fornecedor();
		Date d = new Date();
		
		System.out.print(">>> Cadastrando a Matéria Prima 1...\n");
		MateriaPrima mp1 = new MateriaPrima(1, "batata", "comida", 1.3F, f, true, 3, "kg", d);
		
		System.out.print(">>> Cadastrando a Matéria Prima 2...\n");
		MateriaPrima mp2 = new MateriaPrima(2, "queijo", "alimento", 1.0F, f, true, 3, "kg", d);
		
		MateriaPrimaDAO materiaPrimaDAO = new MateriaPrimaDAO();
		materiaPrimaDAO.inserir(mp1);
		
		MateriaPrimaService materiaPrimaService = new MateriaPrimaService(materiaPrimaDAO);
		materiaPrimaService.inserir(mp2);
		materiaPrimaService.remover(mp2.getId());
		
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
		
	}

}
