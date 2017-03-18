package participacao;

import pessoa.Pessoa;
import pessoa.PessoaController;
import projeto.Projeto;
import projeto.ProjetoController;
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
		factoryDeParticipacao = new FactoryDeParticipacao();
	}

	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora,
			int qntHoras) throws Exception {
		Pessoa pessoa = pessoaController.getPessoa(cpfPessoa);
		Projeto projeto = projetoController.getProjeto(codigoProjeto);
		try {
			ValidaPessoa.validaCpf(cpfPessoa);
			// ModuloDeValidacao.codigoInvalido(codigoProjeto);
			if (valorHora < 0) {
				throw new Exception("Valor da hora Invalido");
			}
			ValidaProjeto.validaDuracao(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		Participacao participacao = factoryDeParticipacao.criaProfessor(pessoa, projeto, coordenador, valorHora, qntHoras);
		adicionaParticipacaoAPessoa(cpfPessoa, codigoProjeto, participacao);
		adicionaParticipacaoAoProjeto(cpfPessoa, codigoProjeto, participacao);
	}

	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
			throws Exception {
		Pessoa pessoa = pessoaController.getPessoa(cpfPessoa);
		Projeto projeto = projetoController.getProjeto(codigoProjeto);
		try {
			ValidaPessoa.validaCpf(cpfPessoa);
			// ModuloDeValidacao.codigoInvalido(codigoProjeto);
			if (valorHora <= 0) {
				throw new Exception("Valor da hora Invalido");
			}
			ValidaProjeto.validaDuracao(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		Participacao participacao = factoryDeParticipacao.criaGraduando(pessoa, projeto, valorHora, qntHoras);
		adicionaParticipacaoAPessoa(cpfPessoa, codigoProjeto, participacao);
		adicionaParticipacaoAoProjeto(cpfPessoa, codigoProjeto, participacao);
	}

	// TODO TESTAR O CARGO
	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws Exception {
		Pessoa pessoa = pessoaController.getPessoa(cpfPessoa);
		Projeto projeto = projetoController.getProjeto(codigoProjeto);
		try {
			ValidaPessoa.validaCpf(cpfPessoa);
			// ModuloDeValidacao.codigoInvalido(codigoProjeto);
			if (valorHora <= 0) {
				throw new Exception("Valor da hora Invalido");
			}
			ValidaProjeto.validaDuracao(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		Participacao participacao = factoryDeParticipacao.criaProfissional(pessoa, projeto, cargo, valorHora, qntHoras);
		adicionaParticipacaoAPessoa(cpfPessoa, codigoProjeto, participacao);
		adicionaParticipacaoAoProjeto(cpfPessoa, codigoProjeto, participacao);
	}

	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		ValidaPessoa.validaCpf(cpfPessoa);
		ModuloDeValidacao.stringInvalida(atributo);
		ModuloDeValidacao.stringInvalida(valor);
		atributo = atributo.toLowerCase();
		pessoaController.editaPessoa(cpfPessoa, atributo, valor);
	}

	private void adicionaParticipacaoAPessoa(String cpfPessoa, String codigoProjeto, Participacao participacao)
			throws Exception {
		pessoaController.adicionaParticipacao(cpfPessoa, codigoProjeto, participacao);
	}

	private void adicionaParticipacaoAoProjeto(String cpfPessoa, String codigoProjeto, Participacao participacao)
			throws Exception {
		projetoController.adicionaParticipacao(cpfPessoa, codigoProjeto, participacao);
	}

}
