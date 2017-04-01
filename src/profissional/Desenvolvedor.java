package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class Desenvolvedor extends Profissional {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Desenvolvedor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * o metodo tem como objetivo calcular o os pontos, que sera 5 por ano.
	 * 
	 * @return - retornara os pontos referentes ao participacao, especificamente
	 *         de "desenvolvedor".
	 */
	@Override
	public double calculaPontuacaoProfissional() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao() / 12);
		int pontos = tempoRemunerado * 5;
		return pontos;
	}
}
