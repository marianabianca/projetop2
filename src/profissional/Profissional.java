package profissional;

import participacao.Participacao;
import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoPED;

public abstract class Profissional extends Participacao {

	public Profissional(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	@Override
	public int calculaPontuacao() {
		if (super.projeto.getClass() == ProjetoPED.class){
			return calculaPontuacaoProfissional();
		}else {
			return 0;
		}
	}
	
	public abstract int calculaPontuacaoProfissional();
}
