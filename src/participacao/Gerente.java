package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class Gerente extends Profissional {

	public Gerente(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	@Override
	public int calculaPontuacaoProfissional() {
		//calcular Gerente PED
		return 0;
	}
	
	
	

}
