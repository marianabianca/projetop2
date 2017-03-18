package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class Professor extends Participacao {

	private boolean coordenador;

	public Professor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras, boolean coordenador) {
		super(pessoa, projeto, valorHora, qntHoras);
		this.coordenador = coordenador;
	}

}
