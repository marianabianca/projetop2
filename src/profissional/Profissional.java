package profissional;

import participacao.Participacao;
import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoPED;

public abstract class Profissional extends Participacao {

	public Profissional(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CALCULAR OS PONTOS DE PARTICIPACÃO EM CLASSES
	 * FILHAS, QUE PODEM SER: "Gerente", "Pesquisador" E "Desenvolvedor".
	 * 
	 * @return RETORNA OS PONTOS DA PARTICIPACÃO.
	 */
	public abstract double calculaPontuacaoProfissional();

	/**
	 * Método responsável por definir se o projeto em questão é PED, caso não
	 * seja, retornará zero.
	 */
	@Override
	public double calculaPontuacao() {
		if (isPED()) {
			return calculaPontuacaoProfissional();
		}
		return 0;
	}

	/**
	 * TODO
	 */
	@Override
	public double getModificadorBolsa() {
		return 1;
	}

}
