package participacao;

import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoMonitoria;

public class Professor extends Participacao {

	private boolean coordenador;

	public Professor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras, boolean coordenador) {
		super(pessoa, projeto, valorHora, qntHoras);
		this.coordenador = coordenador;
	}

	/**
	 * Método responsável por analizar se é um coordenador.
	 * 
	 * @return true, se for coordenador, false, se não.
	 */
	public boolean isCoordenador() {
		return this.coordenador;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CALCULAR O OS PONTOS POR ANO. CASO SEJA
	 * MONITORIA, SERÁ QUATRO PONTOS A CADA ANO, SENDO OUTRO TIPO(PET, PED,
	 * EXTENSÃO), SERÁ QUATRO PONTOS A CADA ANO E MAIS UM PONTO POR CADA ALUNO
	 * PARTICIPANTE DO PROJETO.
	 * 
	 * @return RETORNARÁ OS PONTOS REFERENTES AO PARTICIPACÃO, ESPECIFICAMENTE
	 *         DE "Professor".
	 */
	@Override
	public double calculaPontuacao() {
		double porAno = (projeto.getDuracao() / 12);
		if (isMonitoria()) {
			return (porAno * 4);
		} else {
			return (super.projeto.calculaPontuacao() + (porAno * 4));
		}
	}

	/**
	 * TODO
	 */
	@Override
	public double getModificadorBolsa() {
		if (this.coordenador) {
			return 1.4;
		}
		return 1;
	}

	/**
	 * Método responsável por quebrar o default da classe pai.
	 */
	@Override
	public boolean isProfessor() {
		return true;
	}
}
