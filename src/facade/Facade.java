package facade;

import pessoa.PessoaController;
import projeto.ProjetoController;
import participacao.ParticipacaoController;

import easyaccept.EasyAccept;
import exception.LogicaException;

public class Facade {

	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ParticipacaoController participacaoController;

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us1_test_exception.txt",
				"acceptance_test/us2_test.txt", "acceptance_test/us2_test_exception.txt",
				"acceptance_test/us3_test.txt", "acceptance_test/us3_test_exception.txt",
				"acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt" };
		EasyAccept.main(args);
	}

	// TODO tirar pirâmides do egito, LogicaException, tratamento de exception
	// na Facade

	public Facade() {
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
		participacaoController = new ParticipacaoController(pessoaController, projetoController);
	}

	public void iniciaSistema() {
		// TODO
	}

	public void fechaSistema() {
		// TODO
	}

	/**
	 * MÉTODO "cadastraPessoa" CHAMA O MÉTODO DE "pessoaController".
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param nome
	 *            RECEBE UMA STRING QUE CORRESPONDE AO NOME PARA A NOVA PESSOA.
	 * @param email
	 *            RECEBE UMA STRING QUE CORRESPONDE AO EMAIL PARA A NOVA PESSOA.
	 * @return CHAMA MÉTODO DE "pessoaController".
	 * @throws LogicaException
	 *             ENVIARÁ UMA MENSAGEM DE ERRO CASO NÃO OCORRA O QUE É
	 *             ESPERADO.
	 */
	public String cadastraPessoa(String cpf, String nome, String email) throws LogicaException {
		try {
			return pessoaController.cadastraPessoa(cpf, nome, email);
		} catch (LogicaException e) {
			throw new LogicaException("Erro no cadastro de pessoa: " + e.getMessage());
		}
	}

	/**
	 * MÉTODO "getInfoPessoa" CHAMA O MÉTODO DE "pessoaController".
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAÇÃO BUSCADA SOBRE O
	 *            USUÁRIO.
	 * @return CHAMA MÉTODO DE "pessoaController".
	 * @throws LogicaException
	 *             ENVIARÁ UMA MENSAGEM DE ERRO CASO NÃO OCORRA O QUE É
	 *             ESPERADO.
	 */
	public String getInfoPessoa(String cpf, String atributo) throws LogicaException {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}

	/**
	 * MÉTODO "editaPessoa" CHAMA O MÉTODO DE "pessoaController".
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAÇÃO QUE DESEJA SER
	 *            MODIFICADA EM PESSOA.
	 * @param valor
	 *            RECEBE UMA STRING QUE CORRESPONDE PARA O QUE O ATRIBUTO SERÁ
	 *            MUDADO PESSOA.
	 * @throws LogicaException
	 *             ENVIARÁ UMA MENSAGEM DE ERRO CASO NÃO OCORRA O QUE É
	 *             ESPERADO.
	 */
	public void editaPessoa(String cpf, String atributo, String valor) throws LogicaException {
		try {
			pessoaController.editaPessoa(cpf, atributo, valor);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na atualizacao de pessoa: " + e.getMessage());
		}
	}

	/**
	 * MÉTODO "editaPessoa" CHAMA O MÉTODO DE "pessoaController".
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA REMOVER A
	 *            PESSOA.
	 */
	public void removePessoa(String cpf) {
		pessoaController.removePessoa(cpf);
	}

	/**
	 * Método responsável por chamar "adicionaMonitoria" em "projetoController".
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
	 * @return - Chama método em "projetoController"
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,
				duracao);
	}

	/**
	 * Método responsável por chamar "adicionaPET" em "projetoController".
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
	 * @return - Chama método em "projetoController".
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao);
	}

	/**
	 * Método responsável por chamar "adicionaExtensao" em "projetoController".
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
	 * @return - Chama método em "projetoController".
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		return projetoController.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}

	/**
	 * Método responsável por chamar "adicionaPED" em "projetoController".
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
	 * @return - Chama método em "projetoController".
	 * @throws Exception
	 *             - Lançará uma Exception, caso os parâmetros não sejam os
	 *             esperados.
	 */
	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo,
				dataInicio, duracao);
	}

	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		return projetoController.getInfoProjeto(codigo, atributo);
	}

	public String getCodigoProjeto(String nome) throws Exception {
		return projetoController.getCodigoProjeto(nome);
	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		projetoController.editaProjeto(codigo, atributo, valor);
	}

	public void removeProjeto(String codigo) {
		projetoController.removeProjeto(codigo);
	}

	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora,
			int qntHoras) throws Exception {
		participacaoController.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);
	}

	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
			throws Exception {
		participacaoController.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);
	}

	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws Exception {
		participacaoController.associaProfissional(cpfPessoa, codigoProjeto, cargo, valorHora, qntHoras);
	}

	public void associaPosGraduando(String cpfPessoa, String codigoProjeto, String vinculo, double valorHora,
			int qntHoras) throws Exception {
		participacaoController.associaPosGraduando(cpfPessoa, codigoProjeto, vinculo, valorHora, qntHoras);
	}

	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		participacaoController.removeParticipacao(cpfPessoa, codigoProjeto);
	}

	/**
	 * MÉTODO "calculaPontuacaoPorParticipacao" CHAMA O MÉTODO DE
	 * "pessoaController".
	 * 
	 * @param cpfPessoa
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF DA PESSOA QUE DESEJA
	 *            CALCULAR.
	 * @return CHAMA MÉTODO DE "pessoaController".
	 * @throws LogicaException
	 *             ENVIARÁ UMA MENSAGEM DE ERRO CASO NÃO OCORRA O QUE É
	 *             ESPERADO.
	 */
	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws Exception {
		return pessoaController.calculaPontuacaoPorParticipacao(cpfPessoa);
	}

	/**
	 * Método responsável por chamar "getValorBolsa" em "pessoaController".
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa que deseja calcular a bolsa.
	 * @return - Chamará o método de "pessoaController".
	 * @throws Exception
	 *             - Lancará uma Exception caso os parâmetros não sejam os
	 *             esperados.
	 */
	public double getValorBolsa(String cpfPessoa) throws Exception {
		return pessoaController.getValorBolsa(cpfPessoa);
	}

}
