package pessoa;

import java.util.ArrayList;

import java.util.List;

import participacao.AlunoGraduando;
import participacao.Participacao;

public class Pessoa {

	private String nome, email, cpf;
	private List<Participacao> participacoes;

	public Pessoa(String nome, String email, String cpf) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.participacoes = new ArrayList<>();
	}

	public void adicionaParticipacao(Participacao participacao) {
		this.participacoes.add(participacao);
	}

	public void removeParticipacao(String codigoProjeto) throws Exception {
		if (!this.temParticipacaoEmProjeto(codigoProjeto)) {
			throw new Exception("Pessoa nao possui participacao no projeto indicado");
		}
		for (Participacao participacao : participacoes) {
			if (participacao.getCodigoDoProjeto().equals(codigoProjeto)) {
				participacoes.remove(participacao);
				return;
			}
		}
	}

	public boolean temParticipacaoEmProjeto(String codigoProjeto) {
		for (Participacao participacao : participacoes) {
			if (participacao.getCodigoDoProjeto().equals(codigoProjeto)) {
				return true;
			}
		}
		return false;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getParticipacoes() {
		String listaParticipacoes = "";
		for (Participacao participacao : participacoes) {
			listaParticipacoes += participacao.getNomeDoProjeto() + ", ";
		}
		if (listaParticipacoes.endsWith(", ")) {
			return listaParticipacoes.substring(0, listaParticipacoes.length() - 2);
		}
		return listaParticipacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ("Nome: " + this.nome + ", e-mail: " + this.email + ", CPF: " + this.cpf);
	}

	public double calculaPontuacaoPorParticipacao() {
		int acumulador = 0;
		int acumuladorGraduandoPED = 0;
		int acumuladorGraduandoPET = 0;
		int acumuladorGraduandoMonitoria = 0;
		int acumuladorGraduandoExtensao = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.getClass() == AlunoGraduando.class) {
				if (acumuladorGraduandoPED != 8 && participacao.getTipoDeProjeto().equals("PED")) {
					acumuladorGraduandoPED += participacao.calculaPontuacao();
					if (acumuladorGraduandoPED > 8) {
						acumuladorGraduandoPED = 8;
					}
				} else if (acumuladorGraduandoPET != 8 && participacao.getTipoDeProjeto().equals("PET")) {
					acumuladorGraduandoPED += participacao.calculaPontuacao();
					if (acumuladorGraduandoPET > 8) {
						acumuladorGraduandoPET = 8;
					}
				} else if (acumuladorGraduandoExtensao != 8 && participacao.getTipoDeProjeto().equals("EXTENSAO")) {
					acumuladorGraduandoExtensao += participacao.calculaPontuacao();
					if (acumuladorGraduandoExtensao > 8) {
						acumuladorGraduandoExtensao = 8;
					}
				} else if (acumuladorGraduandoMonitoria != 6 && participacao.getTipoDeProjeto().equals("MONITORIA")) {
					acumuladorGraduandoMonitoria += participacao.calculaPontuacao();
					if (acumuladorGraduandoMonitoria > 6) {
						acumuladorGraduandoMonitoria = 6;
					}
				}
			} else {
				acumulador += participacao.calculaPontuacao();
			}
		}
		acumulador += acumuladorGraduandoExtensao + acumuladorGraduandoMonitoria + acumuladorGraduandoPED
				+ acumuladorGraduandoPET;
		return acumulador;
	}

}