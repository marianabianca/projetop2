package Services;

import java.util.HashMap;
import java.util.Map;

import pessoa.Pessoa;

public class PessoaService {
	
	Map<String, Pessoa> pessoas;
	
	public PessoaService() {
		pessoas = new HashMap<>();
	}

	public String cadastraPessoa(String cpf, String nome, String email) {
		Pessoa pessoa = new Pessoa(nome, email, cpf);
		pessoas.put(cpf, pessoa);
		
		return cpf;
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		if (atributo.equals("email")){
			return this.getEmail(cpf);
		} else {
			return this.getNome(cpf);
		}
	}

	private String getEmail(String cpf) throws Exception {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getEmail();
	}
	
	private String getNome(String cpf) throws Exception {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getNome();
	}

	private Pessoa getPessoa(String cpf) throws Exception {
		try{
			Pessoa pessoa = pessoas.get(cpf);
			return pessoa;
		} catch (Exception e) {
			throw new Exception("Erro na consulta de pessoa: Pessoa nao encontrada");
		}
	}

	public void removePessoa(String cpf) {
		this.pessoas.remove(cpf);
	}
	
	
	
	
	
	

}
