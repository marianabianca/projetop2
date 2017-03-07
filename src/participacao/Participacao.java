package participacao;

import java.util.HashSet;
import java.util.Set;

import exceptions.ParticipanteNaoEncontradoException;
import exceptions.ParticipanteNuloException;
import exceptions.StringInvalidaException;
import pessoas.Pessoa;
import projetos.Projeto;
import validacao.ModuloDeValidacao;

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
	
	public void adicionarParticipante(Pessoa participanteAAdicionar) throws ParticipanteNuloException{
		ModuloDeValidacao.ParticipanteNulo(participanteAAdicionar);
		participantes.add(participanteAAdicionar);	
	}
	
	public Pessoa recuperarParticipante(String cpf) throws ParticipanteNaoEncontradoException{
		for (Pessoa participante : participantes){
			if (participante.getCpf().equals(cpf)){
				return participante;
			}
		}
		ModuloDeValidacao.ParticipanteNaoEncontrado();
		return null;
	}
	
	public void editaParticipante(Pessoa participante, String cpf, String email, String nome) throws StringInvalidaException{
		ModuloDeValidacao.StringInvalida(nome);
		ModuloDeValidacao.StringInvalida(email);
		ModuloDeValidacao.StringInvalida(cpf);
		participante.setCpf(cpf);
		participante.setEmail(email);
		participante.setNome(nome);
	}
	
	public void apagaParticipante(Pessoa participante) throws ParticipanteNuloException{
		ModuloDeValidacao.ParticipanteNulo(participante);
		participantes.remove(participante);
	}
}
