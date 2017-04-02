package profissional;

import java.io.Serializable;

import exception.LogicaException;
import pessoa.Pessoa;
import projeto.Projeto;

public class FactoryDeProfissional implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Profissional criaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras)
			throws LogicaException {
		if (cargo.equalsIgnoreCase("desenvolvedor")) {
			return criaDesenvolvedor(pessoa, projeto, valorHora, qntHoras);
		} else if (cargo.equalsIgnoreCase("gerente")) {
			return criaGerente(pessoa, projeto, valorHora, qntHoras);
		} else if (cargo.equalsIgnoreCase("pesquisador")) {
			return criaPesquisador(pessoa, projeto, valorHora, qntHoras);
		}
		throw new LogicaException("Tipo de profissional nao existe");
	}

	/**
	 * Metodo responsavel por criar um novo desenvolvedor.
	 * 
	 * @param pessoa
	 *            - Pessoa associada a participacao.
	 * @param projeto
	 *            - Projeto associado a participacao.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornara um novo desenvolvedor.
	 */
	private Desenvolvedor criaDesenvolvedor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new Desenvolvedor(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * Metodo responsavel por criar um novo gerente.
	 * 
	 * @param pessoa
	 *            - Pessoa associada a participacao.
	 * @param projeto
	 *            - Projeto associado a participacao.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornara um novo gerente.
	 */
	private Gerente criaGerente(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new Gerente(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * Metodo responsavel por criar um novo pesquisador.
	 * 
	 * @param pessoa
	 *            - Pessoa associada a participacao.
	 * @param projeto
	 *            - Projeto associado a participacao.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornara um novo pesquisador.
	 */
	private Pesquisador criaPesquisador(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new Pesquisador(pessoa, projeto, valorHora, qntHoras);
	}
}
