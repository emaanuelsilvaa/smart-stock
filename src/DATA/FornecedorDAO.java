package DATA;

import java.util.ArrayList;

import ENTITY.Fornecedor;

public class FornecedorDAO implements IFornecedorDAO {
	protected /*@ spec_public nullable @*/ArrayList<Fornecedor> fornecedores;
	protected /*@ spec_public @*/int idSerial;
	
	// Construtores
	/*@ ensures fornecedores != null; 
	  @ ensures idSerial > 0; @*/
	public FornecedorDAO() {
		this.fornecedores = new ArrayList<Fornecedor>();
		this.idSerial = 1;
	}
	/*@ ensures fornecedores != null; 
	  @*/
	public FornecedorDAO(ArrayList<Fornecedor> fornecedores) {
		super();
		this.fornecedores = fornecedores;
	}

	@Override
	public int inserir(Fornecedor fornecedor) {
		fornecedor.setId(this.pegaEIncremanetaId());
		this.fornecedores.add(fornecedor);
		return fornecedor.getId();
	}

	@Override
	public int remover(int id) {
		Fornecedor aux = new Fornecedor();
		for (Fornecedor f : this.fornecedores) {
			if(f.getId() == id) {
				aux = f;
				break;
			}
		}
		this.fornecedores.remove(aux);
		return aux.getId();
	}

	@Override
	public int alterar(int id, Fornecedor fornecedor) {
		for (Fornecedor f : this.fornecedores) {
			if (f.getId() == id) {
				f.setId(id);
				f.setNome(fornecedor.getNome());
				f.setCnpj(fornecedor.getCnpj());
				f.setEndereco(fornecedor.getEndereco()); 
				f.setTelefone(fornecedor.getTelefone());
				f.setEmail(fornecedor.getEmail());
				f.setListaProdutos(fornecedor.getListaProdutos());
				break;
			}
		}
		return id;
	}

	@Override
	public Fornecedor procuraPeloId(int id) {
		for (Fornecedor f : this.fornecedores) {
			if (f.getId() == id) {
				return f;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Fornecedor> procuraTodos(){
		return this.fornecedores;
	}
	
	@Override
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
	
}
