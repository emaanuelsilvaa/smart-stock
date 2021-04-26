import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import BUSINESS.AnaliseLucro;
import BUSINESS.ClienteService;
import BUSINESS.EncomendaService;
import BUSINESS.EstoqueService;
import DATA.ClienteDAO;
import BUSINESS.MateriaPrimaRealService;
import BUSINESS.MateriaPrimaService;
import BUSINESS.ProdutoFinalRealService;
import BUSINESS.ProdutoFinalService;
import BUSINESS.RelatorioService;
import BUSINESS.ValidarAlimento;
import BUSINESS.VendaService;
import DATA.MateriaPrimaDAO;
import DATA.MateriaPrimaRealDAO;
import DATA.ProdutoFinalDAO;
import DATA.ProdutoFinalRealDAO;
import DATA.VendaDAO;
import ENTITY.Alimento;
import ENTITY.Bone;
import ENTITY.Cliente;
import ENTITY.Encomenda;
import ENTITY.Especificidade;
import ENTITY.EspecificidadeAlimento;
import ENTITY.Fornecedor;
import ENTITY.MateriaPrima;
import ENTITY.MateriaPrimaReal;
import ENTITY.ProdutoFinal;
import ENTITY.ProdutoFinalReal;
import ENTITY.Remedio;
import ENTITY.Venda;
import GUI.Home;
import UTIL.BusinessRuleException;
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
import BUSINESS.IAnaliseLucro;
import BUSINESS.ValidarEspecificidadesVendaAlimento;
import BUSINESS.CalcularFreteAlimento;
import ENTITY.FreteAlimento;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws BusinessRuleException {

		// Singleton
		IClienteService clienteService = ClienteService.getInstance();
		IFornecedorService fornecedorService = FornecedorService.getInstance();
		IMateriaPrimaService materiaPrimaService = MateriaPrimaService.getInstance();
		IMateriaPrimaRealService materiaPrimaRealService = MateriaPrimaRealService.getInstance();
		IProdutoFinalService produtoFinalService = ProdutoFinalService.getInstance(new ValidarAlimento());
		IProdutoFinalRealService produtoFinalRealService = ProdutoFinalRealService.getInstance();
		IVendaService vendaService = VendaService.getInstance(new ValidarEspecificidadesVendaAlimento(), new CalcularFreteAlimento(), new FreteAlimento());
		IEstoqueService estoqueService = EstoqueService.getInstance();
		IEncomendaService encomendaService = EncomendaService.getInstance(new ValidarEspecificidadesVendaAlimento());
		IRelatorioService relatorioService = RelatorioService.getInstance();
		IAnaliseLucro analiseLucro = AnaliseLucro.getInstance();
		

		Cliente cliente1 = new Cliente("Maria", "12356271892", "Rua Palmares, 180", "84-33332222");
		Cliente cliente2 = new Cliente("Jõao", "12356271892", "Rua Palmares, 180", "84-33332222");
		Cliente cliente3 = new Cliente("Mario", "12356271892", "Rua Palmares, 180", "84-33332222");
		Cliente cliente4 = new Cliente("Ana", "12356271892", "Rua Palmares, 180", "84-33332222");
		Cliente cliente5 = new Cliente("Cleber", "12356271892", "Rua Palmares, 180", "84-33332222");

		

		clienteService.inserir(cliente1);
		clienteService.inserir(cliente2);
		clienteService.inserir(cliente3);
		clienteService.inserir(cliente4);
		clienteService.inserir(cliente5);
		

		MateriaPrima mp1 = new MateriaPrima("Ácido", "alimento", true, "gramas", (float) 4.5);
		
		ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
		materias.add(mp1);
		
		Fornecedor f1 = new Fornecedor("Nordestão", "12345678912345", "Rua Teste, 12", "88-922221111",
				"email@email.com", materias);
		
		fornecedorService.inserir(f1);
		
		materiaPrimaService.inserir(mp1);
		
		MateriaPrimaReal mr1 = new MateriaPrimaReal(1, 4.5f, new Date(), 10, 1);
		
		materiaPrimaRealService.inserir(mr1);
		
			
		System.out.print("//=== Bem-vindo ao SmartFarmacia! ===//\n\n");
		Home.telaInicial(1);
	}
}
