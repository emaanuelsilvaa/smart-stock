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
	  @ ensures idSerial != null; 
	  @*/
	public FornecedorDAO() {
		this.fornecedores = new ArrayList<Fornecedor>();
		this.idSerial = 1;
	}
	/*@
	  @ assignable fornecedores;
	  @ ensures fornecedores != null; 
	  @*/
	public FornecedorDAO(/*@ non_null @*/ArrayList<Fornecedor> fornecedores) {
		super();
		this.fornecedores = fornecedores;
	}

	@Override
	/*@ 
	  @ assignable fornecerores,fornecedor;
	  @ ensures fornecedor.length == fornecedor.getId();
	  @*/
	public int inserir(/*@ non_null @*/Fornecedor fornecedor) {
		fornecedor.setId(this.pegaEIncremanetaId());
		this.fornecedores.add(fornecedor);
		return fornecedor.getId();
	}

	@Override
	/*@ requires 0 <= id; 
	  @ assignable id, fornecedores;
	  @ ensures \result ==  ( \forall int i; i < this.fornecedores.size() );
	  @*/
	public int remover(/*@ non_null @*/int id) {
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
	/*@ 
	  @ assignable f;
	  @ ensures id != null; 
	  @ ensures \result ==  ( \forall int i; i < this.fornecedores.size() );
	  @*/
	public int alterar(/*@ non_null @*/int id, /*@ non_null @*/Fornecedor fornecedor) {
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
	/*@
	  @ assignable \nothing; 
	  @ ensures f.getId() == id;
	  @ ensures \result ==  ( \forall int i; i < this.fornecedores.size() );
	  @*/
	public Fornecedor procuraPeloId(/*@ non_null @*/int id) {
		for (Fornecedor f : this.fornecedores) {
			if (f.getId() == id) {
				return f;
			}
		}
		return null;
	}

	@Override
	/*@
	  @ assignable \nothing; 
	  @ ensures fornecedores !=null;
	  @*/
	public ArrayList<Fornecedor> procuraTodos(){
		return this.fornecedores;
	}
	
	@Override
	/*@ 
	  @ assignable idAtual, idSerial; 
	  @ ensures idAtual !=null;
	  @ ensures idSerial !=null;
	  @*/
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
	
}
