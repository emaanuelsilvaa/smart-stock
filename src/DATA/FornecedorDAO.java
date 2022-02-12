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

	
	/*@ 
	  @ assignable fornecerores,fornecedor;
	  @ ensures fornecedor.length == fornecedor.getId();
	  @*/
	@Override
	public int inserir(/*@ non_null @*/Fornecedor fornecedor) {
		fornecedor.setId(this.pegaEIncremanetaId());
		this.fornecedores.add(fornecedor);
		return fornecedor.getId();
	}

	
	/*@ requires 0 < id; 
	  @ assignable id, fornecedores;
	  @ ensures \result ==  ( \forall int i; i < this.fornecedores.size() );
	  @*/
	@Override
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

	
	/*@ 
	  @ assignable f;
	  @ ensures id != null; 
	  @ ensures \result ==  ( \forall int i; i < this.fornecedores.size() );
	  @*/
	@Override
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

	
	/*@
	  @ assignable \nothing; 
	  @ ensures f.getId() == id;
	  @ ensures \result ==  ( \forall int i; i < this.fornecedores.size() );
	  @*/
	@Override
	public Fornecedor procuraPeloId(/*@ non_null @*/int id) {
		for (Fornecedor f : this.fornecedores) {
			if (f.getId() == id) {
				return f;
			}
		}
		return null;
	}

	
	/*@
	  @ assignable \nothing; 
	  @ ensures fornecedores !=null;
	  @*/
	@Override
	public ArrayList<Fornecedor> procuraTodos(){
		return this.fornecedores;
	}
	
	

	/*@ 
	  @ assignable idAtual, idSerial; 
	  @ ensures idAtual !=null;
	  @ ensures idSerial !=null;
	  @*/
	@Override
	public int pegaEIncremanetaId() {
		// Função com o objetivo de usar as IDs de maneira sequencial e sem repetição
		int idAtual = this.idSerial;
		this.idSerial += 1;
		return idAtual;
	}
	
}
