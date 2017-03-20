package participacao;

import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoMonitoria;

public class Professor extends Participacao {

	private boolean coordenador;

	public Professor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras, boolean coordenador) {
		super(pessoa, projeto, valorHora, qntHoras);
		this.coordenador = coordenador;
	}

	public boolean isCoordenador() {
		return this.coordenador;
	}

	@Override
	public double calculaPontuacao() {
		double porAno = (projeto.getDuracao() / 12);
		if (super.projeto.getClass() == ProjetoMonitoria.class) {
			return (porAno * 4);
		} else {
			return (super.projeto.calculaPontuacao() + (porAno * 4));
		}
	}

	@Override
	public double getModificadorBolsa() {
		if (this.coordenador) {
			return 1.4;
		}
		return 1;
	}
}
