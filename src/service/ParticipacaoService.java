package service;

import participacao.AlunoGraduando;
import participacao.Professor;
import participacao.Profissional;

public class ParticipacaoService {

	public Professor associaProfessor(boolean coordenador, double valorHora, int qntHoras) {
		return new Professor(valorHora, qntHoras, coordenador);
	}

	public AlunoGraduando associaGraduando(double valorHora, int qntHoras) {
		return new AlunoGraduando(valorHora, qntHoras);
	}

	public Profissional associaProfissional(String cargo, double valorHora, int qntHoras) {
		return new Profissional(valorHora, qntHoras, cargo);
	}

}
