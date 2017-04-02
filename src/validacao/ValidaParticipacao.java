package validacao;

import java.io.Serializable;

import exception.ParametroInvalidoException;

public class ValidaParticipacao implements Serializable{
	private static final long serialVersionUID = 1L;
	private ValidaPessoa validaPessoa;
	private ValidaProjeto validaProjeto;
	
	// TODO
	public ValidaParticipacao() {
		this.validaPessoa = new ValidaPessoa();
		this.validaProjeto = new ValidaProjeto();
	}

	// TODO
	public void validaAssociacao(String cpfPessoa, String codProjeto) throws ParametroInvalidoException{
		this.validaPessoa.validaCpf(cpfPessoa);
		this.validaProjeto.validaCodigo(codProjeto);
	}
	
	// TODO
	public void validaAssociacaoProfessorPED(boolean temProfessorAssociado, boolean temCoordenadorAssociado, boolean ehCoordenador, double valorPorHora) throws Exception{
		if (!ehCoordenador) {
			this.validaProjeto.validaValorHora(valorPorHora);
			if (temProfessorAssociado) {
				throw new Exception("Projetos P&D nao podem ter mais de um professor");
			}
		} else {
			this.validaProjeto.validaValorHora(valorPorHora);
			if (temProfessorAssociado) {
				if (temCoordenadorAssociado) {
					throw new Exception("Projetos P&D nao podem ter mais de um coordenador");
				} else {
					throw new Exception("Projetos P&D nao podem ter mais de um professor associado");
				}
			}
		}
	}
	
	// TODO
	public void validaAssociacaoProfessorMonitoria(boolean temProfessorAssociado, double valorPorHora) throws Exception{
		this.validaProjeto.validaValorHoraMenorQueZero(valorPorHora);
		if (temProfessorAssociado) {
			this.validaProjeto.validaValorHoraDeMonitoria(valorPorHora);
			throw new Exception("Monitoria nao pode ter mais de um professor");
		}
	}
	
	// TODO
	public void validaAssociacaoGraduandoPED(boolean ehCooperativo, boolean jaTemAluno, boolean alunoJaTaNoProjeto) throws Exception{
		if (ehCooperativo && jaTemAluno){
			if (alunoJaTaNoProjeto){
				throw new Exception("Aluno ja esta cadastrado nesse projeto");
			}
		} else if (jaTemAluno){
			throw new Exception("Projetos P&D nao podem ter mais de um graduando");
		}
	}
	
	
	// TODO
	public void validaAssociacaoPosGraduando(String cpfPessoa, int qntHoras, double valorHora, boolean isMonitoria) throws Exception{
		this.validaQtdHoras_CPF_ValorHora(cpfPessoa, valorHora, qntHoras, false);
		if (isMonitoria){
			throw new Exception("Tipo de projeto invalido para pos graduando");
		}
		
	}
	
	// TODO
	public void validaQtdHoras_CPF_ValorHora(String cpf, double valorHora, int horas, boolean isGraduando) throws Exception{
		this.validaCpfHoras(cpf, horas);
		if (!isGraduando){
			this.validaProjeto.validaValorHora(valorHora);
		} else {
			this.validaProjeto.validaValorHoraMenorQueZero(valorHora);
		}
	}
	
	// TODO
	public void validaCpfHoras(String cpf, int horas) throws Exception{
		this.validaPessoa.validaCpf(cpf);
		this.validaProjeto.validaQtdHoras(horas);
	}
	
}