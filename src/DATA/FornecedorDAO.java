package DATA;

import java.util.ArrayList;

import ENTITY.Fornecedor;

public class FornecedorDAO implements IFornecedorDAO {
	protected ArrayList<Fornecedor> fornecedores;
	
	// Construtores
	public FornecedorDAO() {
		this.fornecedores = new ArrayList<Fornecedor>();
	}

	public FornecedorDAO(ArrayList<Fornecedor> fornecedores) {
		super();
		this.fornecedores = fornecedores;
	}

	@Override
	public int inserir(Fornecedor fornecedor) {
		this.fornecedores.add(fornecedor);
		return 0;
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
		return 0;
	}

	@Override
	public int alterar() {
		//TODO things
		
		return 0;
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
	
}
