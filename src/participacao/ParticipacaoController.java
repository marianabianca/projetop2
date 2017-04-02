package participacao;

import java.io.Serializable;

import exception.LogicaException;
import pessoa.Pessoa;
import pessoa.PessoaController;
import projeto.Projeto;
import projeto.ProjetoController;
import projeto.ProjetoCoop;
import validacao.ValidaParticipacao;

public class ParticipacaoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FactoryDeParticipacao factoryDeParticipacao;
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ValidaParticipacao validaParticipacao;

	public ParticipacaoController(PessoaController pessoaController, ProjetoController projetoController) {
		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
		this.factoryDeParticipacao = new FactoryDeParticipacao();

		this.validaParticipacao = new ValidaParticipacao();
	}

	/**
	 * Metodo responsavel por criar uma participacao do tipo associa professor.
	 * 
	 * @param cpfProfessor
	 *            - CPF do professor.
	 * @param codigoProjeto
	 *            - Codigo do projeto.
	 * @param ehCoordenador
	 *            - Se eh coordenador.
	 * @param valorPorHora
	 *            - Valor por hora
	 * @param quantidadeDeHoras
	 *            - Quantidade de horas.
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void associaProfessor(String cpfProfessor, String codigoProjeto, boolean ehCoordenador, double valorPorHora,
			int quantidadeDeHoras) throws LogicaException {
		Pessoa professor;
		Projeto projeto;
		try {
			this.validaParticipacao.validaCpfHoras(cpfProfessor, quantidadeDeHoras);
			professor = pessoaController.getPessoa(cpfProfessor);
			projeto = projetoController.getProjeto(codigoProjeto);
			boolean temProfAssociado = projeto.temProfessorAssociado();
			boolean temCoordAssociado = projeto.temCoordenadorAssociado();
			if (projeto.isPED()) {
				this.validaParticipacao.validaAssociacaoProfessorPED(temProfAssociado, temCoordAssociado, ehCoordenador,
						valorPorHora);
			} else if (projeto.isMonitoria()) {
				this.validaParticipacao.validaAssociacaoProfessorMonitoria(temProfAssociado, valorPorHora);
			}
		} catch (Exception e) {
			throw new LogicaException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaProfessor(professor, projeto, ehCoordenador, valorPorHora,
				quantidadeDeHoras);
		adicionaParticipacaoAPessoa(cpfProfessor, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

	/**
	 * Metodo responsavel por criar uma participacao do tipo associa graduando.
	 * 
	 * @param cpfGraduando
	 *            - CPF do graduando.
	 * @param codigoProjeto
	 *            - Codigo do projeto.
	 * @param valorPorHora
	 *            - Valor da hora.
	 * @param horasSemanais
	 *            - Horas semanais.
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void associaGraduando(String cpfGraduando, String codigoProjeto, double valorPorHora, int horasSemanais)
			throws LogicaException {
		Pessoa graduando;
		Projeto projeto;
		try {
			this.validaParticipacao.validaQtdHoras_CPF_ValorHora(cpfGraduando, valorPorHora, horasSemanais, true);
			graduando = pessoaController.getPessoa(cpfGraduando);
			projeto = projetoController.getProjeto(codigoProjeto);
			boolean ehCoop = projeto instanceof ProjetoCoop;
			boolean temAluno = projeto.temGraduandoAssociado();
			boolean alunoPresenteNoProjeto = graduando.temParticipacaoEmProjeto(codigoProjeto);
			if (projeto.isPED()) {
				this.validaParticipacao.validaAssociacaoGraduandoPED(ehCoop, temAluno, alunoPresenteNoProjeto);
			}
		} catch (Exception e) {
			throw new LogicaException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaGraduando(graduando, projeto, valorPorHora,
				horasSemanais);
		adicionaParticipacaoAPessoa(cpfGraduando, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

	/**
	 * Metodo responsavel por criar uma participacao do tipo associa graduando.
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa.
	 * @param codigoProjeto
	 *            - Codigo do projeto.
	 * @param cargo
	 *            - Cargo do profissional.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws LogicaException {
		Pessoa pessoa = null;
		Projeto projeto = null;
		try {
			this.validaParticipacao.validaQtdHoras_CPF_ValorHora(cpfPessoa, valorHora, qntHoras, false);
			pessoa = pessoaController.getPessoa(cpfPessoa);
			projeto = projetoController.getProjeto(codigoProjeto);
		} catch (Exception e) {
			throw new LogicaException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		Participacao participacao = factoryDeParticipacao.criaProfissional(pessoa, projeto, cargo, valorHora, qntHoras);
		adicionaParticipacaoAPessoa(cpfPessoa, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

	/**
	 * Metodo responsavel por criar uma participacao do tipo associa pos-
	 * graduando.
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa.
	 * @param codigoProjeto
	 *            - Codigo do projeto.
	 * @param vinculo
	 *            - Vinculo.
	 * @param valorHora
	 *            - Valor da hora.
	 * @param qntHoras
	 *            - Quantidade de horas.
	 * @throws Exception
	 *             - Excecao a ser lancada.
	 */
	public void associaPosGraduando(String cpfPessoa, String codigoProjeto, String vinculo, double valorHora,
			int qntHoras) throws Exception {
		Pessoa posGraduando;
		Projeto projeto;
		posGraduando = pessoaController.getPessoa(cpfPessoa);
		projeto = projetoController.getProjeto(codigoProjeto);
		boolean isMonitoriaOrPet = projeto.isMonitoria() || projeto.isPET();
		this.validaParticipacao.validaAssociacaoPosGraduando(cpfPessoa, qntHoras, valorHora, isMonitoriaOrPet);
		Participacao participacao = factoryDeParticipacao.criaPosGraduando(posGraduando, projeto, valorHora, qntHoras,
				vinculo);
		adicionaParticipacaoAPessoa(cpfPessoa, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

	/**
	 * metodo responsavel por chamar metodo "removeParticipacao" em
	 * "pessoaController" e "projetoController" e tratar os erros nos
	 * parametros.
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa que deseja remover a participacao.
	 * @param codigoProjeto
	 *            - Projeto que deseja remover a participacao.
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws LogicaException {
		try {
			if (!projetoController.existeProjeto(codigoProjeto)) {
				throw new LogicaException("Projeto nao encontrado");
			}
			pessoaController.removeParticipacao(cpfPessoa, codigoProjeto);
			projetoController.removeParticipacao(cpfPessoa, codigoProjeto);
		} catch (Exception e) {
			throw new LogicaException("Erro na remocao de participacao: " + e.getMessage());
		}
	}

	/**
	 * metodo responsavel por chamar metodo "adicionaParticipacao" em
	 * "pessoaController".
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa que deseja adicionar determinada participacao.
	 * @param participacao
	 *            - Participacao que deseja adicionar a determinada pessoa.
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	private void adicionaParticipacaoAPessoa(String cpfPessoa, Participacao participacao) throws LogicaException {
		pessoaController.adicionaParticipacao(cpfPessoa, participacao);
	}

	/**
	 * metodo responsavel por chamar metodo "adicionaParticipacao" em
	 * "projetoController".
	 * 
	 * @param codigoProjeto
	 *            - Codigo do projeto que deseja adicionar determinada
	 *            participacao.
	 * @param participacao
	 *            - Participacao que deseja adicionar a determinado projeto.
	 * @throws LogicaException
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	private void adicionaParticipacaoAoProjeto(String codigoProjeto, Participacao participacao) throws LogicaException {
		projetoController.adicionaParticipacao(codigoProjeto, participacao);
	}

}
