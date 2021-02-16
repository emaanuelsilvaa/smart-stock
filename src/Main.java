import java.util.ArrayList;
import java.util.Date;

import BUSINESS.Cliente;
import BUSINESS.ClienteService;
import DATA.ClienteDAO;
import BUSINESS.Fornecedor;
import BUSINESS.FornecedorService;
import DATA.FornecedorDAO;
import BUSINESS.MateriaPrima;
import BUSINESS.MateriaPrimaService;
import DATA.MateriaPrimaDAO;

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
		
		System.out.println(">>> Clientes cadastrados:");
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
		
		MateriaPrima[] materiasPrimas = {mp1, mp2};
		MateriaPrimaDAO mpDAO = new MateriaPrimaDAO(materiasPrimas);
		MateriaPrimaService serviçoMP = new MateriaPrimaService(mpDAO);
		serviçoMP.validarCadastroMateriaPrima(1);
		serviçoMP.listarMateriasPrimas();
		System.out.println(">>> Consulta nome matéria prima 2: " + serviçoMP.consultarMateriaPrimaPeloId(2).getNome() + "\n");
		
		System.out.print("//===================================//\n\n\n");
		//==================================//
		
		//===== Teste FornecedorService ====//
		System.out.print("//==== Teste FornecedorService ====//\n");
		
		System.out.print(">>> Cadastrando o Fornecedor 1...\n");
		Fornecedor f1 = new Fornecedor(1, "Nordestão", "12345678912345", "Rua Teste, 12", "88-922221111", "emailerrado", materiasPrimas);
		
		System.out.print(">>> Cadastrando o Fornecedor 2...\n");
		Fornecedor f2 = new Fornecedor(2, "Extra", "123", "Rua teste, 34", "81-99998888", "email@email.com", materiasPrimas);
		
		Fornecedor[] fornecedores = {f1, f2};
		FornecedorDAO fDAO = new FornecedorDAO(fornecedores);
		FornecedorService serviçoF = new FornecedorService(fDAO);
		serviçoF.validarCadastroFornecedor(1);
		serviçoF.validarCadastroFornecedor(2);
		serviçoF.listarFornecedores();
		System.out.println(">>> Consulta nome fornecedor 2: " + serviçoF.consultarFornecedorPeloId(2).getNome() + "\n");
		
		System.out.print("//=================================//\n\n\n");
		//===================================//
	}

}
