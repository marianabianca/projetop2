package validacao;

import java.io.Serializable;

import exception.LogicaException;
import exception.ParametroInvalidoException;

public class ValidaParticipacao implements Serializable{
	private static final long serialVersionUID = 1L;
	private ValidaPessoa validaPessoa;
	private ValidaProjeto validaProjeto;
	
	/**
	 * contrutor
	 */
	public ValidaParticipacao() {
		this.validaPessoa = new ValidaPessoa();
		this.validaProjeto = new ValidaProjeto();
	}

	/**
	 * Metodo reponsavel por validar a associacao entre pessoa e projeto
	 * 
	 * @param cpfPessoa - cpf da pessoa a ser associada
	 * @param codProjeto - codigo do projeto a ser associado
	 * @throws ParametroInvalidoException - caso haja algum problema com a associacao
	 */
	public void validaAssociacao(String cpfPessoa, String codProjeto) throws ParametroInvalidoException{
		this.validaPessoa.validaCpf(cpfPessoa);
		this.validaProjeto.validaCodigo(codProjeto);
	}
	
	/**
	 * Metodo responsavel por validar a associacao entre professor e um projeto PED
	 * 
	 * @param temProfessorAssociado - se tem um professor associado
	 * @param temCoordenadorAssociado - se tem um coordenador associado
	 * @param ehCoordenador - se é coordenador
	 * @param valorPorHora - o valor por hora
	 * @throws LogicaException - caso haja alguma exceção na validacao
	 */
	public void validaAssociacaoProfessorPED(boolean temProfessorAssociado, boolean temCoordenadorAssociado, boolean ehCoordenador, double valorPorHora) throws LogicaException{
		if (!ehCoordenador) {
			this.validaProjeto.validaValorHora(valorPorHora);
			if (temProfessorAssociado) {
				throw new LogicaException("Projetos P&D nao podem ter mais de um professor");
			}
		} else {
			this.validaProjeto.validaValorHora(valorPorHora);
			if (temProfessorAssociado) {
				if (temCoordenadorAssociado) {
					throw new LogicaException("Projetos P&D nao podem ter mais de um coordenador");
				} else {
					throw new LogicaException("Projetos P&D nao podem ter mais de um professor associado");
				}
			}
		}
	}
	
	/**
	 * Metodo responsavel or validar a associacao entre professor e monitoria
	 * 
	 * @param temProfessorAssociado - se tem professor associado
	 * @param valorPorHora - valor por hora
	 * @throws LogicaException
	 */
	public void validaAssociacaoProfessorMonitoria(boolean temProfessorAssociado, double valorPorHora) throws LogicaException{
		this.validaProjeto.validaValorHoraMenorQueZero(valorPorHora);
		if (temProfessorAssociado) {
			this.validaProjeto.validaValorHoraDeMonitoria(valorPorHora);
			throw new LogicaException("Monitoria nao pode ter mais de um professor");
		}
	}
	
	/**
	 * Metodo responsavel por validar a associacao entre graduando e projeto PED
	 * 
	 * @param ehCooperativo - se é o projeto é do tipo Coop 
	 * @param jaTemAluno - se ja tem aluno
	 * @param alunoJaTaNoProjeto - se o aluno ja esta no projeto
	 * @throws LogicaException -caso haja algum problema na associacao
	 */
	public void validaAssociacaoGraduandoPED(boolean ehCooperativo, boolean jaTemAluno, boolean alunoJaTaNoProjeto) throws LogicaException{
		if (ehCooperativo && jaTemAluno){
			if (alunoJaTaNoProjeto){
				throw new LogicaException("Aluno ja esta cadastrado nesse projeto");
			}
		} else if (jaTemAluno){
			throw new LogicaException("Projetos P&D nao podem ter mais de um graduando");
		}
	}
	
	
	/**
	 * Metodo responsavel por validar a associacao de pos graduando
	 * 
	 * @param cpfPessoa - cpf da pessoa
	 * @param qntHoras - quantidade de horas
	 * @param valorHora - valor da hora
	 * @param isMonitoria - se é monitoria
	 * @throws LogicaException - caso haja algum problema na associacao
	 */
	public void validaAssociacaoPosGraduando(String cpfPessoa, int qntHoras, double valorHora, boolean isMonitoria) throws LogicaException{
		this.validaQtdHoras_CPF_ValorHora(cpfPessoa, valorHora, qntHoras, false);
		if (isMonitoria){
			throw new LogicaException("Tipo de projeto invalido para pos graduando");
		}
		
	}
	
	/**
	 * O metodo verifica se o cpf eh valido, se a quantidade de horas eh valida e se o valor por hora eh valido
	 * 
	 * @param cpf - cpf da pessoa
	 * @param valorHora - o valor da hora
	 * @param horas - a quantidade de horas
	 * @param isGraduando - se é graduando
	 * @throws LogicaException - se houver algum problema na validacao
	 */
	public void validaQtdHoras_CPF_ValorHora(String cpf, double valorHora, int horas, boolean isGraduando) throws LogicaException {
		this.validaCpfHoras(cpf, horas);
		if (!isGraduando){
			this.validaProjeto.validaValorHora(valorHora);
		} else {
			this.validaProjeto.validaValorHoraMenorQueZero(valorHora);
		}
	}
	
	/**
	 * Metodo reponsavel por validar o cpf e a quantidade de horas
	 * 
	 * @param cpf - cpf da pessoa
	 * @param horas - quantidade de horas
	 * @throws LogicaException - caso haja algum problema na verificacao
	 */
	public void validaCpfHoras(String cpf, int horas) throws LogicaException {
		this.validaPessoa.validaCpf(cpf);
		this.validaProjeto.validaQtdHoras(horas);
	}
	
}