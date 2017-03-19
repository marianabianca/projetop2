package participacao;

import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoMonitoria;

public class AlunoGraduando extends Participacao {

	public AlunoGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	@Override
	public double calculaPontuacao() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao() / 6);
		int pontos = 0;
		if (super.projeto.getClass() == ProjetoMonitoria.class) {
			pontos = (int) Math.floor(tempoRemunerado * 1.5);
		} else {
			pontos = (int) Math.floor(tempoRemunerado * 2);
		}
		return pontos;
	}

}
