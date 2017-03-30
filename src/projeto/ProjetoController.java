package projeto;

import java.util.HashMap;
import java.util.Map;

import exception.ObjetoNuloException;
import participacao.Participacao;
import validacao.ModuloDeValidacao;
import validacao.ValidaProjeto;

public class ProjetoController {

	private Map<String, Projeto> projetos;
	private int contadorCodigo;
	private ValidaProjeto validaProjeto;
	private ModuloDeValidacao moduloDeValidacao;

	public ProjetoController() {
		this.projetos = new HashMap<>();
		this.contadorCodigo = 0;

		this.validaProjeto = new ValidaProjeto();
		this.moduloDeValidacao = new ModuloDeValidacao();
	}

	/**
	 * Metodo responsavel por criar e adicionar uma monitoria e verificar se
	 * seus parâmetros sao validos.
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
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Retornara o código do projeto.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
			projetos.put(codigo, monitoria);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

	}

	/**
	 * Metodo responsavel por criar e adicionar um projeto PET e verificar se
	 * seus parâmetros sao validos.
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
	 *            - Producao Acadêmica do projeto a ser criado.
	 * @param patentes
	 *            - Patentes do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Retornara o código do projeto.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
			projetos.put(codigo, pet);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por criar e adicionar um projeto extensao e verificar
	 * se seus parâmetros sao validos.
	 * 
	 * @param nome
	 *            - Nome do projeto a ser criado.
	 * @param objetivo
	 *            - Objetivo do projeto a ser criado.
	 * @param impacto
	 *            - Impacto do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Retornara o código do projeto.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
			projetos.put(codigo, extensao);
			return codigo;
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por criar e adicionar um projeto PED e verificar se
	 * seus parâmetros sao validos.
	 * 
	 * @param nome
	 *            - Nome do projeto a ser criado.
	 * @param categoria
	 *            - Categoria do projeto a ser criado.
	 * @param prodTecnica
	 *            - Producao Tecnica do projeto a ser criado.
	 * @param prodAcademica
	 *            - Producao Acadêmica do projeto a ser criado.
	 * @param patentes
	 *            - Patentes do projeto a ser criado.
	 * @param objetivo
	 *            - Objetivo do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Retornara o código do projeto.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
			Projeto ped = new ProjetoPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
					duracao, codigo);
			projetos.put(codigo, ped);
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
	 *            - Código do projeto que deseja a informacao.
	 * @param atributo
	 *            - Atributo o qual deseja a informacao.
	 * @return - Chama metodo em "projeto".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 *            - Nome do projeto que deseja o código.
	 * @return - Chama metodo em "projeto".
	 * @throws ObjetoNuloException
	 *             - Lancara uma ObjetoNuloException, caso os parâmetros nao
	 *             sejam os esperados.
	 */
	public String getCodigoProjeto(String nome) throws ObjetoNuloException {
		for (Projeto projeto : projetos.values()) {
			if (projeto.getNome().equals(nome)) {
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
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 */
	public void removeProjeto(String codigo) {
		this.projetos.remove(codigo);
	}

	/**
	 * Metodo responsavel por chamar "adicionaParticipacao" em "projeto" e
	 * tratar a excecao.
	 * 
	 * @param codigoProjeto
	 *            - Código do projeto a ter a paticipacao adicionada.
	 * @param participacao
	 *            - Paticipacao a ser adicionada.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 *            - Código do projeto a ter a paticipacao removida.
	 * @param participacao
	 *            - Paticipacao a ser removida.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 * Metodo responsavel por gerar código.
	 * 
	 * @return - Retornara o código.
	 */
	private String geraCodigo() {
		String codigo = Integer.toString(contadorCodigo++);
		return codigo;
	}

	/**
	 * Metodo resposavel por encontrar projeto em projetos e tratar a exececao,
	 * caso o parâmetro nao corresponda a um projeto.
	 * 
	 * @param codigo
	 *            - Código do projeto buscado.
	 * @return - Retornara o projeto buscado.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
	 *             esperados.
	 */
	public Projeto getProjeto(String codigo) throws Exception {
		if (!projetos.containsKey(codigo)) {
			throw new Exception("Projeto nao encontrado");
		}
		Projeto projeto = projetos.get(codigo);
		return projeto;
	}

	/**
	 * Metodo resposavel por definir se ja existe algum projeto com determinado
	 * código.
	 * 
	 * @param codigoProjeto
	 *            - Código do projeto buscado.
	 * @return - Retornara se ja existe(true) ou nao (false)
	 */
	public boolean existeProjeto(String codigoProjeto) {
		return projetos.containsKey(codigoProjeto);
	}

	public void atualizaDespesas(String cod, double montanteBolsas, double montanteCusteio, double montanteCapital) throws Exception {
		Projeto aAtualizar = getProjeto(cod);
		aAtualizar.atualizaBolsas(montanteBolsas);
		aAtualizar.atualizaCusteio(montanteCusteio);
		aAtualizar.atualizaCapital(montanteCapital);
	}

	public double calculaColaboracaoUASC(String codProjeto) throws Exception {
		Projeto aColaborar = getProjeto(codProjeto);
		return aColaborar.calculaColaboracaoUASC();
	}

	public double calculaColaboracaoTotalUASC() {
		double colaboracao = 0;
		for (Projeto proj : projetos.values()) {
			colaboracao += proj.calculaColaboracaoUASC();
		}
		return colaboracao;
	}

}
