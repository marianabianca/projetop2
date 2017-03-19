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
	public double calculaPontuacao() {
		return 0;
	}
	
	@Override
	public double getModificadorBolsa() {
		if (vinculo.equalsIgnoreCase("doutorado")) {
			return 4.0/3.0;
		}
		return 1;
	}

}
