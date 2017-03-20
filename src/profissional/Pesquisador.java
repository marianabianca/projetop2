package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class Pesquisador extends Profissional {

	public Pesquisador(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CALCULAR O OS PONTOS, QUE SERÁ 6 POR ANO.
	 * @return RETORNARÁ OS PONTOS REFERENTES AO PARTICIPACÃO, ESPECIFICAMENTE DE "Pesquisador".
	 */
	@Override
	public double calculaPontuacaoProfissional() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao() / 12);
		int pontos = tempoRemunerado * 6;
		return pontos;
	}

	@Override
	public double getBolsa() {
		int numeroDeParticipantes = super.getNumeroDeParticipantes();
		if (numeroDeParticipantes <= 5) {
			return super.getBolsa() + 100 * numeroDeParticipantes;
		}
		return super.getBolsa() + 500;
	}

}
