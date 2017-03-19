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

	@Override
	public double calculaPontuacao() {
		if (super.projeto.getClass() == ProjetoMonitoria.class) {
			return 4;
		}else{
			return (super.projeto.calculaPontuacao() + 4);		
		}
	}
	
	public boolean isCoordenador() {
		return coordenador;
	}
}
