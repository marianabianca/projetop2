package projeto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import participacao.AlunoGraduando;
import participacao.Participacao;
import participacao.Professor;

public abstract class Projeto {

	private String nome, objetivo, dataInicio, codigo;
	private int duracao;
	private List<Despesa> custos;
	private List<Participacao> participacoes;

	public Projeto(String nome, String objetivo, String dataInicio, int duracao, String codigo) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.codigo = codigo;
		this.custos = new ArrayList<>();
		this.participacoes = new ArrayList<>();
	}

	/**
	 * Método abstrato com função de repassar atividade para classes filhas.
	 * 
	 * @param atributo
	 *            - Atributo o qual deseja a informação.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public abstract String getInfoProjeto(String atributo) throws Exception;

	/**
	 * Método abstrato com função de repassar atividade para classes filhas.
	 * 
	 * @param atributo
	 *            - Atributo do projeto que deseja a editar.
	 * @param valor
	 *            - Valor a ser substituido no atributo.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public abstract void editaProjeto(String atributo, String valor) throws Exception;

	/**
	 * Método responsável por adicionar participação.
	 * 
	 * @param participacao
	 *            - Participação a ser adicionada
	 */
	public void adicionaParticipacao(Participacao participacao) {
		this.participacoes.add(participacao);
	}

	// TODO
	public boolean temProfessorAssociado() {
		for (Participacao participacao : participacoes) {
			if (participacao.isProfessor()) {
				return true;
			}
		}
		return false;
	}

	// TODO
	public boolean temCoordenadorAssociado() {
		for (Participacao participacao : participacoes) {
			if (participacao.isProfessor()) {
				Professor prof = (Professor) participacao;
				if (prof.isCoordenador()) {
					return true;
				}
			}
		}
		return false;
	}

	// TODO
	public boolean temGraduandoAssociado() {
		for (Participacao participacao : participacoes) {
			if (participacao.isAlunoGraduando()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método responsável por determinar se há participação de uma pessoa em
	 * determinado projeto.
	 * 
	 * @param cpfDaParticipacao
	 *            - CPF da pessoa que deseja determinar se há participação em
	 *            determinado projeto.
	 * @return - Retornará true, caso tenha, ou false, caso não tenha.
	 */
	public boolean temParticipacaoPorCPF(String cpfDaParticipacao) {
		for (Participacao participacao : participacoes) {
			if (participacao.getCpfDaPessoa().equals(cpfDaParticipacao)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método responsável por calcular a despesa total.
	 * 
	 * @return - Retornará a despesa total.
	 */
	public double calculaCustoTotal() {
		double despesaTotal = 0;
		for (Despesa despesa : custos) {
			despesaTotal = despesaTotal + despesa.getValor();
		}
		return despesaTotal;
	}

	/**
	 * Método responsável por definir as participações em uma String.
	 * 
	 * @return -retornará a String de lista de participações.
	 */
	public String getParticipacoes() {
		String listaParticipacoes = "";
		ordenaParticipacoesPeloNomeDasPessoas();
		for (Participacao participacao : participacoes) {
			listaParticipacoes += participacao.getNomeDaPessoa() + ", ";
		}
		if (listaParticipacoes.endsWith(", ")) {
			return listaParticipacoes.substring(0, listaParticipacoes.length() - 2);
		}
		return listaParticipacoes;
	}

	/**
	 * Método responsável por remover determinada participação de determinada
	 * pessoa.
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa que deseja remover a paritcipação
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public void removeParticipacao(String cpfPessoa) throws Exception {
		for (Participacao participacao : participacoes) {
			if (participacao.getCpfDaPessoa().equals(cpfPessoa)) {
				participacoes.remove(participacao);
				return;
			}
		}
		throw new Exception("Pessoa nao possui participacao no projeto indicado");
	}

	/**
	 * Método responsável por adicionar despesa.
	 * 
	 * @param despesa
	 *            - Despesa a ser adicionada.
	 */
	public void adicionaDespesa(Despesa despesa) {
		this.custos.add(despesa);
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O NOME DO PROJETO.
	 * 
	 * @return RETORNARÁ O NOME DO PROJETO.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO MUDAR O NOME DO PROJETO.
	 * 
	 * @param nome
	 *            NOVO NOME
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR O OBJETIVO DO PROJETO.
	 * 
	 * @return RETORNARÁ O OBJETIVO DO PROJETO.
	 */
	public String getObjetivo() {
		return this.objetivo;
	}

	/**
	 * 
	 * O MÉTODO TEM COMO OBJETIVO MUDAR O OBJETIVO DO PROJETO.
	 * 
	 * @param objetivo
	 *            NOVO OBJEITVO
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR A DATA DE INÍCIO DO PROJETO.
	 * 
	 * @return RETORNARÁ A DATA DE INÍCIO DO PROJETO.
	 */
	public String getDataInicio() {
		return this.dataInicio;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO MUDAR A DATA DE INÍCIO DO PROJETO.
	 * 
	 * @param dataInicio
	 *            NOVA DATA DE INÍCIO.
	 */
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO MUDAR A DURACÃO DO PROJETO.
	 * 
	 * @param duracao
	 *            NOVA DURACÃO.
	 */
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR A DURAÇÃO DO PROJETO.
	 * 
	 * @return RETORNARÁ A DURAÇÃO DO PROJETO.
	 */
	public int getDuracao() {
		return this.duracao;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR A CÓDIGO DO PROJETO.
	 * 
	 * @return RETORNARÁ A DURAÇÃO DO CÓDIGO.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Hashcode com código sendo referência.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Equals com código sendo referência.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO ORDENAR AS PARTICIPACOES DO PROJETO PELO NOME.
	 */
	private void ordenaParticipacoesPeloNomeDasPessoas() {
		Collections.sort(participacoes);
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CONTAR A QUANTIDADE DE ALUNOS QUE POSSUEM NAS
	 * PARTICIAPAÇÕES DE DETERMINADO PROJETO.
	 * 
	 * @return RETORNARÁ O NÚMERO DE ALUNOS NO PROJETO.
	 */
	public double calculaPontuacao() {
		double acumulador = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.isAlunoGraduando()) {
				acumulador += 1;
			}
		}
		return acumulador;
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO SABER O NÚMERO DE PARTICIPANTES DO PROJETO.
	 * 
	 * @return RETORNARÁ O NÚMERO DE PARTICIPANTES.
	 */
	public int getNumeroDeParticipantes() {
		return this.participacoes.size();
	}

	/**
	 * Método resonsável por definir se o projeto é monitoria tendo o default de
	 * ser falso e na filha monitoria há um override afirmando verdadeiro.
	 * 
	 * @return - Por default, falso.
	 */
	public boolean isMonitoria() {
		return false;
	}

	/**
	 * Método resonsável por definir se o projeto é PED tendo o default de ser
	 * falso e na filha PED há um override afirmando verdadeiro.
	 * 
	 * @return - Por default, falso.
	 */
	public boolean isPED() {
		return false;
	}

}