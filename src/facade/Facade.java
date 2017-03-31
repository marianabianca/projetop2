package facade;

import pessoa.PessoaController;
import projeto.ProjetoController;
import participacao.ParticipacaoController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
				"acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt", 
				"acceptance_test/us6_test.txt", "acceptance_test/us6_test_exception.txt" };
		EasyAccept.main(args);
	}

	// TODO tirar pirâmides do egito, LogicaException, tratamento de exception
	// na Facade

	public Facade() {
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
		participacaoController = new ParticipacaoController(pessoaController, projetoController);
	}

	/**
	 * Método para carregar o sistema salvo. Carrega os objetos pessoaController e ProjetoController
	 * 
	 * @throws IOException - caso haja algum problema com o arquivo
	 * @throws ClassNotFoundException - caso o downcast não funcione, pois a classe não existe
	 */
	public void iniciaSistema() throws IOException, ClassNotFoundException {
		try {
			this.iniciaPessoaController();
		} catch (FileNotFoundException e) {
			this.criaArquivo("pessoa_controller.objeto", this.pessoaController);
		}
		
		try {
			this.iniciaProjetoController();
		} catch (FileNotFoundException e) {
			this.criaArquivo("projeto_controller.objeto", this.pessoaController);
		}		
	}
	
	/**
	 * Método para carregar o objeto pessoaController
	 * 
	 * @throws IOException - caso haja algum problema com o arquivo
	 * @throws ClassNotFoundException - caso o downcast não funcione, pois a classe não existe
	 */
	private void iniciaPessoaController() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("pessoa_controller.objeto");
		ObjectInputStream ois = new ObjectInputStream(fis);
		this.pessoaController = (PessoaController) ois.readObject();
		fis.close();
	}
	
	/**
	 * Método para carregar o objeto projetoController
	 * 
	 * @throws IOException - caso haja algum problema com o arquivo
	 * @throws ClassNotFoundException - caso o downcast não funcione, pois a classe não existe
	 */
	private void iniciaProjetoController() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("projeto_controller.objeto");
		ObjectInputStream ois = new ObjectInputStream(fis);
		this.projetoController = (ProjetoController) ois.readObject();
		fis.close();
	}

	/**
	 * Método para a escrita em um novo arquivo de um objeto
	 * 
	 * @param arquivo - nome do arquivo desejado
	 * @param objeto - objeto a ser escrito no arquivo
	 * @throws IOException - caso haja algum problema com o arquivo
	 */
	private void criaArquivo(String arquivo, Object objeto) throws IOException {
		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(objeto);
		fos.close();
	}

	/**
	 * Método para a escrita dos objetos pessoaController e projetoController em arquivos a fim de salva-los
	 * 
	 * @throws IOException - caso haja algum problema com o arquivo
	 */
	public void fechaSistema() throws IOException {
		FileOutputStream fos = new FileOutputStream("pessoa_controller.objeto");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this.pessoaController);
		
		fos = new FileOutputStream("projeto_controller.objeto");
		oos = new ObjectOutputStream(fos);
		oos.writeObject(this.projetoController);
		
		fos.close();
	}

	/**
	 * Metodo "cadastrapessoa" chama o metodo de "pessoacontroller".
	 * 
	 * @param cpf
	 *           - Recebe uma string que corresponde ao cpf para a nova pessoa.
	 * @param nome
	 *           - Recebe uma string que corresponde ao nome para a nova pessoa.
	 * @param email
	 *           - Recebe uma string que corresponde ao email para a nova pessoa.
	 * @return - Chama metodo de "pessoacontroller".
	 * @throws logicaexception
	 *            - Enviara uma mensagem de erro caso nao ocorra o que e
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
	 * MeTODO "getInfoPessoa" CHAMA O MeTODO DE "pessoaController".
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAcaO BUSCADA SOBRE O
	 *            USUaRIO.
	 * @return CHAMA MeTODO DE "pessoaController".
	 * @throws LogicaException
	 *             ENVIARa UMA MENSAGEM DE ERRO CASO NaO OCORRA O QUE e
	 *             ESPERADO.
	 */
	public String getInfoPessoa(String cpf, String atributo) throws LogicaException {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}

	/**
	 * MeTODO "editaPessoa" CHAMA O MeTODO DE "pessoaController".
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAcaO QUE DESEJA SER
	 *            MODIFICADA EM PESSOA.
	 * @param valor
	 *            RECEBE UMA STRING QUE CORRESPONDE PARA O QUE O ATRIBUTO SERa
	 *            MUDADO PESSOA.
	 * @throws LogicaException
	 *             ENVIARa UMA MENSAGEM DE ERRO CASO NaO OCORRA O QUE e
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
	 * MeTODO "editaPessoa" CHAMA O MeTODO DE "pessoaController".
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA REMOVER A
	 *            PESSOA.
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
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Chama metodo em "projetoController"
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Chama metodo em "projetoController".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Chama metodo em "projetoController".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 *            - Data de Início do projeto a ser criado.
	 * @param duracao
	 *            - Duracao do do projeto a ser criado.
	 * @return - Chama metodo em "projetoController".
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
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
		participacaoController.associaPosGraduando(cpfPessoa, codigoProjeto, vinculo, valorHora, qntHoras);
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
	 *             - Lancara uma Exception, caso os parâmetros nao sejam os
	 *             esperados.
	 */
	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		participacaoController.removeParticipacao(cpfPessoa, codigoProjeto);
	}

	/**
	 * MeTODO "calculaPontuacaoPorParticipacao" CHAMA O MeTODO DE
	 * "pessoaController".
	 * 
	 * @param cpfPessoa
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF DA PESSOA QUE DESEJA
	 *            CALCULAR.
	 * @return CHAMA MeTODO DE "pessoaController".
	 * @throws LogicaException
	 *             ENVIARa UMA MENSAGEM DE ERRO CASO NaO OCORRA O QUE e
	 *             ESPERADO.
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
	 *             - Lancara uma Exception caso os parâmetros nao sejam os
	 *             esperados.
	 */
	public double getValorBolsa(String cpfPessoa) throws Exception {
		return pessoaController.getValorBolsa(cpfPessoa);
	}
	
	public void atualizaDespesasProjeto(String cod, double montanteBolsas, double montanteCusteio, double montanteCapital) throws Exception {
		projetoController.atualizaDespesas(cod, montanteBolsas, montanteCusteio, montanteCapital);
	}
	
	public double calculaColaboracaoUASC(String codProjeto) throws Exception{
		return projetoController.calculaColaboracaoUASC(codProjeto);
	}
	
	public double calculaColaboracaoTotalUASC(){
		return projetoController.calculaColaboracaoTotalUASC();
	}

}
