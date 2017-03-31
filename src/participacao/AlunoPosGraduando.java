package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class AlunoPosGraduando extends Participacao {

	private static final long serialVersionUID = 1L;
	private String vinculo;

	public AlunoPosGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras, String vinculo) {
		super(pessoa, projeto, valorHora, qntHoras);
		this.vinculo = vinculo;
	}

	/**
	 * o metodo tem como objetivo garantir que os pontos referentes a
	 * participacao do "alunoposgraduando" sera sempre zero.
	 * 
	 * @return - retornara os pontos sempre zero.
	 */
	@Override
	public double calculaPontuacao() {
		return 0;
	}

	/**
	 * TODO
	 */
	@Override
	public double getModificadorBolsa() {
		if (vinculo.equalsIgnoreCase("doutorado")) {
			return 4.0 / 3.0;
		}
		return 1;
	}

}
