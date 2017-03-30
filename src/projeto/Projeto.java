package projeto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import participacao.AlunoGraduando;
import participacao.Participacao;
import participacao.Professor;

public abstract class Projeto {

	private String nome, objetivo, dataInicio, codigo;
	private int duracao;
	private Map<String,Despesa> custos;
	private List<Participacao> participacoes;

	public Projeto(String nome, String objetivo, String dataInicio, int duracao, String codigo) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.codigo = codigo;
		this.geraMapa();
		this.participacoes = new ArrayList<>();
	}

	/**
	 * Metodo abstrato com funcao de repassar atividade para classes filhas.
	 * 
	 * @param atributo
	 *            - Atributo o qual deseja a informacao.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
	 *             esperados.
	 */
	public abstract String getInfoProjeto(String atributo) throws Exception;

	/**
	 * Metodo abstrato com funcao de repassar atividade para classes filhas.
	 * 
	 * @param atributo
	 *            - Atributo do projeto que deseja a editar.
	 * @param valor
	 *            - Valor a ser substituido no atributo.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
	 *             esperados.
	 */
	public abstract void editaProjeto(String atributo, String valor) throws Exception;

	/**
	 * Metodo responsavel por adicionar participacao.
	 * 
	 * @param participacao
	 *            - Participacao a ser adicionada
	 */
	public void adicionaParticipacao(Participacao participacao) {
		this.participacoes.add(participacao);
	}

	/**
	 * Metodo responsavel por verificar se ha algum professor associado.
	 * 
	 * @return - Retornara verdadeiro, caso haja, ou falso, caso nao.
	 */
	public boolean temProfessorAssociado() {
		for (Participacao participacao : participacoes) {
			if (participacao.isProfessor()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo responsavel por verificar se ha algum coordenador associado.
	 * 
	 * @return - Retornara verdadeiro, caso haja, ou falso, caso nao.
	 */
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

	/**
	 * Metodo responsavel por verificar se ha algum aluno graduando associado.
	 * 
	 * @return - Retornara verdadeiro, caso haja, ou falso, caso nao.
	 */
	public boolean temGraduandoAssociado() {
		for (Participacao participacao : participacoes) {
			if (participacao.isAlunoGraduando()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo responsavel por determinar se ha participacao de uma pessoa em
	 * determinado projeto.
	 * 
	 * @param cpfDaParticipacao
	 *            - CPF da pessoa que deseja determinar se ha participacao em
	 *            determinado projeto.
	 * @return - Retornara true, caso tenha, ou false, caso nao tenha.
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
	 * Metodo responsavel por calcular a despesa total.
	 * 
	 * @return - Retornara a despesa total.
	 */
	public double calculaCustoTotal() {
		double despesaTotal = 0;
		for (String despesa : custos.keySet()) {
			despesaTotal += custos.get(despesa).getValor();
		}
		return despesaTotal;
	}

	/**
	 * Metodo responsavel por definir as participacões em uma String.
	 * 
	 * @return -retornara a String de lista de participacões.
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
	 * Metodo responsavel por remover determinada participacao de determinada
	 * pessoa.
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa que deseja remover a paritcipacao
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 * Metodo responsavel por adicionar despesa.
	 * 
	 * @param despesa
	 *            - Despesa a ser adicionada.
	 */
	public void adicionaDespesa(String tipoDespesa, Despesa despesa) {
		this.custos.put(tipoDespesa, despesa);
	}

	/**
	 * O METODO TEM COMO OBJETIVO RETORNAR O NOME DO PROJETO.
	 * 
	 * @return RETORNARA O NOME DO PROJETO.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * O METODO TEM COMO OBJETIVO MUDAR O NOME DO PROJETO.
	 * 
	 * @param nome
	 *            NOVO NOME
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * O METODO TEM COMO OBJETIVO RETORNAR O OBJETIVO DO PROJETO.
	 * 
	 * @return RETORNARA O OBJETIVO DO PROJETO.
	 */
	public String getObjetivo() {
		return this.objetivo;
	}

	/**
	 * 
	 * O METODO TEM COMO OBJETIVO MUDAR O OBJETIVO DO PROJETO.
	 * 
	 * @param objetivo
	 *            NOVO OBJEITVO
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	/**
	 * O METODO TEM COMO OBJETIVO RETORNAR A DATA DE INÍCIO DO PROJETO.
	 * 
	 * @return RETORNARA A DATA DE INÍCIO DO PROJETO.
	 */
	public String getDataInicio() {
		return this.dataInicio;
	}

	/**
	 * O METODO TEM COMO OBJETIVO MUDAR A DATA DE INÍCIO DO PROJETO.
	 * 
	 * @param dataInicio
	 *            NOVA DATA DE INÍCIO.
	 */
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * O METODO TEM COMO OBJETIVO MUDAR A DURACaO DO PROJETO.
	 * 
	 * @param duracao
	 *            NOVA DURACAO.
	 */
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	/**
	 * O METODO TEM COMO OBJETIVO RETORNAR A DURACAO DO PROJETO.
	 * 
	 * @return RETORNARa A DURACAO DO PROJETO.
	 */
	public int getDuracao() {
		return this.duracao;
	}

	/**
	 * O METODO TEM COMO OBJETIVO RETORNAR A CÓDIGO DO PROJETO.
	 * 
	 * @return RETORNARA A DURAcaO DO CÓDIGO.
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
	 * O MeTODO TEM COMO OBJETIVO ORDENAR AS PARTICIPACOES DO PROJETO PELO NOME.
	 */
	private void ordenaParticipacoesPeloNomeDasPessoas() {
		Collections.sort(participacoes);
	}

	/**
	 * O MeTODO TEM COMO OBJETIVO CONTAR A QUANTIDADE DE ALUNOS QUE POSSUEM NAS
	 * PARTICIAPAcÕES DE DETERMINADO PROJETO.
	 * 
	 * @return RETORNARa O NÚMERO DE ALUNOS NO PROJETO.
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
	 * O MeTODO TEM COMO OBJETIVO SABER O NÚMERO DE PARTICIPANTES DO PROJETO.
	 * 
	 * @return RETORNARa O NÚMERO DE PARTICIPANTES.
	 */
	public int getNumeroDeParticipantes() {
		return this.participacoes.size();
	}

	/**
	 * Metodo resonsavel por definir se o projeto e monitoria, tendo como
	 * default falso e na filha monitoria um override afirmando verdadeiro.
	 * 
	 * @return - Por default, falso.
	 */
	public boolean isMonitoria() {
		return false;
	}

	/**
	 * Metodo resonsavel por definir se o projeto e PED, tendo como default
	 * falso e na filha PED um override afirmando verdadeiro.
	 * 
	 * @return - Por default, falso.
	 */
	public boolean isPED() {
		return false;
	}

	public void atualizaBolsas(double montanteBolsas) {
		custos.get("bolsas").setValor(montanteBolsas);
	}

	public void atualizaCusteio(double montanteCusteio) {
		custos.get("custeio").setValor(montanteCusteio);
	}

	public void atualizaCapital(double montanteCapital) {
		custos.get("capital").setValor(montanteCapital);
	}
	
	public double getCapital(){
		return custos.get("capital").getValor();
	}
	
	public double getBolsas(){
		return custos.get("bolsas").getValor();
	}
	
	public double getCusteio(){
		return custos.get("custeio").getValor();
	}

	public double calculaColaboracaoUASC() {
		if (this.getCusteio() == 0 && this.getCapital() == 0){
			return 0;
		} 
		if (this.getCusteio() <= 10000 && this.getCapital() == 0){
			return 0;
		}
		if (this.getCapital() <= 10000 && this.getCusteio() == 0){
			return 0;
		}
		if (this.getCusteio() <= 10000 && this.getCapital() <= 10000){
			return 0;
		}
		return 0.10 * this.calculaCustoTotal();
	}
	
	private void geraMapa(){
		custos = new HashMap<String, Despesa>();
		custos.put("bolsas", new Despesa(0));
		custos.put("custeio", new Despesa(0));
		custos.put("capital", new Despesa(0));

	}

}