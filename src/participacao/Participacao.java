package participacao;

import java.util.HashSet;
import java.util.Set;

import exceptions.ParticipanteNaoEncontrado;
import exceptions.ParticipanteNulo;
import exceptions.StringInvalida;
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
	
	public void adicionarParticipante(Pessoa participanteAAdicionar) throws ParticipanteNulo{
		ModuloDeValidacao.ParticipanteNulo(participanteAAdicionar);
		participantes.add(participanteAAdicionar);	
	}
	
	public Pessoa recuperarParticipante(String cpf) throws ParticipanteNaoEncontrado{
		for (Pessoa participante : participantes){
			if (participante.getCpf().equals(cpf)){
				return participante;
			}
		}
		ModuloDeValidacao.ParticipanteNaoEncontrado();
		return null;
	}
	
	public void editaParticipante(Pessoa participante, String cpf, String email, String nome) throws StringInvalida{
		ModuloDeValidacao.StringInvalida(nome);
		ModuloDeValidacao.StringInvalida(email);
		ModuloDeValidacao.StringInvalida(cpf);
		participante.setCpf(cpf);
		participante.setEmail(email);
		participante.setNome(nome);
	}
	
	public void apagaParticipante(Pessoa participante) throws ParticipanteNulo{
		ModuloDeValidacao.ParticipanteNulo(participante);
		participantes.remove(participante);
	}
}
