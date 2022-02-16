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
		IProdutoFinalService produtoFinalService = ProdutoFinalService.getInstance();
		IProdutoFinalRealService produtoFinalRealService = ProdutoFinalRealService.getInstance();
		IVendaService vendaService = VendaService.getInstance();
		IEstoqueService estoqueService = EstoqueService.getInstance();
		IEncomendaService encomendaService = EncomendaService.getInstance();
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

		MateriaPrima mp1 = new MateriaPrima("Farinha de Trigo", "alimento", true, "kilos", (float) 4.5);
		MateriaPrima mp2 = new MateriaPrima("Carne de Sol", "alimento", true, "kilos", (float) 4.5);
		MateriaPrima mp3 = new MateriaPrima("Frango", "alimento", true, "kilos", (float) 4.5);
		MateriaPrima mp4 = new MateriaPrima("Óleo", "alimento", true, "litros", (float) 4.5);
		MateriaPrima mp5 = new MateriaPrima("Fermento", "alimento", true, "kilos", (float) 2.5);
		MateriaPrima mp6 = new MateriaPrima("Margarina", "alimento", true, "kilos", (float) 4.5);
		MateriaPrima mp7 = new MateriaPrima("Manteiga", "alimento", true, "kilos", (float) 4.5);
		MateriaPrima mp8 = new MateriaPrima("Ovo", "alimento", true, "unidades", (float) 4.5);
		MateriaPrima mp9 = new MateriaPrima("Leite", "alimento", true, "litros", (float) 4.5);

		materiaPrimaService.inserir(mp1);
		materiaPrimaService.inserir(mp2);
		materiaPrimaService.inserir(mp3);
		materiaPrimaService.inserir(mp4);
		materiaPrimaService.inserir(mp5);
		materiaPrimaService.inserir(mp6);
		materiaPrimaService.inserir(mp7);
		materiaPrimaService.inserir(mp8);
		materiaPrimaService.inserir(mp9);

		ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
		materias.add(mp1);
		materias.add(mp2);
		materias.add(mp3);
		materias.add(mp4);
		materias.add(mp5);

		
		Fornecedor f1 = new Fornecedor("Nordestão", "12345678912345", "Rua Teste, 12", "88-922221111",
				"email@email.com", materias);
		Fornecedor f2 = new Fornecedor("Distribuidora Goró", "12345678912345", "Rua Teste, 12", "88-922221111",
				"email@email.com", materias);
		Fornecedor f3 = new Fornecedor("Alambique LTDA", "12345678912345", "Rua Teste, 12", "88-922221111",
				"emal@email.com", materias);
		Fornecedor f4 = new Fornecedor("Granja Fernão", "12345678912345", "Rua Teste, 12", "88-922221111",
				"email@email.com", materias);
		Fornecedor f5 = new Fornecedor("Mario Distribuidora", "12345678912345", "Rua Teste, 12", "88-922221111",
				"email@email.com", materias);

		fornecedorService.inserir(f1);
		fornecedorService.inserir(f2);
		fornecedorService.inserir(f3);
		fornecedorService.inserir(f4);
		fornecedorService.inserir(f5);

		MateriaPrimaReal mr1 = new MateriaPrimaReal(1, 4.5f, new Date(), 10, 1);
		MateriaPrimaReal mr2 = new MateriaPrimaReal(2, 30f, new Date(), 5, 1);
		MateriaPrimaReal mr3 = new MateriaPrimaReal(3, 10f, new Date(), 5, 1);
		MateriaPrimaReal mr4 = new MateriaPrimaReal(4, 3f, new Date(), 2, 1);
		MateriaPrimaReal mr5 = new MateriaPrimaReal(5, 0.5f, new Date(), 1 , 1);
		MateriaPrimaReal mr6 = new MateriaPrimaReal(6, 13f, new Date(), 3, 1);
		MateriaPrimaReal mr7 = new MateriaPrimaReal(7, 13f, new Date(), 5, 1);
		MateriaPrimaReal mr8 = new MateriaPrimaReal(8, 1f, new Date(), 50, 1);
		MateriaPrimaReal mr9 = new MateriaPrimaReal(9, 3f, new Date(), 10, 1);
		
		materiaPrimaRealService.inserir(mr1);
		materiaPrimaRealService.inserir(mr2);
		materiaPrimaRealService.inserir(mr3);
		materiaPrimaRealService.inserir(mr4);
		materiaPrimaRealService.inserir(mr5);
		materiaPrimaRealService.inserir(mr6);
		materiaPrimaRealService.inserir(mr7);
		materiaPrimaRealService.inserir(mr8);
		materiaPrimaRealService.inserir(mr9);
		
		HashMap<Integer, Float> receita1 = new HashMap<Integer, Float>();
		receita1.put(1, 0.05f);
		receita1.put(2, 0.1f);
		receita1.put(4, 0.1f);
		HashMap<Integer, Float> receita2 = new HashMap<Integer, Float>();
		receita2.put(1, 0.08f);
		receita2.put(3, 0.1f);
		receita2.put(4, 0.1f);
		HashMap<Integer, Float> receita3 = new HashMap<Integer, Float>();
		receita3.put(1, 0.05f);
		receita3.put(2, 0.1f);
		receita3.put(4, 0.1f);
		receita3.put(7, 0.01f);

		ProdutoFinal p1 = new ProdutoFinal("Esfirra", 2f, 30, receita1);
		ProdutoFinal p2 = new ProdutoFinal("Empada", 4f, 30, receita1);
		ProdutoFinal p3 = new ProdutoFinal("Pastel de Carne", 3.5f, 30, receita1);
		
		produtoFinalService.inserir(p1);
		produtoFinalService.inserir(p2);
		produtoFinalService.inserir(p3);



		Date data = new Date("04/21/2031");

		HashMap<Integer, Float> receitaReal1 = new HashMap<Integer, Float>();
		receitaReal1.put(1, 0.05f);
		receitaReal1.put(2, 0.1f);
		receitaReal1.put(4, 0.1f);

		HashMap<Integer, Float> receitaReal2 = new HashMap<Integer, Float>();
		receitaReal2.put(1, 0.08f);
		receitaReal2.put(3, 0.1f);
		receitaReal2.put(4, 0.1f);
		HashMap<Integer, Float> receitaReal3 = new HashMap<Integer, Float>();
		receitaReal3.put(1, 0.08f);
		receitaReal3.put(3, 0.1f);
		receitaReal3.put(4, 0.1f);
		receitaReal3.put(7, 0.01f);


		ProdutoFinalReal pr1 = new ProdutoFinalReal(1, data, 100, receitaReal1);
		ProdutoFinalReal pr2 = new ProdutoFinalReal(2, data, 90, receitaReal2);
		ProdutoFinalReal pr3 = new ProdutoFinalReal(3, data, 70, receitaReal3);
		produtoFinalRealService.inserir(pr1);
		produtoFinalRealService.inserir(pr2);
		produtoFinalRealService.inserir(pr3);
		
		
		
		HashMap<Integer, Integer> listProdutos3 = new HashMap<Integer, Integer>();
		listProdutos3.put(1, 10);
		listProdutos3.put(2, 30);
		Date data3 = new Date("03/30/2021");
	
		
		HashMap<Integer, Integer> listProdutos4 = new HashMap<Integer, Integer>();
		listProdutos4.put(1, 10);
		listProdutos4.put(2, 20);
		listProdutos4.put(3, 50);
		
		Date data4 = new Date("04/25/2021");

//		encomendaService.realizarEncomenda(listProdutos3, 1, data3);
//		encomendaService.realizarEncomenda(listProdutos4, 2, data4);
		
		vendaService.realizarVenda(listProdutos3, 1);
		vendaService.realizarVenda(listProdutos4, 2);
		
		HashMap<Integer, Integer> listProdutos1 = new HashMap<Integer, Integer>();
		listProdutos1.put(1, 10);
		listProdutos1.put(2, 10);		

		HashMap<Integer, Integer> listProdutos2 = new HashMap<Integer, Integer>();
		listProdutos2.put(2, 5);
		listProdutos2.put(1, 30);
	
		System.out.print("//=== Bem-vindo ao SmartStock! ===//\n\n");
		Home.telaInicial(1);
	}
}
