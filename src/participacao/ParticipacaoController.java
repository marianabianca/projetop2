package participacao;

import pessoa.Pessoa;
import pessoa.PessoaController;
import projeto.Projeto;
import projeto.ProjetoController;
import projeto.ProjetoMonitoria;
import projeto.ProjetoPED;
import validacao.ModuloDeValidacao;
import validacao.ValidaPessoa;
import validacao.ValidaProjeto;

public class ParticipacaoController {

	private FactoryDeParticipacao factoryDeParticipacao;
	private PessoaController pessoaController;
	private ProjetoController projetoController;

	public ParticipacaoController(PessoaController pessoaController, ProjetoController projetoController) {
		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
		this.factoryDeParticipacao = new FactoryDeParticipacao();
	}
	
	public void associaProfessor(String cpfProfessor, String codigoProjeto, boolean ehCoordenador, double valorPorHora,
			int quantidadeDeHoras) throws Exception {
		Pessoa professor;
		Projeto projeto;
		try {
			ValidaPessoa.validaCpf(cpfProfessor);
			ValidaProjeto.validaQtdHoras(quantidadeDeHoras);
			professor = pessoaController.getPessoa(cpfProfessor);
			projeto = projetoController.getProjeto(codigoProjeto);
			if (projeto.getClass() == ProjetoPED.class){
				if (!ehCoordenador){
					ValidaProjeto.validaValorHora(valorPorHora);
					if (projeto.temProfessorAssociado()){
						throw new Exception("Projetos P&D nao podem ter mais de um professor");
					}
				} else {
					ValidaProjeto.validaValorHoraDeCoordenador(valorPorHora);
					if (projeto.temProfessorAssociado()){
						if (projeto.temCoordenadorAssociado()){
							throw new Exception("Projetos P&D nao podem ter mais de um coordenador");
						} else {
							throw new Exception("Projetos P&D nao podem ter mais de um professor associado");
						}
					}
				}
			} 
			if (projeto.getClass() == ProjetoMonitoria.class) {
				ValidaProjeto.validaValorHoraDeMonitoria(valorPorHora);
			}
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaProfessor(professor, projeto, ehCoordenador, valorPorHora, quantidadeDeHoras);
		adicionaParticipacaoAPessoa(cpfProfessor, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
		
	}

//	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora,
//			int qntHoras) throws Exception {
//		Pessoa pessoa;
//		Projeto projeto;
//		try {
//			ValidaPessoa.validaCpf(cpfPessoa);
//			pessoa = pessoaController.getPessoa(cpfPessoa);
//			projeto = projetoController.getProjeto(codigoProjeto);
//			ValidaProjeto.validaQtdHoras(qntHoras);
//			if (coordenador) {
//				ValidaProjeto.validaValorHoraDeCoordenador(valorHora);
//			} else {
//				ValidaProjeto.validaValorHora(valorHora);
//			}
//			if (projeto.getClass().equals(ProjetoMonitoria.class)) {
//				ValidaProjeto.validaValorHoraDeMonitoria(valorHora);
//				if (projeto.temProfessorAssociado()){
//					ValidaProjeto.validaValorHoraDeMonitoria(valorHora);					
//				}
//			}
//			if (projeto.temProfessorAssociado() && projeto.getClass().equals(ProjetoPED.class)) {
//				if (!(projeto.temCoordenadorAssociado())) {
//					throw new Exception("Projetos P&D nao podem ter mais de um professor");
//				} else {
//					throw new Exception("Projetos P&D nao podem ter mais de um coordenador");
//				}
//			}
//		} catch (Exception e) {
//			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
//		}
//		Participacao participacao = factoryDeParticipacao.criaProfessor(pessoa, projeto, coordenador, valorHora,
//				qntHoras);
//		adicionaParticipacaoAPessoa(cpfPessoa, participacao);
//		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
//	}

	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
			throws Exception {
		Pessoa pessoa = null;
		Projeto projeto = null;
		try {
			ValidaPessoa.validaCpf(cpfPessoa);
			pessoa = pessoaController.getPessoa(cpfPessoa);
			projeto = projetoController.getProjeto(codigoProjeto);
			if (pessoa.temParticipacaoEmProjeto(codigoProjeto)) {
				throw new Exception("Aluno ja esta cadastrado nesse projeto");
			}
			ValidaProjeto.validaValorHora(valorHora);
			ValidaProjeto.validaQtdHoras(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		Participacao participacao = factoryDeParticipacao.criaGraduando(pessoa, projeto, valorHora, qntHoras);
		adicionaParticipacaoAPessoa(cpfPessoa, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws Exception {
		Pessoa pessoa = null;
		Projeto projeto = null;
		try {
			ValidaPessoa.validaCpf(cpfPessoa);
			pessoa = pessoaController.getPessoa(cpfPessoa);
			projeto = projetoController.getProjeto(codigoProjeto);
			ValidaProjeto.validaValorHora(valorHora);
			ValidaProjeto.validaQtdHoras(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		Participacao participacao = factoryDeParticipacao.criaProfissional(pessoa, projeto, cargo, valorHora, qntHoras);
		adicionaParticipacaoAPessoa(cpfPessoa, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		try {
			if (!projetoController.existeProjeto(codigoProjeto)) {
				throw new Exception("Projeto nao encontrado");
			}
			pessoaController.removeParticipacao(cpfPessoa, codigoProjeto);
		} catch (Exception e) {
			throw new Exception("Erro na remocao de participacao: " + e.getMessage());
		}
	}

	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		ValidaPessoa.validaCpf(cpfPessoa);
		ModuloDeValidacao.stringInvalida(atributo);
		ModuloDeValidacao.stringInvalida(valor);
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
