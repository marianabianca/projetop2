package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class Pesquisador extends Participacao {

	public Pesquisador(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	@Override
	public int calculaPontuacao() {
		//calcular Pesquisador PED
		return 0;
	}
	
	
	

}
