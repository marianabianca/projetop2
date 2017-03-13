package pessoa;

import java.util.HashMap;
import java.util.Map;

import participacao.Participacao;

public class Pessoa {

	private String nome, email, cpf;
	private Map<String, Participacao> participacoes;

	public Pessoa(String nome, String email, String cpf) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.participacoes = new HashMap<>();
	}

	public void adicionaParticipacao(String codigoProjeto, Participacao participacao) {
		this.participacoes.put(codigoProjeto, participacao);
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ("Nome: " + this.nome + ", e-mail: " + this.email + ", CPF: " + this.cpf);
	}

}