package participacao;

import java.io.Serializable;

import pessoa.Pessoa;
import projeto.Projeto;
import profissional.FactoryDeProfissional;
import profissional.Profissional;

public class FactoryDeParticipacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FactoryDeProfissional factoryDeProfissional;

	public FactoryDeParticipacao() {
		factoryDeProfissional = new FactoryDeProfissional();
	}

	/**
	 * Método responsável por criar um novo professor.
	 * 
	 * @param pessoa
	 *            - Pessoa associada à participação.
	 * @param projeto
	 *            - Projeto associado à participação.
	 * @param coordenador
	 *            - Se é coordenador.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornará um novo professor.
	 */
	public Professor criaProfessor(Pessoa pessoa, Projeto projeto, boolean coordenador, double valorHora,
			int qntHoras) {
		return new Professor(pessoa, projeto, valorHora, qntHoras, coordenador);
	}

	/**
	 * Método responsável por criar um novo aluno graduando.
	 * 
	 * @param pessoa
	 *            - Pessoa associada à participação.
	 * @param projeto
	 *            - Projeto associado à participação.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornará um novo alundo graduando.
	 */
	public AlunoGraduando criaGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new AlunoGraduando(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * Método responsável por criar um novo profissional e verificar se o cargo
	 * é válido.
	 * 
	 * @param pessoa
	 *            - Pessoa associada à participação.
	 * @param projeto
	 *            - Projeto associado à participação.
	 * @param cargo
	 *            - Cargo do profissional.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornará um novo profissional.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public Profissional criaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras)
			throws Exception {
		return factoryDeProfissional.criaProfissional(pessoa, projeto, cargo, valorHora, qntHoras);
	}

	/**
	 * Método responsável por criar um novo aluno pós-graduando.
	 * 
	 * @param pessoa
	 *            - Pessoa associada à participação.
	 * @param projeto
	 *            - Projeto associado à participação.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @param vinculo
	 *            - Vinculo do aluno pós-graduando.
	 * @return - Retornará um novo aluno pós-graduando.
	 */
	public Participacao criaPosGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras,
			String vinculo) {
		return new AlunoPosGraduando(pessoa, projeto, valorHora, qntHoras, vinculo);
	}

}
