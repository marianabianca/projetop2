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
					ValidaProjeto.validaValorHora(valorPorHora);
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
				ValidaProjeto.validaValorHoraDeCoordenador(valorPorHora);
				if (projeto.temProfessorAssociado()) {
					ValidaProjeto.validaValorHoraDeMonitoria(valorPorHora);
					throw new Exception("Monitoria nao pode ter mais de um professor");
				}
			}
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaProfessor(professor, projeto, ehCoordenador, valorPorHora, quantidadeDeHoras);
		adicionaParticipacaoAPessoa(cpfProfessor, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
		
	}
	
	public void associaGraduando(String cpfGraduando, String codigoProjeto, double valorPorHora, int horasSemanais) throws Exception {
		Pessoa graduando;
		Projeto projeto;
		try {
			ValidaPessoa.validaCpf(cpfGraduando);
			ValidaProjeto.validaQtdHoras(horasSemanais);
			ValidaProjeto.validaValorHora(valorPorHora);
			graduando = pessoaController.getPessoa(cpfGraduando);
			projeto = projetoController.getProjeto(codigoProjeto);
			if (projeto instanceof ProjetoPED){
				if (projeto.temGraduandoAssociado()) {
					if (graduando.temParticipacaoEmProjeto(codigoProjeto)) {
						throw new Exception("Projetos P&D nao podem ter mais de um graduando");
					}
				}
			}
				
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaGraduando(graduando, projeto, valorPorHora, horasSemanais);
		adicionaParticipacaoAPessoa(cpfGraduando, participacao);
		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
	}

//	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
//			throws Exception {
//		Pessoa pessoa = null;
//		Projeto projeto = null;
//		try {
//			ValidaPessoa.validaCpf(cpfPessoa);
//			pessoa = pessoaController.getPessoa(cpfPessoa);
//			projeto = projetoController.getProjeto(codigoProjeto);
//			if (projeto.temAlunoAssociado()){
//				if (pessoa.temParticipacaoEmProjeto(codigoProjeto)) {
//					throw new Exception("Aluno ja esta cadastrado nesse projeto");
//				}
//				if (projeto.getClass() == ProjetoPED.class){
//					throw new Exception("Projetos P&D nao podem ter mais de um graduando");
//				}
//			}
//			ValidaProjeto.validaValorHoraDeCoordenador(valorHora);
//			ValidaProjeto.validaQtdHoras(qntHoras);
//		} catch (Exception e) {
//			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
//		}
//
//		Participacao participacao = factoryDeParticipacao.criaGraduando(pessoa, projeto, valorHora, qntHoras);
//		adicionaParticipacaoAPessoa(cpfPessoa, participacao);
//		adicionaParticipacaoAoProjeto(codigoProjeto, participacao);
//	}

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
			projetoController.removeParticipacao(cpfPessoa, codigoProjeto);
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
