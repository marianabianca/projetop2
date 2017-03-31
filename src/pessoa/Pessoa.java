package pessoa;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import exception.LogicaException;
import participacao.AlunoGraduando;
import participacao.Participacao;

public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nome, email, cpf;
	private List<Participacao> participacoes;

	public Pessoa(String nome, String email, String cpf) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.participacoes = new ArrayList<>();
	}

	/**
	 * O metodo eh responsavel por adicionar participacoes dentro de pessoa.
	 * 
	 * @param participacao
	 *            - pariticipacao a ser adicionada.
	 */
	public void adicionaParticipacao(Participacao participacao) {
		this.participacoes.add(participacao);
	}

	/**
	 * Metodo responsavel por remover a pariticipacao da pessoa.
	 * 
	 * @param codigoProjeto
	 *            - codigo do projeto, o qual será tirada a participacao.
	 * @throws LogicaException
	 *             - caso o parametro nao seja o esperado, lancara uma excecao.
	 */
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

	/**
	 * Metodo responsavel por avaliar se ha pariticipacao no projeto
	 * especificado.
	 * 
	 * @param codigoProjeto
	 *            - codigo do projeto a ser analizado.
	 * @return - se ha(true) ou nao(false).
	 */
	public boolean temParticipacaoEmProjeto(String codigoProjeto) {
		for (Participacao participacao : participacoes) {
			if (participacao.getCodigoDoProjeto().equals(codigoProjeto)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * metodo responsavel por calcular o numero de pontos de uma pessoa.
	 * 
	 * @return - retorna a quantidade final de pontos.
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

	/**
	 * Metodo auxiliar de "calculaPontuacaoPorParticipacao" com o objetivo de
	 * calcular todos os projetos que sejam de aluno graduando e que nao sao
	 * monitoria, o qual pode no maximo acumular 8 pontos.
	 * 
	 * @param acumuladorGraduandoPEDExtensaoPET
	 *            - acumulador de pontos recebido de
	 *            "calculaPontuacaoPorParticipacao".
	 * @param participacao
	 *            - participacao a ser calculada de
	 *            "calculaPontuacaoPorParticipacao".
	 * @return - chama o metodo "calculaPontuacao" de pariticipacao.
	 */
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

	/**
	 * Metodo auxiliar de "calculaPontuacaoPorParticipacao" com o objetivo de
	 * calcular todos os projetos que sejam de aluno graduando e que sao
	 * monitoria, o qual pode no maximo acumular 6 pontos.
	 * 
	 * @param acumuladorGraduandoMonitoria
	 *            - acumulador de pontos recebido de
	 *            "calculaPontuacaoPorParticipacao".
	 * @param participacao
	 *            - participacao a ser calculada de
	 *            "calculaPontuacaoPorParticipacao".
	 * @return - chama o metodo "calculaPontuacao" de pariticipacao.
	 */
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

	/**
	 * Metodo responsavel por acumular o valor da bolsa de uma pessoa.
	 * 
	 * @return - retornara o valor da bolsa em double.
	 */
	public double getValorBolsa() {
		int acumulador = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.getBolsa() < 350) {
				acumulador += 350;
			}	else { 
				acumulador += participacao.getBolsa();
			}
		}
		return acumulador;
	}                        

	/**
	 * o metodo tem como objetivo retornar o nome da pessoa.
	 * 
	 * @return - retornara o nome da pessoa.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * o metodo tem como objetivo mudar o nome de pessoa.
	 * 
	 * @param nome
	 *            - novo nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * o metodo tem como objetivo retornar o email da pessoa.
	 * 
	 * @return - retornara o email da pessoa.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * o metodo tem como objetivo mudar o email de pessoa.
	 * 
	 * @param email
	 *            - novo email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * o metodo tem como objetivo retornar o cpf da pessoa.
	 * 
	 * @return - retornara o cpf da pessoa.
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * o metodo tem como objetivo mudar o cpf de pessoa.
	 * 
	 * @param cpf
	 *            - novo cpf.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * metodo responsavel por definir as participacoes em uma String.
	 * 
	 * @return - retornara a String de lista de participacoes.
	 */
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

	/**
	 * Hashcode com CPF sendo referencia.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	/**
	 * Equals com CPF sendo referencia.
	 */
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
	 * o metodo tem como objetivo retornar uma string com todas as informacoes
	 * de pessoa.
	 */
	@Override
	public String toString() {
		return ("Nome: " + this.nome + ", e-mail: " + this.email + ", CPF: " + this.cpf);
	}

}