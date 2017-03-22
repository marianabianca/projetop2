package participacao;

import pessoa.Pessoa;
import pessoa.PessoaController;
import projeto.Projeto;
import projeto.ProjetoController;
import projeto.ProjetoMonitoria;
import projeto.ProjetoPED;
import projeto.ProjetoPET;
import validacao.ModuloDeValidacao;
import validacao.ValidaPessoa;
import validacao.ValidaProjeto;

public class ParticipacaoController {

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

	public void associaProfessor(String cpfProfessor, String codigoProjeto, boolean ehCoordenador, double valorPorHora,
			int quantidadeDeHoras) throws Exception {
		Pessoa professor;
		Projeto projeto;
		try {
			this.validaPessoa.validaCpf(cpfProfessor);
			this.validaProjeto.validaQtdHoras(quantidadeDeHoras);
			professor = pessoaController.getPessoa(cpfProfessor);
			projeto = projetoController.getProjeto(codigoProjeto);
			if (projeto.getClass() == ProjetoPED.class) {
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
			if (projeto.getClass() == ProjetoMonitoria.class) {
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
			if (projeto instanceof ProjetoPED) {
				ProjetoPED ped = (ProjetoPED) projeto;
				if (projeto.temGraduandoAssociado() && !ped.getCategoria().equalsIgnoreCase("coop")) {
					throw new Exception("Projetos P&D nao podem ter mais de um graduando");
				} else if (projeto.temGraduandoAssociado() && ped.getCategoria().equalsIgnoreCase("coop")) {
					if (graduando.temParticipacaoEmProjeto(codigoProjeto)) {
						throw new Exception("Aluno ja esta cadastrado nesse projeto");
					}
				}
			}

		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaGraduando(graduando, projeto, valorPorHora,
				horasSemanais);
		adicionaParticipacaoAPessoa(cpfGraduando, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

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

	public void associaPosGraduando(String cpfPessoa, String codigoProjeto, String vinculo, double valorHora,
			int qntHoras) throws Exception {
		Pessoa posGraduando;
		Projeto projeto;
		try {
			this.validaPessoa.validaCpf(cpfPessoa);
			this.validaProjeto.validaQtdHoras(qntHoras);
			this.validaProjeto.validaValorHora(valorHora);
			posGraduando = pessoaController.getPessoa(cpfPessoa);
			projeto = projetoController.getProjeto(codigoProjeto);
			if (projeto instanceof ProjetoMonitoria || projeto instanceof ProjetoPET) {
				throw new Exception("Tipo de projeto invalido para pos graduando");
			}
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaPosGraduando(posGraduando, projeto, valorHora, qntHoras,
				vinculo);
		adicionaParticipacaoAPessoa(cpfPessoa, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

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

	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		this.validaPessoa.validaCpf(cpfPessoa);
		this.moduloDeValidacao.stringInvalida(atributo);
		this.moduloDeValidacao.stringInvalida(valor);
		atributo = atributo.toLowerCase();
		pessoaController.editaPessoa(cpfPessoa, atributo, valor);
	}

	private void adicionaParticipacaoAPessoa(String cpfPessoa, Participacao participacao) throws Exception {
		pessoaController.adicionaParticipacao(cpfPessoa, participacao);
	}

	private void adicionaParticipacaoAoProjeto(String codigoProjeto, Participacao participacao) throws Exception {
		projetoController.adicionaParticipacao(codigoProjeto, participacao);
	}

}
