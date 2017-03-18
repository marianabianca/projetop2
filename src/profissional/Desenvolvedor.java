package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class Desenvolvedor extends Profissional{

	public Desenvolvedor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	@Override
	public int calculaPontuacaoProfissional() {
		//calcular Desenvolvedor PED
		return 0;
	}

}
