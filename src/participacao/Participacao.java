package participacao;

import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoMonitoria;

public abstract class Participacao implements Comparable<Participacao> {

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
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O NOME DA PARTICIPAÇÃO.
	 * 
	 * @return RETORNARÁ O NOME.
	 */
	public String getNomeDaPessoa() {
		return this.pessoa.getNome();
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O CPF DA PARTICIPAÇÃO.
	 * 
	 * @return RETORNARÁ O CPF.
	 */
	public String getCpfDaPessoa() {
		return this.pessoa.getCpf();
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR A PESSOA DA PARTICIPAÇÃO.
	 * 
	 * @return RETORNARÁ A PESSOA.
	 */
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O NOME DO PROJETO REFERENTE A
	 * PARTICIPAÇÃO.
	 * 
	 * @return RETORNARÁ O NOME.
	 */
	public String getNomeDoProjeto() {
		return this.projeto.getNome();
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O CÓDIGO DO PROJETO REFERENTE A
	 * PARTICIPAÇÃO.
	 * 
	 * @return RETORNARÁ O CÓDIGO.
	 */
	public String getCodigoDoProjeto() {
		return this.projeto.getCodigo();
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O NÚMERO DE PARTICIPANTES DO PROJETO
	 * REFERENTE A PARTICIPAÇÃO.
	 * 
	 * @return RETORNARÁ O NÚMERO DE PESSOAS.
	 */
	public int getNumeroDeParticipantes() {
		return this.projeto.getNumeroDeParticipantes();
	}

	/**
	 * O MÉTODO TEM COM OBJETIVO DIZER SE O PROJETO É REFERENTE A MONITORIA OU
	 * NÃO.
	 * 
	 * @return RETORNA SE É MONITORIA(TRUE) OU SE NÃO É(FALSE).
	 */
	public boolean isMonitoria() {
		return projeto.isMonitoria();
	}

	/**
	 * O MÉTODO TEM COM OBJETIVO DIZER SE O PROJETO É REFERENTE A PED OU NÃO.
	 * 
	 * @return RETORNA SE É PED(TRUE) OU SE NÃO É(FALSE).
	 */
	public boolean isPED() {
		return projeto.isPED();
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O VALOR DA HORA DA PARTICIPAÇÃO.
	 * 
	 * @return RETORNARÁ O VALOR DA HORA.
	 */
	public double getValorDaHora() {
		return this.valorHora;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O QUANTIDADE DE HORAS SEMANAIS DA
	 * PARTICIPAÇÃO.
	 * 
	 * @return RETORNARÁ A QUANTIDADE DE HORAS SEMANAIS.
	 */
	public int getQntHoras() {
		return this.qntHoras;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CALCULAR OS PONTOS DE PARTICIPACAO EM CLASSES
	 * FILHAS, QUE PODEM SER: "Professor", "AlunoGraduando", "AlunoGraduando" E
	 * "Profissional" (COM SUAS FILHAS).
	 * 
	 * @return RETORNA OS PONTOS DA PARTICIPACÃO.
	 */
	public abstract double calculaPontuacao();

	public abstract double getModificadorBolsa();

	public double getBolsa() {
		return Math.ceil(this.getModificadorBolsa() * this.qntHoras * this.valorHora);
	}

	@Override
	public int compareTo(Participacao outra) {
		return this.pessoa.getNome().compareTo(outra.getPessoa().getNome());
	}

	/**
	 * O MÉTODO TEM COM OBJETIVO DIZER SE A PARTICIPACÃO É REFERENTE A ALUNO
	 * GRADUANDO OU NÃO.
	 * 
	 * @return RETORNA SE É ALUNO GRADUANDO(TRUE) OU SE NÃO É(FALSE).
	 */
	public abstract boolean isAlunoGraduando();

	/**
	 * O MÉTODO TEM COM OBJETIVO DIZER SE A PARTICIPACÃO É REFERENTE A PROFESSOR
	 * OU NÃO.
	 * 
	 * @return RETORNA SE É PROFESSOR(TRUE) OU SE NÃO É(FALSE).
	 */
	public abstract boolean isProfessor();
}