package DATA;

import java.util.ArrayList;

import ENTITY.Fornecedor;

public class FornecedorDAO implements IFornecedorDAO {
	protected /*@ spec_public @*/ArrayList<Fornecedor> fornecedores;
	protected /*@ spec_public @*/int idSerial;
	
	// Construtores
	/*@ 
	  @ assignable fornecedores,idSerial;
	  @ ensures fornecedores != null; 
	  @ ensures idSerial > 0; 
	  @*/
	public FornecedorDAO() {
		this.fornecedores = new ArrayList<Fornecedor>();
		this.idSerial = 1;
	}
	/*@
	  @ assignable fornecedores;
	  @ ensures fornecedores != null; 
	  @*/
	public FornecedorDAO(ArrayList<Fornecedor> fornecedores) {
		super();
		this.fornecedores = fornecedores;
	}

	/*@ also
	  @ assignable fornecedor;
	  @ ensures \result == fornecedor.getId();
	  @*/
	@Override
	public int inserir(Fornecedor fornecedor) {
		fornecedor.setId(this.pegaEIncremanetaId());
		this.fornecedores.add(fornecedor);
		return fornecedor.getId();
	}

	/*@ also 
	  @requires 0 < id; 
	  @ assignable id, fornecedores;
	  @ ensures \result == id;
	  @*/
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

	/*@ also
	  @ ensures id >0; 
	  @ ensures \result ==  id;
	  @*/
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

	/*@ also
	  @ ensures 0 < id;
	  @ ensures \result ==  id;
	  @*/
	@Override
	public Fornecedor procuraPeloId(int id) {
		for (Fornecedor f : this.fornecedores) {
			if (f.getId() == id) {
				return f;
			}
		}
		return null;
	}

	/*@ also
	  @ ensures fornecedores !=null;
	  @*/
	@Override
	public ArrayList<Fornecedor> procuraTodos(){
		return this.fornecedores;
	}
	
	/*@ also
	  @ assignable idSerial; 
	  @ ensures this.idSerial == \old(idSerial+1);
	  @*/
	@Override
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
	
}
