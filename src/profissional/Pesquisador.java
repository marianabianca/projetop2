package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class Pesquisador extends Profissional {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pesquisador(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * o metodo tem como objetivo calcular o os pontos, que sera 6 por ano.
	 * 
	 * @return - retornara os pontos referentes ao participacao, especificamente
	 *         de "pesquisador".
	 */
	@Override
	public double calculaPontuacaoProfissional() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao() / 12);
		int pontos = tempoRemunerado * 6;
		return pontos;
	}

	/**
	 * o metodo calcula a bolsa que paticipacao porporciona.
	 * 
	 * @return double - bolsa que a paticipacao porporciona.
	 */
	@Override
	public double getBolsa() {
		double bolsa = super.getBolsa() + 100;
		if (bolsa < 350) {
			return 350;
		} else {
			return bolsa;
		}
	}

}
