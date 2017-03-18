package profissional;

import participacao.Participacao;
import pessoa.Pessoa;
import projeto.Projeto;

public class Pesquisador extends Profissional {

	public Pesquisador(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	@Override
	public int calculaPontuacaoProfissional() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
