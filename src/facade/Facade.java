package facade;

import pessoa.PessoaController;
import projeto.ProjetoController;
import participacao.ParticipacaoController;
import persistencia.Persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import easyaccept.EasyAccept;
import exception.LogicaException;
import exception.ParametroInvalidoException;

public class Facade {

	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ParticipacaoController participacaoController;
	private Persistencia persistencia;

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us1_test_exception.txt",
				"acceptance_test/us2_test.txt", "acceptance_test/us2_test_exception.txt",
				"acceptance_test/us3_test.txt", "acceptance_test/us3_test_exception.txt",
				"acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt", "acceptance_test/us6_test.txt",
				"acceptance_test/us6_test_exception.txt" };
		EasyAccept.main(args);
	}

	// TODO tirar piramides do egito, LogicaException, tratamento de exception
	// na Facade

	public Facade() {
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
		participacaoController = new ParticipacaoController(pessoaController, projetoController);
		persistencia = new Persistencia();
	}

	/**
	 * Metodo para carregar o sistema salvo. Carrega os objetos pessoaController
	 * e ProjetoController
	 * 
	 * @throws IOException
	 *             - caso haja algum problema com o arquivo
	 * @throws ClassNotFoundException
	 *             - caso o downcast nao funcione, pois a classe nao existe
	 */
	public void iniciaSistema() throws IOException, ClassNotFoundException {
		try {
			this.persistencia.iniciaSistema();
			this.pessoaController = persistencia.iniciaPessoa();
			this.projetoController = persistencia.iniciaProjeto();
			this.participacaoController = persistencia.iniciaParticipacao();
		} catch (FileNotFoundException e) {
		}
	}

	/**
	 * Metodo para a escrita dos objetos pessoaController e projetoController em um
	 * arquivo a fim de salva-los
	 * 
	 * @throws IOException
	 *             - caso haja algum problema com o arquivo
	 */
	public void fechaSistema() throws IOException {
		this.persistencia.fechaSistma(projetoController, pessoaController, participacaoController);
	}

	/**
	 * Metodo "cadastrapessoa" chama o metodo de "pessoacontroller".
	 * 
	 * @param cpf
	 *            - Recebe uma string que corresponde ao cpf para a nova pessoa.
	 * @param nome
	 *            - Recebe uma string que corresponde ao nome para a nova
	 *            pessoa.
	 * @param email
	 *            - Recebe uma string que corresponde ao email para a nova
	 *            pessoa.
	 * @return - Chama metodo de "pessoacontroller".
	 * @throws logicaexception
	 *             - Enviara uma mensagem de erro caso nao ocorra o que e
	 *             esperado.
	 */
	public String cadastraPessoa(String cpf, String nome, String email) throws LogicaException {
		try {
			return pessoaController.cadastraPessoa(cpf, nome, email);
		} catch (LogicaException e) {
			throw new LogicaException("Erro no cadastro de pessoa: " + e.getMessage());
		}
	}

	/**
	 * Metodo "getInfoPessoa" chama o metodo de "pessoaController".
	 * 
	 * @param cpf
	 *            - recebe uma string que corresponde ao cpf para a nova pessoa.
	 * @param atributo
	 *            - recebe uma string que corresponde a informacao buscada sobre
	 *            o usuario.
	 * @return chama metodo de "pessoacontroller".
	 * @throws logicaexception
	 *             - enviara uma mensagem de erro caso nao ocorra o que e
	 *             esperado.
	 */
	public String getInfoPessoa(String cpf, String atributo) throws LogicaException {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}

	/**
	 * Metodo "editaPessoa" chama metodo de "pessoaController".
	 * 
	 * @param cpf
	 *            - recebe uma string que corresponde ao cpf para a nova pessoa.
	 * @param atributo
	 *            - recebe uma string que corresponde a informacao que deseja
	 *            ser - modificada em pessoa.
	 * @param valor
	 *            - recebe uma string que corresponde para o que o atributo sera
	 *            mudado pessoa.
	 * @throws logicaexception
	 *             - enviara uma mensagem de erro caso nao ocorra o que e
	 *             esperado.
	 */
	public void editaPessoa(String cpf, String atributo, String valor) throws LogicaException {
		try {
			pessoaController.editaPessoa(cpf, atributo, valor);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na atualizacao de pessoa: " + e.getMessage());
		}
	}

	/**
	 * metodo "editapessoa" chama o metodo de "pessoacontroller".
	 * 
	 * @param cpf
	 *            - recebe uma string que corresponde ao cpf para remover a
	 *            pessoa.
	 */
	public void removePessoa(String cpf) {
		pessoaController.removePessoa(cpf);
	}

	/**
	 * Metodo responsavel por chamar "adicionaMonitoria" em "projetoController".
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
	 * @return - Chama metodo em "projetoController"
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,
				duracao);
	}

	/**
	 * Metodo responsavel por chamar "adicionaPET" em "projetoController".
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
	 *            - Data de Inicio do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Chama metodo em "projetoController".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao);
	}

	/**
	 * Metodo responsavel por chamar "adicionaExtensao" em "projetoController".
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
	 * @return - Chama metodo em "projetoController".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		return projetoController.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}

	/**
	 * Metodo responsavel por chamar "adicionaPED" em "projetoController".
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
	 *            - Data de Inicio do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Chama metodo em "projetoController".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		return projetoController.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo,
				dataInicio, duracao);
	}

	/**
	 * Metodo responsavel por chamar "getInfoProjeto" em "projetoController".
	 * 
	 * @param codigo
	 *            - Código do projeto que deseja a informacao.
	 * @param atributo
	 *            - Atributo o qual deseja a informacao.
	 * @return - Chama metodo em "projetoController".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		return projetoController.getInfoProjeto(codigo, atributo);
	}

	/**
	 * Metodo responsavel por chamar "getCodigoProjeto" em "projetoController".
	 * 
	 * @param nome
	 *            - Nome do projeto que deseja o código.
	 * @return - Chama metodo em "projetoController".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public String getCodigoProjeto(String nome) throws Exception {
		return projetoController.getCodigoProjeto(nome);
	}

	/**
	 * Metodo responsavel por chamar "editaProjeto" em "projetoController".
	 * 
	 * @param codigo
	 *            - Codigo do projeto que deseja editar.
	 * @param atributo
	 *            - Atributo do projeto que deseja editar.
	 * @param valor
	 *            - Valor a ser substituido no atributo.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		projetoController.editaProjeto(codigo, atributo, valor);
	}

	/**
	 * Metodo responsavel por chamar "removeProjeto" em "projetoController".
	 * 
	 * @param codigo
	 *            - Codigo do projeto que deseja remover.
	 */
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
		try{
			participacaoController.associaPosGraduando(cpfPessoa, codigoProjeto, vinculo, valorHora, qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por chamar "removeParticipacao" em
	 * "participacaoController".
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa que deseja remover a participacao.
	 * @param codigoProjeto
	 *            - Projeto que deseja remover a participacao.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		participacaoController.removeParticipacao(cpfPessoa, codigoProjeto);
	}

	/**
	 * Metodo "calculapontuacaoporparticipacao" chama o metodo de
	 * "pessoacontroller".
	 * 
	 * @param cpfpessoa
	 *            - recebe uma string que corresponde ao cpf da pessoa que
	 *            deseja calcular.
	 * @return - chama metodo de "pessoacontroller".
	 * @throws logicaexception
	 *             - enviara uma mensagem de erro caso nao ocorra o que e
	 *             esperado.
	 */
	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws Exception {
		return pessoaController.calculaPontuacaoPorParticipacao(cpfPessoa);
	}

	/**
	 * Metodo responsavel por chamar "getValorBolsa" em "pessoaController".
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa que deseja calcular a bolsa.
	 * @return - Chamara o metodo de "pessoaController".
	 * @throws Exception
	 *             - Lancara uma Exception caso os parametros nao sejam os
	 *             esperados.
	 */
	public double getValorBolsa(String cpfPessoa) throws Exception {
		return pessoaController.getValorBolsa(cpfPessoa);
	}

	public void atualizaDespesasProjeto(String cod, double montanteBolsas, double montanteCusteio,
			double montanteCapital) throws Exception {
		try {
			projetoController.atualizaDespesas(cod, montanteBolsas, montanteCusteio, montanteCapital);
		} catch (ParametroInvalidoException e) {
			throw new ParametroInvalidoException("Erro na atualizacao de projeto: " + e.getMessage());
		}
	}

	public double calculaColaboracaoUASC(String codProjeto) throws Exception {
		try {
			return projetoController.calculaColaboracaoUASC(codProjeto);
		} catch (ParametroInvalidoException e) {
			throw new ParametroInvalidoException("Erro na consulta de projeto: " + e.getMessage());
		}
	}

	public double calculaColaboracaoTotalUASC() {
		return projetoController.calculaColaboracaoTotalUASC();
	}

	public void diminuiReceita(double valor) throws LogicaException {
		try {
			this.projetoController.diminuiReceita(valor);
		} catch (LogicaException e) {
			throw new ParametroInvalidoException("Erro na atualizacao da receita da unidade: " + e.getMessage());
		}
	}

	public double calculaTotalEmCaixaUASC() {
		return this.projetoController.calculaTotalEmCaixaUASC();
	}

}
