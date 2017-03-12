package service;

import java.util.HashMap;
import java.util.Map;

import pessoa.Pessoa;

public class PessoaService {

	private Map<String, Pessoa> pessoas;

	public PessoaService() {
		pessoas = new HashMap<>();
	}

	public String cadastraPessoa(String cpf, String nome, String email) {
		Pessoa pessoa = new Pessoa(nome, email, cpf);
		pessoas.put(cpf, pessoa);
		return cpf;
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		if (atributo.equalsIgnoreCase("email")) {
			return this.getEmailDePessoa(cpf);
		} else {
			return this.getNomeDePessoa(cpf);
		}
	}

	public void removePessoa(String cpf) {
		this.pessoas.remove(cpf);
	}

	private String getEmailDePessoa(String cpf) throws Exception {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getEmail();
	}

	private String getNomeDePessoa(String cpf) throws Exception {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getNome();
	}

	private Pessoa getPessoa(String cpf) throws Exception {
		try {
			Pessoa pessoa = pessoas.get(cpf);
			return pessoa;
		} catch (Exception e) {
			throw new Exception("Erro na consulta de pessoa: Pessoa nao encontrada");
		}
	}

}