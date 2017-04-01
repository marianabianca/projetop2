package participacao;

import java.io.Serializable;

import pessoa.Pessoa;
import pessoa.PessoaController;
import projeto.Projeto;
import projeto.ProjetoController;
import projeto.ProjetoCoop;
import projeto.ProjetoMonitoria;
import projeto.ProjetoPET;
import projeto.ProjetoPIBICPIBITI;
import validacao.ModuloDeValidacao;
import validacao.ValidaPessoa;
import validacao.ValidaProjeto;

public class ParticipacaoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FactoryDeParticipacao factoryDeParticipacao;
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ValidaPessoa validaPessoa;
	private ValidaProjeto validaProjeto;
	private ModuloDeValidacao moduloDeValidacao;

	public ParticipacaoController(PessoaController pessoaController, ProjetoController projetoController) {
		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
		this.factoryDeParticipacao = new FactoryDeParticipacao();

		this.validaPessoa = new ValidaPessoa();
		this.validaProjeto = new ValidaProjeto();
		this.moduloDeValidacao = new ModuloDeValidacao();
	}

	/**
	 * TODO
	 * 
	 * @param cpfProfessor
	 * @param codigoProjeto
	 * @param ehCoordenador
	 * @param valorPorHora
	 * @param quantidadeDeHoras
	 * @throws Exception
	 */
	public void associaProfessor(String cpfProfessor, String codigoProjeto, boolean ehCoordenador, double valorPorHora,
			int quantidadeDeHoras) throws Exception {
		Pessoa professor;
		Projeto projeto;
		try {
			this.validaPessoa.validaCpf(cpfProfessor);
			this.validaProjeto.validaQtdHoras(quantidadeDeHoras);
			professor = pessoaController.getPessoa(cpfProfessor);
			projeto = projetoController.getProjeto(codigoProjeto);
			if (projeto.isPED()) {
				if (!ehCoordenador) {
					this.validaProjeto.validaValorHora(valorPorHora);
					if (projeto.temProfessorAssociado()) {
						throw new Exception("Projetos P&D nao podem ter mais de um professor");
					}
				} else {
					this.validaProjeto.validaValorHora(valorPorHora);
					if (projeto.temProfessorAssociado()) {
						if (projeto.temCoordenadorAssociado()) {
							throw new Exception("Projetos P&D nao podem ter mais de um coordenador");
						} else {
							throw new Exception("Projetos P&D nao podem ter mais de um professor associado");
						}
					}
				}
			}
			if (projeto.isMonitoria()) {
				this.validaProjeto.validaValorHoraMenorQueZero(valorPorHora);
				if (projeto.temProfessorAssociado()) {
					this.validaProjeto.validaValorHoraDeMonitoria(valorPorHora);
					throw new Exception("Monitoria nao pode ter mais de um professor");
				}
			}
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaProfessor(professor, projeto, ehCoordenador, valorPorHora,
				quantidadeDeHoras);
		adicionaParticipacaoAPessoa(cpfProfessor, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);

	}

	/**
	 * TODO
	 * 
	 * @param cpfGraduando
	 * @param codigoProjeto
	 * @param valorPorHora
	 * @param horasSemanais
	 * @throws Exception
	 */
	public void associaGraduando(String cpfGraduando, String codigoProjeto, double valorPorHora, int horasSemanais)
			throws Exception {
		Pessoa graduando;
		Projeto projeto;
		try {
			this.validaPessoa.validaCpf(cpfGraduando);
			this.validaProjeto.validaQtdHoras(horasSemanais);
			this.validaProjeto.validaValorHoraMenorQueZero(valorPorHora);
			graduando = pessoaController.getPessoa(cpfGraduando);
			projeto = projetoController.getProjeto(codigoProjeto);
			if (projeto instanceof ProjetoCoop && projeto.temGraduandoAssociado()) {
				if (graduando.temParticipacaoEmProjeto(codigoProjeto)) {
					throw new Exception("Aluno ja esta cadastrado nesse projeto");
				}
			} else if (projeto instanceof ProjetoPIBICPIBITI && projeto.temGraduandoAssociado()) {
				throw new Exception("Projetos P&D nao podem ter mais de um graduando");
			}
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaGraduando(graduando, projeto, valorPorHora,
				horasSemanais);
		adicionaParticipacaoAPessoa(cpfGraduando, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

	/**
	 * TODO
	 * 
	 * @param cpfPessoa
	 * @param codigoProjeto
	 * @param cargo
	 * @param valorHora
	 * @param qntHoras
	 * @throws Exception
	 */
	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws Exception {
		Pessoa pessoa = null;
		Projeto projeto = null;
		try {
			this.validaPessoa.validaCpf(cpfPessoa);
			pessoa = pessoaController.getPessoa(cpfPessoa);
			projeto = projetoController.getProjeto(codigoProjeto);
			this.validaProjeto.validaValorHora(valorHora);
			this.validaProjeto.validaQtdHoras(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		Participacao participacao = factoryDeParticipacao.criaProfissional(pessoa, projeto, cargo, valorHora, qntHoras);
		adicionaParticipacaoAPessoa(cpfPessoa, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

	/**
	 * TODO
	 * 
	 * @param cpfPessoa
	 * @param codigoProjeto
	 * @param vinculo
	 * @param valorHora
	 * @param qntHoras
	 * @throws Exception
	 */
	public void associaPosGraduando(String cpfPessoa, String codigoProjeto, String vinculo, double valorHora,
			int qntHoras) throws Exception {
		Pessoa posGraduando;
		Projeto projeto;
		this.validaPessoa.validaCpf(cpfPessoa);
		this.validaProjeto.validaQtdHoras(qntHoras);
		this.validaProjeto.validaValorHora(valorHora);
		posGraduando = pessoaController.getPessoa(cpfPessoa);
		projeto = projetoController.getProjeto(codigoProjeto);
		if (projeto instanceof ProjetoMonitoria || projeto instanceof ProjetoPET) {
			throw new Exception("Tipo de projeto invalido para pos graduando");
		}
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
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		try {
			if (!projetoController.existeProjeto(codigoProjeto)) {
				throw new Exception("Projeto nao encontrado");
			}
			pessoaController.removeParticipacao(cpfPessoa, codigoProjeto);
			projetoController.removeParticipacao(cpfPessoa, codigoProjeto);
		} catch (Exception e) {
			throw new Exception("Erro na remocao de participacao: " + e.getMessage());
		}
	}

	/**
	 * TODO
	 * 
	 * @param cpfPessoa
	 * @param atributo
	 * @param valor
	 * @throws Exception
	 */
	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		this.validaPessoa.validaCpf(cpfPessoa);
		this.moduloDeValidacao.stringInvalida(atributo);
		this.moduloDeValidacao.stringInvalida(valor);
		atributo = atributo.toLowerCase();
		pessoaController.editaPessoa(cpfPessoa, atributo, valor);
	}

	/**
	 * metodo responsavel por chamar metodo "adicionaParticipacao" em
	 * "pessoaController".
	 * 
	 * @param cpfPessoa
	 *            - CPF da pessoa que deseja adicionar determinada participacao.
	 * @param participacao
	 *            - Participacao que deseja adicionar a determinada pessoa.
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	private void adicionaParticipacaoAPessoa(String cpfPessoa, Participacao participacao) throws Exception {
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
	 * @throws Exception
	 *             - Lancara uma Exception, caso os parametros nao sejam os
	 *             esperados.
	 */
	private void adicionaParticipacaoAoProjeto(String codigoProjeto, Participacao participacao) throws Exception {
		projetoController.adicionaParticipacao(codigoProjeto, participacao);
	}

}
