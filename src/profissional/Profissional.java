package profissional;

import participacao.Participacao;
import pessoa.Pessoa;
import projeto.Projeto;

public abstract class Profissional extends Participacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Profissional(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * O metodo tem como objetivo calcular os pontos de participacao em classes
	 * filhas, que podem ser: "gerente", "pesquisador" e "desenvolvedor".
	 * 
	 * @return - retorna os pontos da participacao.
	 */
	public abstract double calculaPontuacaoProfissional();

	/**
	 * Metodo responsavel por definir se o projeto em questao eh PED, caso nao
	 * seja, retornara zero.
	 */
	@Override
	public double calculaPontuacao() {
		if (isPED()) {
			return calculaPontuacaoProfissional();
		}
		return 0;
	}

	/**
	 * Metodo responsavel por retornar o adicional da bolsa, como nao ha, o
	 * retorno sera sempre um.
	 */
	@Override
	public double getModificadorBolsa() {
		return 1;
	}
	
	/**
	 * Metodo responsavel por quebrar o default da classe pai.
	 */
	@Override
	public boolean isProfissional() {
		return true;
	}

}
