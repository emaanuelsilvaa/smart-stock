import java.util.Date;

import BUSINESS.Cliente;
import BUSINESS.Fornecedor;
import BUSINESS.MateriaPrima;
import BUSINESS.ClienteService;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.print("//=== Bem-vindo ao SmartStock! ===//\n\n");
		
		//==== Teste ClienteService ===//
		System.out.print(">>> Cadastrando o cliente 1...\n");
		Cliente cliente1 = new Cliente(1, "Maria", "123", "Rua Palmares, 180", "84-3332222");
		
		System.out.print(">>> Cadastrando o cliente 2...\n");
		Cliente cliente2 = new Cliente(2, "João", "11122233344", "Rua dos Poetas, 255", "84-44445555");
		
		Cliente []clientes = {cliente1, cliente2};		
		ClienteService serviçoCliente = new ClienteService();
		serviçoCliente.setCliente(clientes);
		serviçoCliente.validarCadastroCliente(1);
		serviçoCliente.listarClientes();
		System.out.println(">>> Consulta nome cliente 2: " + serviçoCliente.consultarCliente(2).getNome() + "\n");
		//=============================//

	}

}
