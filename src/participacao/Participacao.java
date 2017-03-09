package participacao;

import java.util.HashSet;

import java.util.Set;

import exceptions.ParticipanteNaoEncontradoException;
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
	
	// colocar teste de unidade ou não?
	public void adicionarParticipante(Pessoa participanteAAdicionar){
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
		ModuloDeValidacao.stringInvalida(nome);
		ModuloDeValidacao.stringInvalida(email);
		ModuloDeValidacao.stringInvalida(cpf);
		participante.setCpf(cpf);
		participante.setEmail(email);
		participante.setNome(nome);
	}
	
	public void apagaParticipante(Pessoa participante){
		participantes.remove(participante);
	}
}
