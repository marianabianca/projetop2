package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class Gerente extends Profissional {

	public Gerente(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CALCULAR O OS PONTOS, QUE SERÁ 9 POR ANO.
	 * 
	 * @return RETORNARÁ OS PONTOS REFERENTES AO PARTICIPACÃO, ESPECIFICAMENTE
	 *         DE "Gerente".
	 */
	@Override
	public double calculaPontuacaoProfissional() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao() / 12);
		int pontos = tempoRemunerado * 9;
		return pontos;
	}

	@Override
	public double getBolsa() {
		int numeroDeParticipantes = super.getNumeroDeParticipantes();
		if (numeroDeParticipantes <= 5) {
			return super.getBolsa() + 20 * numeroDeParticipantes;
		}
		return super.getBolsa() + 100;
	}

}
