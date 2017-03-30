package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class FactoryDeProfissional {

	public Profissional criaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras)
			throws Exception {
		if (cargo.equalsIgnoreCase("desenvolvedor")) {
			return criaDesenvolvedor(pessoa, projeto, valorHora, qntHoras);
		} else if (cargo.equalsIgnoreCase("gerente")) {
			return criaGerente(pessoa, projeto, valorHora, qntHoras);
		} else if (cargo.equalsIgnoreCase("pesquisador")) {
			return criaPesquisador(pessoa, projeto, valorHora, qntHoras);
		}
		throw new Exception("Tipo de profissional nao existe");
	}

	/**
	 * Método responsável por criar um novo desenvolvedor.
	 * 
	 * @param pessoa
	 *            - Pessoa associada à participação.
	 * @param projeto
	 *            - Projeto associado à participação.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornará um novo desenvolvedor.
	 */
	private Desenvolvedor criaDesenvolvedor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new Desenvolvedor(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * Método responsável por criar um novo gerente.
	 * 
	 * @param pessoa
	 *            - Pessoa associada à participação.
	 * @param projeto
	 *            - Projeto associado à participação.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornará um novo gerente.
	 */
	private Gerente criaGerente(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new Gerente(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * Método responsável por criar um novo pesquisador.
	 * 
	 * @param pessoa
	 *            - Pessoa associada à participação.
	 * @param projeto
	 *            - Projeto associado à participação.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornará um novo pesquisador.
	 */
	private Pesquisador criaPesquisador(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new Pesquisador(pessoa, projeto, valorHora, qntHoras);
	}
}
