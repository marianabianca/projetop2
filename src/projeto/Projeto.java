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
			if (participacao.getClass().equals(Professor.class)) {
				return true;
			}
		}
		return false;
	}

	public boolean temCoordenadorAssociado() {
		for (Participacao participacao : participacoes) {
			if (participacao.getClass().equals(Professor.class)) {
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
			if (participacao.getClass() == AlunoGraduando.class) {
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	/**
	 * O MÉTODO TEM COMO OBJETIVO RETORNAR A DURAÇÃO DO PROJETO.
	 * @return RETORNARÁ A DURAÇÃO DO PROJETO.
	 */
	public int getDuracao() {
		return this.duracao;
	}

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

	private void ordenaParticipacoesPeloNomeDasPessoas() {
		Collections.sort(participacoes);
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO CONTAR A QUANTIDADE DE ALUNOS QUE POSSUEM NAS 
	 * PARTICIAPAÇÕES DE DETERMINADO PROJETO.
	 * @return RETORNARÁ O NÚMERO DE ALUNOS NO PROJETO.
	 */
	public double calculaPontuacao() {
		double acumulador = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.getClass() == AlunoGraduando.class) {
				acumulador += 1;
			}
		}
		return acumulador;
	}

	public int getNumeroDeParticipantes() {
		return this.participacoes.size();
	}

}