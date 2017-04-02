package participacao;

import java.io.Serializable;

import pessoa.Pessoa;
import projeto.Projeto;

public abstract class Participacao implements Comparable<Participacao>, Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	protected Projeto projeto;
	private double valorHora;
	private int qntHoras;

	public Participacao(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		this.pessoa = pessoa;
		this.projeto = projeto;
		this.valorHora = valorHora;
		this.qntHoras = qntHoras;
	}

	/**
	 * o metodo tem como objetivo retornar o nome da participacao.
	 * 
	 * @return - retornara o nome.
	 */
	public String getNomeDaPessoa() {
		return this.pessoa.getNome();
	}

	/**
	 * o metodo tem como objetivo retornar o cpf da participacao.
	 * 
	 * @return - retornara o cpf.
	 */
	public String getCpfDaPessoa() {
		return this.pessoa.getCpf();
	}

	/**
	 * o metodo tem como objetivo retornar a pessoa da participacao.
	 * 
	 * @return - retornara a pessoa.
	 */
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	/**
	 * o metodo tem como objetivo retornar o nome do projeto referente a
	 * participacao.
	 * 
	 * @return - retornara o nome.
	 */
	public String getNomeDoProjeto() {
		return this.projeto.getNome();
	}

	/**
	 * o metodo tem como objetivo retornar o codigo do projeto referente a
	 * participacao.
	 * 
	 * @return - retornara o codigo.
	 */
	public String getCodigoDoProjeto() {
		return this.projeto.getCodigo();
	}

	/**
	 * o metodo tem como objetivo retornar o numero de participantes do projeto
	 * referente a participacao.
	 * 
	 * @return - retornara o numero de pessoas.
	 */
	public int getNumeroDeParticipantes() {
		return this.projeto.getNumeroDeParticipantes();
	}

	/**
	 * o metodo tem com objetivo dizer se o projeto eh referente a monitoria ou
	 * nao.
	 * 
	 * @return - retorna se eh monitoria(true) ou se nao eh(false).
	 */
	public boolean isMonitoria() {
		return projeto.isMonitoria();
	}

	/**
	 * o metodo tem com objetivo dizer se o projeto eh referente a PED ou nao.
	 * 
	 * @return - retorna se eh PED(true) ou se nao eh(false).
	 */
	public boolean isPED() {
		return projeto.isPED();
	}

	public boolean isExtensao() {
		return projeto.isExtensao();
	}

	/**
	 * o metodo tem como objetivo retornar o valor da hora da participacao.
	 * 
	 * @return - retornara o valor da hora.
	 */
	public double getValorDaHora() {
		return this.valorHora;
	}

	/**
	 * o metodo tem como objetivo retornar o quantidade de horas semanais da
	 * participacao.
	 * 
	 * @return - retornara a quantidade de horas semanais.
	 */
	public int getQntHoras() {
		return this.qntHoras;
	}

	/**
	 * o metodo tem como objetivo calcular os pontos de participacao em classes
	 * filhas, que podem ser: "professor", "alunograduando", "alunograduando" e
	 * "profissional" (com suas filhas).
	 * 
	 * @return - retorna os pontos da participacao.
	 */
	public abstract double calculaPontuacao();

	/**
	 * TODO
	 * 
	 * @return
	 */
	public abstract double getModificadorBolsa();

	/**
	 * oo metodo calcula a bolsa que a pessoa recebe
	 * 
	 * @return double - o valor da bolsa
	 */
	public double getBolsa() {
		double bolsa = Math.ceil(this.getModificadorBolsa() * this.qntHoras * this.valorHora);
		if (bolsa < 350) {
			bolsa = 350;
		}
		return bolsa;
	}

	/**
	 * Metodo responsavel por comparar nome da pessoa da participacao.
	 */
	@Override
	public int compareTo(Participacao outra) {
		return this.pessoa.getNome().compareTo(outra.getPessoa().getNome());
	}

	/**
	 * metodo resonsavel por definir se a participacao eh alundo graduando,
	 * tendo como default falso e na filha aluno graduando ha um override
	 * afirmando verdadeiro.
	 * 
	 * @return - Por default, falso.
	 */
	public boolean isAlunoGraduando() {
		return false;
	}

	/**
	 * metodo resonsavel por definir se a participacao eh alundo pos graduando,
	 * tendo como default falso e na filha aluno pos graduando ha um override
	 * afirmando verdadeiro.
	 * 
	 * @return - Por default, falso.
	 */
	public boolean isAlunoPosGraduando() {
		return false;
	}

	/**
	 * metodo resonsavel por definir se a participacao eh professor, tendo como
	 * default falso e na filha professor ha um override afirmando verdadeiro.
	 * 
	 * @return - Por default, falso.
	 */
	public boolean isProfessor() {
		return false;
	}
	
	// TODO JAVADOCS
	public boolean isCoordenador() {
		return false;
	}

	public boolean isProfissional() {
		return false;
	}
}