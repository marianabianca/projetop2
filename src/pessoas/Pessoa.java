package pessoas;

import exceptions.StringInvalidaException;

public abstract class Pessoa {
	private String nome, email, cpf;
	
	public Pessoa(String nome, String email, String cpf) throws StringInvalidaException {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		// TODO TRATAMENTO
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		// TODO TRATAMENTO
		this.email = email;
	}

	public void setNome(String nome) {
		// TODO TRATAMENTO
		this.nome = nome;
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
		return ("Nome:" + this.nome + ", e-mail:" + this.email + ", CPF:" + this.cpf);
	}
	
	
	
}
