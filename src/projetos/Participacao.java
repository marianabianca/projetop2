package projetos;

import java.util.HashSet;
import java.util.Set;

import pessoas.Pessoa;

public class Participacao {
	private Set<Pessoa> participantes;
	private Projeto projetoDeParticipacao;
	
	public Participacao(){
		participantes = new HashSet<Pessoa>();
	}
	
	public Projeto getProjetoDeParticipacao() {
		return projetoDeParticipacao;
	}
	public void setProjetoDeParticipacao(Projeto projetoDeParticipacao) {
		this.projetoDeParticipacao = projetoDeParticipacao;
	}

	public Set<Pessoa> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Pessoa> participantes) {
		this.participantes = participantes;
	}
	
	public void adicionarParticipante(Pessoa participanteAAdicionar){
		// TRATAMENTO
		
		
	}
	
}
