package pessoa;

import java.util.HashMap;

import exceptions.StringInvalidaException;


public class PessoaController {
	
	HashMap<String, Pessoa> pessoas;
	
	public PessoaController() {
		pessoas = new HashMap<>();
	}

	public String cadastraPessoa(String cpf, String nome, String email) throws StringInvalidaException {
		Pessoa pessoa = new Pessoa(nome, email, cpf);
		pessoas.put(pessoa.getCpf(), pessoa);
		return pessoa.getCpf();
	}

	public String getInfoPessoa(String cpf, String atributo) {
		if (atributo.equals("Nome")){
			return this.getNome(cpf);
		} else {
			return this.getEmail(cpf);
		}
	}

	private String getNome(String cpf) {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getNome();
	}

	private String getEmail(String cpf) {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getEmail();
	}
	
	private Pessoa getPessoa(String cpf) {
		return pessoas.get(cpf);
	}
	
	

}
