package participacao;

import exception.LogicaException;
import exception.ParametroInvalidoException;
import pessoa.Pessoa;
import projeto.Projeto;

public class AlunoPosGraduando extends Participacao {

	private static final long serialVersionUID = 1L;
	private String vinculo;

	public AlunoPosGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras, String vinculo)
			throws LogicaException {
		super(pessoa, projeto, valorHora, qntHoras);
		if (projeto.isPED()) {
			if (!projeto.getInfoProjeto("categoria").equalsIgnoreCase("coop")) {
				throw new ParametroInvalidoException("Tipo de projeto invalido para pos graduando");
			}
		}
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
	 * Metodo responsavel por retornar o adicional da bolsa que, no caso de ser
	 * doutorando, ganhara 1/3 de adicional.
	 */
	@Override
	public double getModificadorBolsa() {
		if (vinculo.equalsIgnoreCase("doutorado")) {
			return 4.0 / 3.0;
		}
		return 1;
	}

	/**
	 * Metodo responsavel por quebrar o default da classe pai.
	 */
	@Override
	public boolean isAlunoPosGraduando() {
		return true;
	}

}
