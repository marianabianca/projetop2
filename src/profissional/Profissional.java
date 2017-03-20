package profissional;

import participacao.Participacao;
import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoPED;

public abstract class Profissional extends Participacao {

	public Profissional(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	public abstract double calculaPontuacaoProfissional();

	@Override
	public double calculaPontuacao() {
		if (super.projeto.getClass() == ProjetoPED.class) {
			return calculaPontuacaoProfissional();
		}
		return 0;
	}

	@Override
	public double getModificadorBolsa() {
		return 1;
	}

}
