import java.util.Date;

import BUSINESS.Cliente;
import BUSINESS.ClienteService;
import DATA.ClienteDAO;
import BUSINESS.MateriaPrima;
import BUSINESS.MateriaPrimaService;
import BUSINESS.Fornecedor;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.print("//=== Bem-vindo ao SmartStock! ===//\n\n");
		
		//==== Teste ClienteService ===//
		System.out.print("//==== Teste ClienteService ===//\n");
		
		System.out.print(">>> Cadastrando o cliente 1...\n");
		Cliente cliente1 = new Cliente(1, "Maria", "123", "Rua Palmares, 180", "84-3332222");
		
		System.out.print(">>> Cadastrando o cliente 2...\n");
		Cliente cliente2 = new Cliente(2, "João", "11122233344", "Rua dos Poetas, 255", "84-44445555");
		
		Cliente[] clientes = {cliente1, cliente2};		
		ClienteDAO clienteDAO = new ClienteDAO();	
		clienteDAO.setClienteDAO(clientes);
		ClienteService serviçoCliente = new ClienteService(clienteDAO);
		serviçoCliente.validarCadastroCliente(1);
		serviçoCliente.listarClientes();
		System.out.println(">>> Consulta nome cliente 2: " + serviçoCliente.consultarClientePeloId(2).getNome() + "\n");
		
		System.out.print("//=============================//\n\n\n");
		//=============================//

		
		//==== Teste MateriaPrimaService ===//
		System.out.print("//==== Teste MateriaPrimaService ===//\n");

		Fornecedor f = new Fornecedor();
		Date d = new Date();
		
		System.out.print(">>> Cadastrando a Matéria Prima 1...\n");
		MateriaPrima mp1 = new MateriaPrima(1, "batata", "comida", 1.3F, f, true, 3, "kg", d);
		
		System.out.print(">>> Cadastrando a Matéria Prima 2...\n");
		MateriaPrima mp2 = new MateriaPrima(2, "queijo", "alimento", 1.0F, f, true, 3, "kg", d);
		
		MateriaPrima[] materiasPrimas = {mp1, mp2};
		MateriaPrimaService serviçoMP = new MateriaPrimaService(materiasPrimas);
		serviçoMP.validarCadastroMateriaPrima(1);
		serviçoMP.listarMateriasPrimas();
		System.out.println(">>> Consulta nome matéria prima 2: " + serviçoMP.consultarMateriaPrima(2).getNome() + "\n");
		
		System.out.print("//==================================//\n\n\n");
		//==================================//
	}

}
