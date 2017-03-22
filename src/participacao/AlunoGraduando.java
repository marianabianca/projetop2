package participacao;

import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoMonitoria;

public class AlunoGraduando extends Participacao {

	public AlunoGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CALCULAR O OS PONTOS POR SEMESTRE. CASO SEJA
	 * MONITORIA, SERÁ 1,5 PONTOS A CADA SEMESTRE, CASO SEJA OUTRO TIPO, SERÁ 2
	 * PONTOS A CADA SEMESTRE.
	 * 
	 * @return RETORNARÁ OS PONTOS REFERENTES AO PARTICIPACÃO, ESPECIFICAMENTE
	 *         DE "AlunoGraduando".
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

	@Override
	public double getModificadorBolsa() {
		return 1;
	}

	@Override
	public boolean isAlunoGraduando() {
		return true;
	}

	@Override
	public boolean isProfessor() {
		return false;
	}

}
