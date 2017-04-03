package projeto;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import exception.LogicaException;
import exception.ObjetoNuloException;
import participacao.Participacao;
import persistencia.ArquivosRelatorios;
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
	private ArquivosRelatorios arquivos;

	public ProjetoController() {
		this.projetos = new ArrayList<Projeto>();
		this.contadorCodigo = 0;
		this.validaProjeto = new ValidaProjeto();
		this.moduloDeValidacao = new ModuloDeValidacao();
		this.factoryDePED = new FactoryDePED();
		this.descontoReceita = 0;
		this.arquivos = new ArquivosRelatorios();
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws LogicaException {
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
			throw new LogicaException("Erro no cadastro de projeto: " + e.getMessage());
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws LogicaException {
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
			throw new LogicaException("Erro no cadastro de projeto: " + e.getMessage());
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws LogicaException {
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
			throw new LogicaException("Erro no cadastro de projeto: " + e.getMessage());
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws LogicaException {
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
			throw new LogicaException("Erro no cadastro de projeto: " + e.getMessage());
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String getInfoProjeto(String codigo, String atributo) throws LogicaException {
		try {
			this.validaProjeto.validaAtributo(atributo);
			Projeto projeto = this.getProjeto(codigo);
			return projeto.getInfoProjeto(atributo);
		} catch (Exception e) {
			throw new LogicaException("Erro na consulta de projeto: " + e.getMessage());
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void editaProjeto(String codigo, String atributo, String valor) throws LogicaException {
		try {
			this.validaProjeto.validaAtributo(atributo);
			this.validaProjeto.validaValorAtributo(atributo, valor);
			Projeto projeto = this.getProjeto(codigo);
			projeto.editaProjeto(atributo, valor);
		} catch (Exception e) {
			throw new LogicaException("Erro na atualizacao de projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por remover um projeto.
	 * 
	 * @param codigo
	 *            - Codigo do projeto que deseja remover.
	 * @throws LogicaException
	 *             - casoo o projeto nao exista
	 */
	public void removeProjeto(String codigo) throws LogicaException {
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void adicionaParticipacao(String codigoProjeto, Participacao participacao) throws LogicaException {
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws LogicaException {
		try {
			Projeto projeto = this.getProjeto(codigoProjeto);
			projeto.removeParticipacao(cpfPessoa);
		} catch (Exception e) {
			throw new LogicaException("Erro na remocao de participacao: " + e.getMessage());
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
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public Projeto getProjeto(String codigo) throws LogicaException {
		this.validaProjeto.validaCodigo(codigo);
		for (Projeto projeto : projetos) {
			if (projeto.getCodigo().equals(codigo)) {
				return projeto;
			}
		}
		throw new LogicaException("Projeto nao encontrado");
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
	 * @throws LogicaException
	 *             - Lancara uma Exception caso os parametros nao sejam os
	 *             esperados.
	 */
	public void atualizaDespesas(String cod, double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws LogicaException {
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
	 * @throws LogicaException
	 *             - Lancara uma Exception caso os parametros nao sejam os
	 *             esperados.
	 */
	public double calculaColaboracaoUASC(String codProjeto) throws LogicaException {
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

	/**
	 * Metodo que gera e salva o relatorio referente a parte dos projetos
	 * 
	 * @throws IOException - caso haja algum problema com o arquivo
	 */
	public void geraRelatorioProjetos() throws IOException {
		String texto = this.getRelatorioDeProjetosCadastrados();
		this.arquivos.salvaRelatorioProjetos(texto);
	}
	
	/**
	 * Metodo que gera e salva o relatorio referente a parte dos projetos
	 * 
	 * @throws IOException - caso haja algum problema com o arquivo
	 */
	public void geraRelatorioColaboracoes() throws IOException {
		String texto = this.getRelatorioDasColaboracoes();
		this.arquivos.salvaRelatorioColaboracoes(texto);
	}
	
	/**
	 * Metodo responsavel por retornar o relatorio de todos os projetos
	 * cadastrados em String.
	 * 
	 * @return String representante de todos os projetos cadastrados.
	 */
	private String getRelatorioDeProjetosCadastrados() {
		String relatorio = "Cadastro de Projetos: " + this.projetos.size() + " projeto(s) registrado(s)" + LS;
		for (int i = 0; i < projetos.size(); i++) {
			Projeto projetoAtual = this.projetos.get(i);
			relatorio += "==> Projeto " + (i + 1) + LS + projetoAtual.toString() + LS + LS;
		}
		relatorio += "Total de projetos concluidos: " + getNumeroDeProjetosConcluidos() + LS + "% Participacao da graduacao: "
				+ calculaPorcentagemGraduandos() + LS + "% Participacao da pos-graduacao: "
				+ calculaPorcentagemPosGraduandos() + LS + "% Participacao de profissionais: "
				+ calculaPorcentagemProfissionais();
		return relatorio;
	}

	/**
	 * Metodo responsavel por calcular o numero de projetos finalizados.
	 * @return - Numero de projetos finalizados.
	 */
	private int getNumeroDeProjetosConcluidos() {
		int projetosConcluidos = 0;
		for (Projeto projeto : projetos) {
			if (projeto.isFinalizado().equals(("Finalizado"))) {
				projetosConcluidos++;
			}
		}
		return projetosConcluidos;
	}

	/**
	 * Metodo responsavel por retornar o relatorio de colaboracoes de todos os
	 * projetos cadastrados em String.
	 * 
	 * @return - String representante de todas as colaboracoes dos projetos
	 *         cadastrados.
	 */
	private String getRelatorioDasColaboracoes() {
		String relatorio = "Historico das Colaboracoes:" + LS;
		for (Projeto projeto : projetos) {
			relatorio += "==> " + projeto.getRelatorioDeColaboracoes() + LS;
		}
		relatorio += "Total acumulado com colaboracoes: R$" + calculaColaboracaoTotalUASC() + LS + "Total gasto: R$"
				+ this.descontoReceita + LS + "Total em caixa: R$" + calculaTotalEmCaixaUASC();
		return relatorio;
	}

	/**
	 * Metodo responsavel por retornar o numero de participacoes em cada
	 * projeto.
	 * 
	 * @return - Retornara o numero de paticipacoes total de todos os projetos.
	 */
	private int calculaNumeroDeParticipacoes() {
		int numeroDeParticipacoes = 0;
		for (Projeto projeto : projetos) {
			numeroDeParticipacoes += projeto.getNumeroDeParticipantes();
		}
		return numeroDeParticipacoes;
	}

	/**
	 * Metodo responsavel por calcular a percentagem de algunos graduandos.
	 * 
	 * @return - Retornara a percentagem de alunos graduandos.
	 */
	private String calculaPorcentagemGraduandos() {
		int numeroDeGraduandos = 0;
		for (Projeto projeto : projetos) {
			numeroDeGraduandos += projeto.getNumeroDeGraduandos();
		}
		return calculaPorcentagemDeParticipacoes(numeroDeGraduandos);
	}

	/**
	 * Metodo responsavel por calcular a percentagem de algunos pos-graduandos.
	 * 
	 * @return - Retornara a percentagem de alunos pos-graduandos.
	 */
	private String calculaPorcentagemPosGraduandos() {
		int numeroDePosGraduandos = 0;
		for (Projeto projeto : projetos) {
			numeroDePosGraduandos += projeto.getNumeroDePosGraduandos();
		}
		return calculaPorcentagemDeParticipacoes(numeroDePosGraduandos);
	}

	/**
	 * Metodo responsavel por calcular a percentagem de profissionais.
	 * 
	 * @return - Retornara a percentagem de profissionais.
	 */
	private String calculaPorcentagemProfissionais() {
		int numeroDeProfissionais = 0;
		for (Projeto projeto : projetos) {
			numeroDeProfissionais += projeto.getNumeroDePosGraduandos();
		}
		return calculaPorcentagemDeParticipacoes(numeroDeProfissionais);
	}

	/**
	 * Metodo responsavel por calcular a percentagem de algum tipo de
	 * participacao, auxiliando os metodos: "calculaPorcentagemProfissionais",
	 * "calculaPorcentagemPosGraduandos" e "calculaPorcentagemGraduandos".
	 * 
	 * @return - Retornara a percentagem de de alguma paraticipacao especifica.
	 */
	private String calculaPorcentagemDeParticipacoes(int participacoesEspecificas) {
		double porcentagem = 100.0 * participacoesEspecificas / this.calculaNumeroDeParticipacoes();
		DecimalFormat formata = new DecimalFormat("#.##");
		return formata.format(porcentagem) + "%";
	}

}
