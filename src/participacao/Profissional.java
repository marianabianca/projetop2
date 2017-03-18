package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class Profissional extends Participacao {
	
	private String cargo;

	public Profissional(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras, String cargo) {
		super(pessoa, projeto, valorHora, qntHoras);
		this.cargo = cargo;
	}

}
