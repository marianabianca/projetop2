package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class AlunoGraduando extends Participacao {

	private static final long serialVersionUID = 1L;

	public AlunoGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * o metodo tem como objetivo calcular o os pontos por semestre. caso seja
	 * monitoria, sera 1,5 pontos a cada semestre, caso seja outro tipo, sera 2
	 * pontos a cada semestre.
	 * 
	 * @return - retornara os pontos referentes ao participacao, especificamente
	 *         de "alunograduando".
	 */
	@Override
	public double calculaPontuacao() {
		int tempoRemunerado = (int) Math.floor(super.projeto.getDuracao() / 6);
		int pontos = 0;
		if (isMonitoria()) {
			pontos = (int) Math.floor(tempoRemunerado * 1.5);
		} else {
			pontos = (int) Math.floor(tempoRemunerado * 2);
		}
		return pontos;
	}

	/**
	 * TODO
	 */
	@Override
	public double getModificadorBolsa() {
		return 1;
	}

	/**
	 * Metodo responsavel por quebrar o default da classe pai.
	 */
	@Override
	public boolean isAlunoGraduando() {
		return true;
	}

}
