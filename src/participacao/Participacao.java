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

	public String getNomeDaPessoa() {
		return this.pessoa.getNome();
	}

	public String getCpfDaPessoa() {
		return this.pessoa.getCpf();
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public String getNomeDoProjeto() {
		return this.projeto.getNome();
	}

	public String getCodigoDoProjeto() {
		return this.projeto.getCodigo();
	}

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
		if (this.projeto.getClass() == ProjetoMonitoria.class) {
			return true;
		}
		return false;
	}

	public double getValorDaHora() {
		return this.valorHora;
	}

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

}