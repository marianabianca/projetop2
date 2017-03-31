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
	 * Metodo responsavel por criar um novo professor.
	 * 
	 * @param pessoa
	 *            - Pessoa associada a participacao.
	 * @param projeto
	 *            - Projeto associado a participacao.
	 * @param coordenador
	 *            - Se e coordenador.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornara um novo professor.
	 */
	public Professor criaProfessor(Pessoa pessoa, Projeto projeto, boolean coordenador, double valorHora,
			int qntHoras) {
		return new Professor(pessoa, projeto, valorHora, qntHoras, coordenador);
	}

	/**
	 * Metodo responsavel por criar um novo aluno graduando.
	 * 
	 * @param pessoa
	 *            - Pessoa associada a participacao.
	 * @param projeto
	 *            - Projeto associado a participacao.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornara um novo alundo graduando.
	 */
	public AlunoGraduando criaGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new AlunoGraduando(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * Metodo responsavel por criar um novo profissional e verificar se o cargo
	 * e valido.
	 * 
	 * @param pessoa
	 *            - Pessoa associada a participacao.
	 * @param projeto
	 *            - Projeto associado a participacao.
	 * @param cargo
	 *            - Cargo do profissional.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @return - Retornara um novo profissional.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public Profissional criaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras)
			throws Exception {
		return factoryDeProfissional.criaProfissional(pessoa, projeto, cargo, valorHora, qntHoras);
	}

	/**
	 * Metodo responsavel por criar um novo aluno pos-graduando.
	 * 
	 * @param pessoa
	 *            - Pessoa associada a participacao.
	 * @param projeto
	 *            - Projeto associado a participacao.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @param vinculo
	 *            - Vinculo do aluno pos-graduando.
	 * @return - Retornara um novo aluno pos-graduando.
	 */
	public Participacao criaPosGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras,
			String vinculo) {
		return new AlunoPosGraduando(pessoa, projeto, valorHora, qntHoras, vinculo);
	}

}
