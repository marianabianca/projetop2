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
	 * Método responsável por criar e adicionar uma monitoria e verificar se
	 * seus parâmetros são válidos.
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
	 *            - Duração do do projeto a ser criado.
	 * @return - Retornará o código do projeto.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
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
	 * Método responsável por criar e adicionar um projeto PET e verificar se
	 * seus parâmetros são válidos.
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
	 *            - Produção Técnica do projeto a ser criado.
	 * @param prodAcademica
	 *            - Produção Acadêmica do projeto a ser criado.
	 * @param patentes
	 *            - Patentes do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duração do do projeto a ser criado.
	 * @return - Retornará o código do projeto.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
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
	 * Método responsável por criar e adicionar um projeto extensão e verificar
	 * se seus parâmetros são válidos.
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
	 *            - Duração do do projeto a ser criado.
	 * @return - Retornará o código do projeto.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
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
	 * Método responsável por criar e adicionar um projeto PED e verificar se
	 * seus parâmetros são válidos.
	 * 
	 * @param nome
	 *            - Nome do projeto a ser criado.
	 * @param categoria
	 *            - Categoria do projeto a ser criado.
	 * @param prodTecnica
	 *            - Produção Técnica do projeto a ser criado.
	 * @param prodAcademica
	 *            - Produção Acadêmica do projeto a ser criado.
	 * @param patentes
	 *            - Patentes do projeto a ser criado.
	 * @param objetivo
	 *            - Objetivo do projeto a ser criado.
	 * @param dataInicio
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duração do do projeto a ser criado.
	 * @return - Retornará o código do projeto.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
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
	 * Método responsável por chamar "getInfoProjeto" em "projeto", tratar as
	 * exceções e encontrar o projeto desejado.
	 * 
	 * @param codigo
	 *            - Código do projeto que deseja a informação.
	 * @param atributo
	 *            - Atributo o qual deseja a informação.
	 * @return - Chama método em "projeto".
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
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
	 * Método responsável por chamar "getCodigo" em "projeto" e tratar a
	 * exceção.
	 * 
	 * @param nome
	 *            - Nome do projeto que deseja o código.
	 * @return - Chama método em "projeto".
	 * @throws ObjetoNuloException
	 *             - Lançará uma ObjetoNuloException, caso os parâmetros não
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
	 * Método responsável por chamar "editaProjeto" em "projeto" e tratar a
	 * exceção.
	 * 
	 * @param codigo
	 *            - Codigo do projeto que deseja a editar.
	 * @param atributo
	 *            - Atributo do projeto que deseja a editar.
	 * @param valor
	 *            - Valor a ser substituido no atributo.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
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
	 * Método responsável por remover um projeto.
	 * 
	 * @param codigo
	 *            - Codigo do projeto que deseja remover.
	 */
	public void removeProjeto(String codigo) {
		this.projetos.remove(codigo);
	}

	/**
	 * Método responsável por chamar "adicionaParticipacao" em "projeto" e
	 * tratar a exceção.
	 * 
	 * @param codigoProjeto
	 *            - Código do projeto a ter a paticipação adicionada.
	 * @param participacao
	 *            - Paticipação a ser adicionada.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public void adicionaParticipacao(String codigoProjeto, Participacao participacao) throws Exception {
		Projeto projeto = getProjeto(codigoProjeto);
		projeto.adicionaParticipacao(participacao);
	}

	/**
	 * Método responsável por chamar "adicionaParticipacao" em "projeto" e
	 * tratar a exceção.
	 * 
	 * @param codigoProjeto
	 *            - Código do projeto a ter a paticipação removida.
	 * @param participacao
	 *            - Paticipação a ser removida.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
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
	 * Método responsável por gerar código.
	 * 
	 * @return - Retornará o código.
	 */
	private String geraCodigo() {
		String codigo = Integer.toString(contadorCodigo++);
		return codigo;
	}

	/**
	 * Método resposável por encontrar projeto em projetos e tratar a execeção,
	 * caso o parâmetro não corresponda a um projeto.
	 * 
	 * @param codigo
	 *            - Código do projeto buscado.
	 * @return - Retornará o projeto buscado.
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
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
	 * Método resposável por definir se já existe algum projeto com determinado
	 * código.
	 * 
	 * @param codigoProjeto
	 *            - Código do projeto buscado.
	 * @return - Retornará se já existe(true) ou não (false)
	 */
	public boolean existeProjeto(String codigoProjeto) {
		return projetos.containsKey(codigoProjeto);
	}

}
