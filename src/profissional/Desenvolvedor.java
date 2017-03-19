package profissional;

import pessoa.Pessoa;
import projeto.Projeto;

public class Desenvolvedor extends Profissional{

	public Desenvolvedor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	@Override
	public double calculaPontuacaoProfissional() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao()/12);
		int pontos = tempoRemunerado * 5;
		return pontos;
	}

}
