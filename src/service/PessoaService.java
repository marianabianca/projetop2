package service;

import java.util.HashMap;
import java.util.Map;

import participacao.Participacao;
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
		this.pessoas.remove(cpf, pessoas.get(cpf));
	}
	
	public void adicionaParticipacao(String cpfPessoa, String codigoProjeto, Participacao participacao) throws Exception {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		pessoa.adicionaParticipacao(codigoProjeto, participacao);		
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
		for (String cpfDaPessoa : pessoas.keySet()) {
			if (cpf.equals(cpfDaPessoa)){
				return pessoas.get(cpfDaPessoa);
			}
		}
		throw new Exception("Erro na consulta de pessoa: Pessoa nao encontrada");
	}

	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		Pessoa aEditar = getPessoa(cpfPessoa);
		if (atributo.equals("nome")){
			aEditar.setNome(valor);
		} else if (atributo.equals("cpf")){
			aEditar.setCpf(valor);
		} else {
			aEditar.setEmail(valor);
		}
		
	}

}