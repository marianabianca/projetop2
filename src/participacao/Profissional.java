package participacao;

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
			calculaPontuacaoProfissional();
		}else{
			return 0;
		}
		return 0;
	}
	
	public abstract int calculaPontuacaoProfissional();
}
