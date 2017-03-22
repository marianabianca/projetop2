package pessoa;

import java.util.ArrayList;

import java.util.List;

import exception.LogicaException;
import participacao.AlunoGraduando;
import participacao.Participacao;

public class Pessoa {

	private String nome, email, cpf;
	private List<Participacao> participacoes;

	public Pessoa(String nome, String email, String cpf) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.participacoes = new ArrayList<>();
	}

	public void adicionaParticipacao(Participacao participacao) {
		this.participacoes.add(participacao);
	}

	public void removeParticipacao(String codigoProjeto) throws LogicaException {
		if (!this.temParticipacaoEmProjeto(codigoProjeto)) {
			throw new LogicaException("Pessoa nao possui participacao no projeto indicado");
		}
		for (Participacao participacao : participacoes) {
			if (participacao.getCodigoDoProjeto().equals(codigoProjeto)) {
				participacoes.remove(participacao);
				return;
			}
		}
	}

	public boolean temParticipacaoEmProjeto(String codigoProjeto) {
		for (Participacao participacao : participacoes) {
			if (participacao.getCodigoDoProjeto().equals(codigoProjeto)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * MÉTODO "calculaPontuacaoPorParticipacao" TEM COMO OBJETIVO ACUMULAR OS
	 * VALORES RETORNADOS NOS MÉTODOS DE CADA PARTICIPACÃO. ALÉM DISSO, TRATA OS
	 * CASOS QUE TEM UM LIMITE DE PONTOS ACUMULADOS, COMO O CASO DO
	 * "AlunoGraduando" QUE PARA AS PARTICIPAÇÕES EM PROJETOS DE MONITORIA ELE
	 * ACUMULARÁ NO MÁXIMO 6 E PARA OS OUTROS TIPOS DE PROJETO ELE ACUMULARÁ NO
	 * MÁXIMO 8.
	 * 
	 * @return RETORNA O RESULTADO FINAL DE PONTOS.
	 */
	public double calculaPontuacaoPorParticipacao() {
		double acumulador = 0;
		double acumuladorGraduandoPEDExtensaoPET = 0;
		double acumuladorGraduandoMonitoria = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.isAlunoGraduando() && !participacao.isMonitoria()) {
				acumuladorGraduandoPEDExtensaoPET = calculaPontuacaoPorParticipacaoPEDExtensaoPET(
						acumuladorGraduandoPEDExtensaoPET, participacao);
			} else if (participacao.isAlunoGraduando() && participacao.isMonitoria()) {
				acumuladorGraduandoMonitoria = calculaPontuacaoPorParticipacaoPEDExtensaoPET(
						acumuladorGraduandoMonitoria, participacao);
			} else {
				acumulador += participacao.calculaPontuacao();
			}
		}
		acumulador += acumuladorGraduandoMonitoria + acumuladorGraduandoPEDExtensaoPET;
		return acumulador;
	}

	public double calculaPontuacaoPorParticipacaoPEDExtensaoPET(double acumuladorGraduandoPEDExtensaoPET,
			Participacao participacao) {
		if (acumuladorGraduandoPEDExtensaoPET != 8) {
			acumuladorGraduandoPEDExtensaoPET += participacao.calculaPontuacao();
			if (acumuladorGraduandoPEDExtensaoPET > 8) {
				return 8.0;
			}
			return acumuladorGraduandoPEDExtensaoPET;
		}
		return 8.0;
	}

	public double calculaPontuacaoPorParticipacaoMonitoria(double acumuladorGraduandoMonitoria,
			Participacao participacao) {
		if (acumuladorGraduandoMonitoria != 6) {
			acumuladorGraduandoMonitoria += participacao.calculaPontuacao();
			if (acumuladorGraduandoMonitoria > 6) {
				return 6.0;
			}
			return acumuladorGraduandoMonitoria;
		}
		return 6.0;
	}

	public double getValorBolsa() {
		int acumulador = 0;
		for (Participacao participacao : participacoes) {
			acumulador += participacao.getBolsa();
		}
		if (acumulador < 350) {
			return 350;
		}
		return acumulador;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O NOME DA PESSOA.
	 * 
	 * @return RETORNARÁ O NOME DA PESSOA.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO MUDAR O NOME DE PESSOA.
	 * 
	 * @param email
	 *            NOVO NOME.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O EMAIL DA PESSOA.
	 * 
	 * @return RETORNARÁ O EMAIL DA PESSOA.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO MUDAR O EMAIL DE PESSOA.
	 * 
	 * @param email
	 *            NOVO EMAIL.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O CPF DA PESSOA.
	 * 
	 * @return RETORNARÁ O CPF DA PESSOA.
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO MUDAR O CPF DE PESSOA.
	 * 
	 * @param email
	 *            NOVO CPF.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getParticipacoes() {
		String listaParticipacoes = "";
		for (Participacao participacao : participacoes) {
			listaParticipacoes += participacao.getNomeDoProjeto() + ", ";
		}
		if (listaParticipacoes.endsWith(", ")) {
			return listaParticipacoes.substring(0, listaParticipacoes.length() - 2);
		}
		return listaParticipacoes;
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

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR UMA STRING COM TODAS AS INFORMAÇÕES
	 * DE PESSOA.
	 */
	@Override
	public String toString() {
		return ("Nome: " + this.nome + ", e-mail: " + this.email + ", CPF: " + this.cpf);
	}

}