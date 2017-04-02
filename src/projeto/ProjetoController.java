package projeto;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import exception.LogicaException;
import exception.ObjetoNuloException;
import participacao.Participacao;
import validacao.ModuloDeValidacao;
import validacao.ValidaProjeto;

public class ProjetoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String LS = System.lineSeparator();
	private List<Projeto> projetos;
	private int contadorCodigo;
	private ValidaProjeto validaProjeto;
	private ModuloDeValidacao moduloDeValidacao;
	private FactoryDePED factoryDePED;
	private double descontoReceita;

	public ProjetoController() {
		this.projetos = new ArrayList<Projeto>();
		this.contadorCodigo = 0;
		this.validaProjeto = new ValidaProjeto();
		this.moduloDeValidacao = new ModuloDeValidacao();
		this.factoryDePED = new FactoryDePED();
		this.descontoReceita = 0;
	}

	/**
	 * Metodo responsavel por criar e adicionar uma monitoria e verificar se
	 * seus parametros sao validos.
	 * 
	 * @param nome
	 *            - Nome do projeto a ser criado.
	 * @param disciplina
	 *            - Disciplina do projeto a ser criado.
	 * @param rendimento
	 *            - Rendimento do projeto a ser criado.
	 * @param objetivo
	 *            - Objetivo do projeto a ser criado.
	 * @param periodo
	 *            - Periodo do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Inicio do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Retornara o codigo do projeto.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		try {
			this.validaProjeto.validaNome(nome);
			this.validaProjeto.validaDisciplina(disciplina);
			this.validaProjeto.validaRendimento(rendimento);
			this.validaProjeto.validaObjetivo(objetivo);
			this.validaProjeto.validaPeriodo(periodo);
			this.moduloDeValidacao.dataInvalida(dataInicio);
			this.validaProjeto.validaDuracao(duracao);
			String codigo = this.geraCodigo();
			Projeto monitoria = new ProjetoMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,
					duracao, codigo);
			projetos.add(monitoria);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	/**
	 * Metodo responsavel por criar e adicionar um projeto PET e verificar se
	 * seus parametros sao validos.
	 * 
	 * @param nome
	 *            - Nome do projeto a ser criado.
	 * @param objetivo
	 *            - Objetivo do projeto a ser criado.
	 * @param impacto
	 *            - Impacto do projeto a ser criado.
	 * @param rendimento
	 *            - Rendimento do projeto a ser criado.
	 * @param prodTecnica
	 *            - Producao Tecnica do projeto a ser criado.
	 * @param prodAcademica
	 *            - Producao Academica do projeto a ser criado.
	 * @param patentes
	 *            - Patentes do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Inicio do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Retornara o codigo do projeto.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		try {
			this.validaProjeto.validaNome(nome);
			this.validaProjeto.validaObjetivo(objetivo);
			this.validaProjeto.validaImpacto(impacto);
			this.validaProjeto.validaRendimento(rendimento);
			this.validaProjeto.validaProdTecnica(prodTecnica);
			this.validaProjeto.validaProdTecnica(prodTecnica);
			this.validaProjeto.validaPatentes(patentes);
			this.moduloDeValidacao.dataInvalida(dataInicio);
			this.validaProjeto.validaDuracao(duracao);
			String codigo = this.geraCodigo();
			Projeto pet = new ProjetoPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
					dataInicio, duracao, codigo);
			projetos.add(pet);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por criar e adicionar um projeto extensao e verificar
	 * se seus parametros sao validos.
	 * 
	 * @param nome
	 *            - Nome do projeto a ser criado.
	 * @param objetivo
	 *            - Objetivo do projeto a ser criado.
	 * @param impacto
	 *            - Impacto do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Inicio do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Retornara o codigo do projeto.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		try {
			this.validaProjeto.validaNome(nome);
			this.validaProjeto.validaObjetivo(objetivo);
			this.validaProjeto.validaImpacto(impacto);
			this.moduloDeValidacao.dataInvalida(dataInicio);
			this.validaProjeto.validaDuracao(duracao);
			String codigo = this.geraCodigo();
			Projeto extensao = new ProjetoExtensao(nome, objetivo, impacto, dataInicio, duracao, codigo);
			projetos.add(extensao);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por criar e adicionar um projeto PED e verificar se
	 * seus parametros sao validos.
	 * 
	 * @param nome
	 *            - Nome do projeto a ser criado.
	 * @param categoria
	 *            - Categoria do projeto a ser criado.
	 * @param prodTecnica
	 *            - Producao Tecnica do projeto a ser criado.
	 * @param prodAcademica
	 *            - Producao Academica do projeto a ser criado.
	 * @param patentes
	 *            - Patentes do projeto a ser criado.
	 * @param objetivo
	 *            - Objetivo do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Inicio do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Retornara o codigo do projeto.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		try {
			this.validaProjeto.validaNome(nome);
			this.validaProjeto.validaCategoria(categoria);
			this.validaProjeto.validaProdTecnica(prodTecnica);
			this.validaProjeto.validaProdAcademica(prodAcademica);
			this.validaProjeto.validaPatentes(patentes);
			this.validaProjeto.validaObjetivo(objetivo);
			this.moduloDeValidacao.dataInvalida(dataInicio);
			this.validaProjeto.validaDuracao(duracao);
			String codigo = this.geraCodigo();
			Projeto ped = this.factoryDePED.criaProjetoPED(nome, categoria, prodTecnica, prodAcademica, patentes,
					objetivo, dataInicio, duracao, codigo);
			projetos.add(ped);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por chamar "getInfoProjeto" em "projeto", tratar as
	 * excecões e encontrar o projeto desejado.
	 * 
	 * @param codigo
	 *            - Codigo do projeto que deseja a informacao.
	 * @param atributo
	 *            - Atributo o qual deseja a informacao.
	 * @return - Chama metodo em "projeto".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		try {
			this.validaProjeto.validaAtributo(atributo);
			Projeto projeto = this.getProjeto(codigo);
			return projeto.getInfoProjeto(atributo);
		} catch (Exception e) {
			throw new Exception("Erro na consulta de projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por chamar "getCodigo" em "projeto" e tratar a
	 * excecao.
	 * 
	 * @param nome
	 *            - Nome do projeto que deseja o codigo.
	 * @return - Chama metodo em "projeto".
	 * @throws ObjetoNuloException
	 *             - Lancara uma ObjetoNuloException, caso os parametros nao
	 *             sejam os esperados.
	 */
	public String getCodigoProjeto(String nome) throws ObjetoNuloException {
		for (Projeto projeto : projetos) {
			if (projeto.getNome().equalsIgnoreCase(nome)) {
				return projeto.getCodigo();
			}
		}
		throw new ObjetoNuloException("Erro na obtencao de codigo de projeto: Projeto nao encontrado");
	}

	/**
	 * Metodo responsavel por chamar "editaProjeto" em "projeto" e tratar a
	 * excecao.
	 * 
	 * @param codigo
	 *            - Codigo do projeto que deseja a editar.
	 * @param atributo
	 *            - Atributo do projeto que deseja a editar.
	 * @param valor
	 *            - Valor a ser substituido no atributo.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		try {
			this.validaProjeto.validaAtributo(atributo);
			this.validaProjeto.validaValorAtributo(atributo, valor);
			Projeto projeto = this.getProjeto(codigo);
			projeto.editaProjeto(atributo, valor);
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por remover um projeto.
	 * 
	 * @param codigo
	 *            - Codigo do projeto que deseja remover.
	 * @throws Exception
	 *             - casoo o projeto nao exista
	 */
	public void removeProjeto(String codigo) throws Exception {
		Projeto projeto = this.getProjeto(codigo);
		this.projetos.remove(projeto);
	}

	/**
	 * Metodo responsavel por chamar "adicionaParticipacao" em "projeto" e
	 * tratar a excecao.
	 * 
	 * @param codigoProjeto
	 *            - Codigo do projeto a ter a paticipacao adicionada.
	 * @param participacao
	 *            - Paticipacao a ser adicionada.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void adicionaParticipacao(String codigoProjeto, Participacao participacao) throws Exception {
		Projeto projeto = getProjeto(codigoProjeto);
		projeto.adicionaParticipacao(participacao);
	}

	/**
	 * Metodo responsavel por chamar "adicionaParticipacao" em "projeto" e
	 * tratar a excecao.
	 * 
	 * @param codigoProjeto
	 *            - Codigo do projeto a ter a paticipacao removida.
	 * @param participacao
	 *            - Paticipacao a ser removida.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		try {
			Projeto projeto = this.getProjeto(codigoProjeto);
			projeto.removeParticipacao(cpfPessoa);
		} catch (Exception e) {
			throw new Exception("Erro na remocao de participacao: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por gerar codigo.
	 * 
	 * @return - Retornara o codigo.
	 */
	private String geraCodigo() {
		String codigo = Integer.toString(contadorCodigo++);
		return codigo;
	}

	/**
	 * Metodo resposavel por encontrar projeto em projetos e tratar a exececao,
	 * caso o parametro nao corresponda a um projeto.
	 * 
	 * @param codigo
	 *            - Codigo do projeto buscado.
	 * @return - Retornara o projeto buscado.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public Projeto getProjeto(String codigo) throws Exception {
		this.validaProjeto.validaCodigo(codigo);
		for (Projeto projeto : projetos) {
			if (projeto.getCodigo().equals(codigo)) {
				return projeto;
			}
		}
		throw new Exception("Projeto nao encontrado");
	}

	/**
	 * Metodo resposavel por definir se ja existe algum projeto com determinado
	 * codigo.
	 * 
	 * @param codigoProjeto
	 *            - Codigo do projeto buscado.
	 * @return - Retornara se ja existe(true) ou nao (false)
	 */
	public boolean existeProjeto(String codigoProjeto) {
		for (Projeto projeto : projetos) {
			if (projeto.getCodigo().equals(codigoProjeto)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo responsavel por chamar "atualizaDespesasProjeto" do projeto
	 * especifico e validar os parametros.
	 * 
	 * @param cod
	 *            - Código do projeto a ser atualizadas despesas.
	 * @param montanteBolsas
	 *            - Montante de bolsas para qual vai ser atualizada.
	 * @param montanteCusteio
	 *            - Montante de custeio para qual vai ser atualizada.
	 * @param montanteCapital
	 *            - Montante de capital para qual vai ser atualizada.
	 * @throws Exception
	 *             - Lancara uma Exception caso os parametros nao sejam os
	 *             esperados.
	 */
	public void atualizaDespesas(String cod, double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws Exception {
		this.validaProjeto.validaCodigo(cod);
		this.validaProjeto.validaValorAtributo(montanteBolsas);
		this.validaProjeto.validaValorAtributo(montanteCusteio);
		this.validaProjeto.validaValorAtributo(montanteCapital);
		Projeto aAtualizar = getProjeto(cod);
		aAtualizar.atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
	}

	/**
	 * Metodo responsavel por chamar "atualizaDespesasProjeto" do projeto
	 * especifico e validar os parametros.
	 * 
	 * @param codProjeto
	 *            - Código do projeto a ser calculado.
	 * @return - Valor do calculo.
	 * @throws Exception
	 *             - Lancara uma Exception caso os parametros nao sejam os
	 *             esperados.
	 */
	public double calculaColaboracaoUASC(String codProjeto) throws Exception {
		Projeto aColaborar = getProjeto(codProjeto);
		return aColaborar.calculaColaboracaoUASC();
	}

	/**
	 * Metodo responsavel por acumular todas as colaboracoes e retorna-la.
	 * 
	 * @return - Valor do calculo de todas as colaborações UASC dos projetos.
	 */
	public double calculaColaboracaoTotalUASC() {
		double colaboracao = 0;
		for (Projeto projeto : projetos) {
			colaboracao += projeto.calculaColaboracaoUASC();
		}
		return colaboracao;
	}

	/**
	 * Metodo responsavel por definir o desconto da receita e tratar excecoes
	 * 
	 * @param preco
	 *            - Preco canditado a descontoReceita
	 * @throws LogicaException
	 *             - Lancara uma Exception caso os parametros nao sejam os
	 *             esperados.
	 */
	public void diminuiReceita(double preco) throws LogicaException {
		this.moduloDeValidacao.numeroNegativo(preco);
		if (preco > this.calculaTotalEmCaixaUASC()) {
			throw new LogicaException("a unidade nao possui fundos suficientes");
		}
		this.descontoReceita = preco;
	}

	/**
	 * Metodo responsavel calcular o valor final arrecadado na caixa UASC com
	 * desconto da receita.
	 * 
	 * @return - Valor total em caixa UASC.
	 */
	public double calculaTotalEmCaixaUASC() {
		double total = this.calculaColaboracaoTotalUASC();
		total -= this.descontoReceita;
		return total;
	}

	// TODO Javadoc dos m�todos abaixo
	public String getRelatorioDeProjetosCadastrados() {
		String relatorio = "Cadastro de Projetos: " + this.projetos.size() + " projeto(s) registrado(s)" + LS;
		for (int i = 0; i < projetos.size(); i++) {
			Projeto projetoAtual = this.projetos.get(i);
			relatorio += "==> Projeto " + (i + 1) + LS + projetoAtual.toString() + LS + LS;
		}
		relatorio += "Total de projetos concluidos: " + this.projetos.size() + LS + "% Participacao da graduacao: "
				+ calculaPorcentagemGraduandos() + LS + "% Participacao da pos-graduacao: "
				+ calculaPorcentagemPosGraduandos() + LS + "% Participacao de profissionais: "
				+ calculaPorcentagemProfissionais();
		return relatorio;
	}

	public String getRelatorioDasColaboracoes() {
		String relatorio = "Historico das Colaboracoes:" + LS;
		for (Projeto projeto : projetos) {
			relatorio += "==> " + projeto.getRelatorioDeColaboracoes() + LS;
		}
		relatorio += "Total acumulado com colaboracoes: R$" + calculaColaboracaoTotalUASC() + LS + "Total gasto: R$"
				+ LS + "Total em caixa: R$" + calculaTotalEmCaixaUASC();
		return relatorio;
	}

	private int calculaNumeroDeParticipacoes() {
		int numeroDeParticipacoes = 0;
		for (Projeto projeto : projetos) {
			numeroDeParticipacoes += projeto.getNumeroDeParticipantes();
		}
		return numeroDeParticipacoes;
	}

	private String calculaPorcentagemGraduandos() {
		int numeroDeGraduandos = 0;
		for (Projeto projeto : projetos) {
			numeroDeGraduandos += projeto.getNumeroDeGraduandos();
		}
		return calculaPorcentagemDeParticipacoes(numeroDeGraduandos);
	}

	private String calculaPorcentagemPosGraduandos() {
		int numeroDePosGraduandos = 0;
		for (Projeto projeto : projetos) {
			numeroDePosGraduandos += projeto.getNumeroDePosGraduandos();
		}
		return calculaPorcentagemDeParticipacoes(numeroDePosGraduandos);
	}

	private String calculaPorcentagemProfissionais() {
		int numeroDeProfissionais = 0;
		for (Projeto projeto : projetos) {
			numeroDeProfissionais += projeto.getNumeroDePosGraduandos();
		}
		return calculaPorcentagemDeParticipacoes(numeroDeProfissionais);
	}

	private String calculaPorcentagemDeParticipacoes(int participacoesEspecificas) {
		double porcentagem = 100.0 * participacoesEspecificas / this.calculaNumeroDeParticipacoes();
		DecimalFormat formata = new DecimalFormat("#.##");
		return formata.format(porcentagem);
	}

}
