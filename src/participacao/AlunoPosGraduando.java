package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class AlunoPosGraduando extends Participacao {
	
	private String vinculo;

	public AlunoPosGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras, String vinculo) {
		super(pessoa, projeto, valorHora, qntHoras);
		this.vinculo = vinculo;
	}

	@Override
	public int calculaPontuacao() {
		return 0;
	}

}
