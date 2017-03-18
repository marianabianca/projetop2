package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class FactoryDeParticipacao {

	public Professor criaProfessor(Pessoa pessoa, Projeto projeto, boolean coordenador, double valorHora, int qntHoras) {
		return new Professor(pessoa, projeto, valorHora, qntHoras, coordenador);
	}

	public AlunoGraduando criaGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new AlunoGraduando(pessoa, projeto, valorHora, qntHoras);
	}

	public Profissional criaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras) {
		return new Profissional(pessoa, projeto, valorHora, qntHoras, cargo);
	}

}
