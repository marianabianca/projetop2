package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class Desenvolvedor extends Profissional {

	public Desenvolvedor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CALCULAR O OS PONTOS, QUE SERÁ 5 POR ANO.
	 * 
	 * @return RETORNARÁ OS PONTOS REFERENTES AO PARTICIPACÃO, ESPECIFICAMENTE
	 *         DE "Desenvolvedor".
	 */
	@Override
	public double calculaPontuacaoProfissional() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao() / 12);
		int pontos = tempoRemunerado * 5;
		return pontos;
	}

	@Override
	public boolean isAlunoGraduando() {
		return false;
	}

	@Override
	public boolean isProfessor() {
		return false;
	}

}
