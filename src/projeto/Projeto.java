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

	public abstract String getInfoProjeto(String atributo) throws Exception;

	public abstract void editaProjeto(String atributo, String valor) throws Exception;

	public void adicionaParticipacao(Participacao participacao) {
		this.participacoes.add(participacao);
	}

	public boolean temProfessorAssociado() {
		for (Participacao participacao : participacoes) {
			if (participacao.isProfessor()) {
				return true;
			}
		}
		return false;
	}

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

	public boolean temGraduandoAssociado() {
		for (Participacao participacao : participacoes) {
			if (participacao.isAlunoGraduando()) {
				return true;
			}
		}
		return false;
	}

	public boolean temParticipacaoPorCPF(String cpfDaParticipacao) {
		for (Participacao participacao : participacoes) {
			if (participacao.getCpfDaPessoa().equals(cpfDaParticipacao)) {
				return true;
			}
		}
		return false;
	}

	public double calculaCustoTotal() {
		double despesaTotal = 0;
		for (Despesa despesa : custos) {
			despesaTotal = despesaTotal + despesa.getValor();
		}
		return despesaTotal;
	}

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

	public void removeParticipacao(String cpfPessoa) throws Exception {
		for (Participacao participacao : participacoes) {
			if (participacao.getCpfDaPessoa().equals(cpfPessoa)) {
				participacoes.remove(participacao);
				return;
			}
		}
		throw new Exception("Pessoa nao possui participacao no projeto indicado");
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

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
	
	public abstract boolean isMonitoria();

	public abstract boolean isPED();

}