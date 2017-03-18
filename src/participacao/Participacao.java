package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public abstract class Participacao {
	
	private Pessoa pessoa;
	private Projeto projeto;
	private double valorHora;
	private int qntHoras;

	public Participacao(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		this.pessoa = pessoa;
		this.projeto = projeto;
		this.valorHora = valorHora;
		this.qntHoras = qntHoras;
	}

}
