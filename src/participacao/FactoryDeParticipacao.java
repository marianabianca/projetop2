package participacao;

import pessoa.Pessoa;
import projeto.Projeto;
import profissional.FactoryDeProfissional;
import profissional.Profissional;

public class FactoryDeParticipacao {

	FactoryDeProfissional factoryDeProfissional;

	public FactoryDeParticipacao() {
		factoryDeProfissional = new FactoryDeProfissional();
	}

	public Professor criaProfessor(Pessoa pessoa, Projeto projeto, boolean coordenador, double valorHora,
			int qntHoras) {
		return new Professor(pessoa, projeto, valorHora, qntHoras, coordenador);
	}

	public AlunoGraduando criaGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		return new AlunoGraduando(pessoa, projeto, valorHora, qntHoras);
	}

	public Profissional criaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras)
			throws Exception {
		return factoryDeProfissional.criaProfissional(pessoa, projeto, cargo, valorHora, qntHoras);
	}

	public Participacao criaPosGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras,
			String vinculo) {
		return new AlunoPosGraduando(pessoa, projeto, valorHora, qntHoras, vinculo);
	}

}
