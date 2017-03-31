package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class Gerente extends Profissional {

	public Gerente(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * o metodo tem como objetivo calcular o os pontos, que sera 9 por ano.
	 * 
	 * @return - retornara os pontos referentes ao participacao, especificamente
	 *         de "gerente".
	 */
	@Override
	public double calculaPontuacaoProfissional() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao() / 12);
		int pontos = tempoRemunerado * 9;
		return pontos;
	}

	/**
	 * TODO
	 */
	@Override
	public double getBolsa() {
		int numeroDeParticipantes = super.getNumeroDeParticipantes();
		if (numeroDeParticipantes <= 5) {
			return super.getBolsa() + 20 * numeroDeParticipantes;
		}
		return super.getBolsa() + 100;
	}

}
