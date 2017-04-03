package projeto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import exception.LogicaException;
import exception.ParametroInvalidoException;
import participacao.Participacao;
import participacao.Professor;

public abstract class Projeto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String LS = System.lineSeparator();
	private String nome, objetivo, dataInicio, codigo;
	private int duracao;
	private Map<String, Despesa> custos;
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public abstract String getInfoProjeto(String atributo) throws LogicaException;

	/**
	 * Metodo abstrato com funcao de repassar atividade para classes filhas.
	 * 
	 * @param atributo
	 *            - Atributo do projeto que deseja a editar.
	 * @param valor
	 *            - Valor a ser substituido no atributo.
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public abstract void editaProjeto(String atributo, String valor) throws LogicaException;

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
	 * Metodo responsavel por definir as participacoes em uma String.
	 * 
	 * @return - retornara a String de lista de participacoes.
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void removeParticipacao(String cpfPessoa) throws LogicaException {
		for (Participacao participacao : participacoes) {
			if (participacao.getCpfDaPessoa().equals(cpfPessoa)) {
				participacoes.remove(participacao);
				return;
			}
		}
		throw new LogicaException("Pessoa nao possui participacao no projeto indicado");
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
	 * o metodo tem como objetivo retornar o nome do projeto.
	 * 
	 * @return - retornara o nome do projeto.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * o metodo tem como objetivo mudar o nome do projeto.
	 * 
	 * @param nome
	 *            - novo nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * o metodo tem como objetivo retornar o objetivo do projeto.
	 * 
	 * @return - retornara o objetivo do projeto.
	 */
	public String getObjetivo() {
		return this.objetivo;
	}

	/**
	 * o metodo tem como objetivo mudar o objetivo do projeto.
	 * 
	 * @param objetivo
	 *            - novo objetivo.
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	/**
	 * o metodo tem como objetivo retornar a data de inicio do projeto.
	 * 
	 * @return - retornara a data de incio do projeto.
	 */
	public String getDataInicio() {
		return this.dataInicio;
	}

	/**
	 * o metodo tem como objetivo mudar a data de inicio do projeto.
	 * 
	 * @param dataInicio
	 *            - nova data de inicio.
	 */
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * o metodo tem como objetivo mudar a duracao do projeto.
	 * 
	 * @param duracao
	 *            - nova duracao.
	 */
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	/**
	 * o metodo tem como objetivo retornar a duracao do projeto.
	 * 
	 * @return - retornara a duracao do projeto.
	 */
	public int getDuracao() {
		return this.duracao;
	}

	/**
	 * o metodo tem como objetivo retornar o codigo do projeto.
	 * 
	 * @return - retornara o codigo do projeto.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Hashcode com codigo sendo referencia.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Equals com codigo sendo referencia.
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
	 * o metodo tem como objetivo ordenar as participacoes do projeto pelo nome.
	 */
	private void ordenaParticipacoesPeloNomeDasPessoas() {
		Collections.sort(participacoes);
	}

	/**
	 * o metodo tem como objetivo contar a quantidade de alunos que possuem nas
	 * particiapacões de determinado projeto.
	 * 
	 * @return - retornara o número de alunos no projeto.
	 */
	public double calculaPontuacao() {
		double acumulador = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.isAlunoGraduando() || participacao.isAlunoPosGraduando()) {
				acumulador += 1;
			}
		}
		return acumulador;
	}

	/**
	 * o metodo tem como objetivo saber o número de participantes do projeto.
	 * 
	 * @return - retornara o número de participantes.
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

	/**
	 * Metodo resonsavel por definir se o projeto eh Extensao, tendo como
	 * default falso e na filha Extensao um override afirmando verdadeiro.
	 * 
	 * @return - Por default, falso.
	 */
	public boolean isExtensao() {
		return false;
	}

	/**
	 * Metodo responsavel por atualizar o valor de bolsas em custos.
	 * 
	 * @param montanteBolsas
	 *            - Montante para qual vai ser mudado.
	 */
	public void atualizaBolsas(double montanteBolsas) {
		custos.get("bolsas").setValor(montanteBolsas);
	}

	/**
	 * Metodo responsavel por atualizar o valor de custeio em custos.
	 * 
	 * @param montanteCusteio
	 *            - Montante para qual vai ser mudado.
	 */
	public void atualizaCusteio(double montanteCusteio) {
		custos.get("custeio").setValor(montanteCusteio);
	}

	/**
	 * Metodo responsavel por atualizar o valor de capital em custos.
	 * 
	 * @param montanteCapital
	 *            - Montante para qual vai ser mudado.
	 */
	public void atualizaCapital(double montanteCapital) {
		custos.get("capital").setValor(montanteCapital);
	}

	/**
	 * Metodo responsavel por retornar o valor de capital.
	 * 
	 * @return - Retornara o valor desse custo.
	 */
	public double getCapital() {
		return custos.get("capital").getValor();
	}

	/**
	 * Metodo responsavel por retornar o valor de bolsas.
	 * 
	 * @return - Retornara o valor desse custo.
	 */
	public double getBolsas() {
		return custos.get("bolsas").getValor();
	}

	/**
	 * Metodo responsavel por retornar o valor de custeio.
	 * 
	 * @return - Retornara o valor desse custo.
	 */
	public double getCusteio() {
		return custos.get("custeio").getValor();
	}

	/**
	 * Metodo recursivo que tem como funcao calcular o valor da colaboracao UASC
	 * 
	 * @return - Valor do calculo.
	 */
	public double calculaColaboracaoUASC() {
		if (this.getCusteio() <= 10000 && this.getCapital() <= 10000) {
			return 0;
		}
		return 0.10 * this.calculaCustoTotal();
	}

	/**
	 * Metodo responsavel por gerar o mapa.
	 */
	private void geraMapa() {
		custos = new HashMap<String, Despesa>();
		custos.put("bolsas", new Despesa(0));
		custos.put("custeio", new Despesa(0));
		custos.put("capital", new Despesa(0));

	}

	/**
	 * 
	 * Metodo abstrato com funcao de repassar atividade para classes filhas.
	 * 
	 * @param montanteBolsas
	 *            - Montante de bolsas para qual vai ser atualizada.
	 * @param montanteCusteio
	 *            - Montante de custeio para qual vai ser atualizada.
	 * @param montanteCapital
	 *            - Montante de capital para qual vai ser atualizada.
	 * @throws ParametroInvalidoException
	 *             - Lancara uma Exception caso os parametros nao sejam os
	 *             esperados.
	 */
	public abstract void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ParametroInvalidoException;

	/**
	 * Metodo responsavel por transformar a data em um objeto do tipo Date e
	 * para analisar a exception.
	 * 
	 * @return - Data em date.
	 */
	private Date getDataInicioEmDate() {
		DateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicio = null;
		try {
			dataInicio = formata.parse(this.dataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataInicio;
	}

	/**
	 * Metodo responsavel por retornar o nome do coordenador, caso nao haja,
	 * retornara a mensagem: "Nao ha coordenador cadastrado neste projeto".
	 * 
	 * @return - caso haja coordenador, retornara seu nome, caso nao haja,
	 *         retornara a mensagem: "Nao ha coordenador cadastrado neste
	 *         projeto".
	 */
	private String getNomeDoCoordenador() {
		for (Participacao participacao : participacoes) {
			if (participacao.isCoordenador()) {
				return participacao.getNomeDaPessoa();
			}
		}
		return "Nao ha coordenador cadastrado neste projeto";
	}

	/**
	 * Metodo responsavel por retornar a data fim do projeto.
	 * 
	 * @return - retornara a data fim do projeto.
	 */
	private Date getDataFimDoProjeto() {
		Date dataFim = this.getDataInicioEmDate();
		Calendar c = Calendar.getInstance();
		c.setTime(dataFim);
		c.add(Calendar.MONTH, this.duracao);
		dataFim.setTime(c.getTime().getTime());
		return dataFim;
	}

	/**
	 * Metodo responsavel por analisar se o projeto esta finalizado ou nao.
	 * 
	 * @return - caso esteja finalizado, retornara a String "Finalizado", caso
	 *         nao esteja, retornara "Em andamento".
	 */
	public String isFinalizado() {
		Date dataFim = null;
		dataFim = this.getDataFimDoProjeto();
		Date hoje = Calendar.getInstance().getTime();
		if (dataFim.before(hoje)) {
			return "Finalizado";
		} else {
			return "Em andamento";
		}
	}

	/**
	 * Metodo responsavel por enviar as informacoes do projeto em String
	 */
	@Override
	public String toString() {
		return "Nome: " + this.nome + LS + "Data de inicio: " + this.getDataFormatada() + LS + "Coordenador: "
				+ this.getNomeDoCoordenador() + LS + "Situacao: " + this.isFinalizado();
	}

	/**
	 * Metodo responsavel por formatar a data para o forma yyyy-MM-dd
	 * 
	 * @return String - a data formatada
	 */
	private String getDataFormatada() {
		Date dataInicio = this.getDataInicioEmDate();
		DateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
		String dataInicioFormatada = formata.format(dataInicio);
		return dataInicioFormatada;
	}

	/**
	 * Metodo resposavel por retornar o numero de alunos graduandos nas
	 * participacoes de determinado projeto.
	 * 
	 * @return - Retornara o numero de alunos graduandos nas participacoes do
	 *         projeto.
	 */
	public int getNumeroDeGraduandos() {
		int numeroDeGraduandos = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.isAlunoGraduando()) {
				numeroDeGraduandos++;
			}
		}
		return numeroDeGraduandos;
	}

	/**
	 * Metodo resposavel por retornar o numero de alunos pos-graduandos nas
	 * participacoes de determinado projeto.
	 * 
	 * @return - Retornara o numero de alunos pos-graduandos nas participacoes
	 *         do projeto.
	 */
	public int getNumeroDePosGraduandos() {
		int numeroDePosGraduandos = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.isAlunoPosGraduando()) {
				numeroDePosGraduandos++;
			}
		}
		return numeroDePosGraduandos;
	}

	/**
	 * Metodo resposavel por retornar o numero de profissionais nas
	 * participacoes de determinado projeto.
	 * 
	 * @return - Retornara o numero de profissionais nas participacoes do
	 *         projeto.
	 */
	public int getNumeroDeProfissionais() {
		int numeroDeProfissionais = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.isProfissional()) {
				numeroDeProfissionais++;
			}
		}
		return numeroDeProfissionais;
	}

	/**
	 * Metodo responsavel por retornar um relatorio de colaboracoes em String.
	 * 
	 * @return - Retornara um relatorio de colaboracoes em String.
	 */
	public String getRelatorioDeColaboracoes() {
		return "Nome: " + this.nome + " Data de inicio: " + getDataFormatada() + " Valor colaborado: R$"
				+ calculaColaboracaoUASC();
	}

	/**
	 * Metodo responsavel por dizer se o projeto eh ou nao do tipo PET
	 * 
	 * @return boolean - se o projeto e ou nao do tipo PET
	 */
	public boolean isPET() {
		return false;
	}

}