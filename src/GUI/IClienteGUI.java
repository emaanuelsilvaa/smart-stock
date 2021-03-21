package GUI;

public interface IClienteGUI {

	void init(int a);

	void telaInicial(int a);

	void sair(int a);

	void telaCadastrar(int a);

	void telaAlterar(int a);

	void clientesCadastrados();

	void clienteDetalhado(int id);

	void telaConsultar(int a);

	void removerCliente(int id);

	void telaRemover(int a);

}