package participacao;

import pessoa.PessoaService;
import projeto.ProjetoService;
import validacao.ModuloDeValidacao;

public class ParticipacaoController {
	
	PessoaService pessoaService;
	ProjetoService projetoService;
	ParticipacaoService participacaoService;
	
	public ParticipacaoController() {
		pessoaService = new PessoaService();
		projetoService = new ProjetoService();
		participacaoService = new ParticipacaoService();
	}
	
	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora,
			int qntHoras) throws Exception {
		try {
			ModuloDeValidacao.cpfInvalido(cpfPessoa);
			ModuloDeValidacao.codigoInvalido(codigoProjeto);
			if (valorHora < 0) {
				throw new Exception("Valor da hora Invalido");
			}
			ModuloDeValidacao.duracaoInvalida(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		participacaoService.associaProfessor(coordenador, valorHora, qntHoras);
		
		Participacao part = participacaoService.associaProfissional(cpfPessoa, valorHora, qntHoras);
		adicionaParticipacaoAoProjeto(cpfPessoa, codigoProjeto, part);
		adicionaParticipacaoAPessoa(cpfPessoa, codigoProjeto, part);
	}

	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
			throws Exception {
		try {
			ModuloDeValidacao.cpfInvalido(cpfPessoa);
			ModuloDeValidacao.codigoInvalido(codigoProjeto);
			if (valorHora <= 0) {
				throw new Exception("Valor da hora Invalido");
			}
			ModuloDeValidacao.duracaoInvalida(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		participacaoService.associaGraduando(valorHora, qntHoras);
		
		Participacao part = participacaoService.associaProfissional(cpfPessoa, valorHora, qntHoras);
		adicionaParticipacaoAoProjeto(cpfPessoa, codigoProjeto, part);
		adicionaParticipacaoAPessoa(cpfPessoa, codigoProjeto, part);
	}

	// TODO TESTAR O CARGO
	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws Exception {
		try {
			ModuloDeValidacao.cpfInvalido(cpfPessoa);
			ModuloDeValidacao.codigoInvalido(codigoProjeto);
			if (valorHora <= 0) {
				throw new Exception("Valor da hora Invalido");
			}
			ModuloDeValidacao.duracaoInvalida(qntHoras);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}

		Participacao part = participacaoService.associaProfissional(cpfPessoa, valorHora, qntHoras);
		adicionaParticipacaoAoProjeto(cpfPessoa, codigoProjeto, part);
		adicionaParticipacaoAPessoa(cpfPessoa, codigoProjeto, part);
	}

	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		ModuloDeValidacao.cpfInvalido(cpfPessoa);
		ModuloDeValidacao.stringInvalida(atributo);
		ModuloDeValidacao.stringInvalida(valor);
		atributo = atributo.toLowerCase();

		pessoaService.editaPessoa(cpfPessoa, atributo, valor);
	}

	private void adicionaParticipacaoAPessoa(String cpfPessoa, String codigoProjeto, Participacao participacao) throws Exception {
		pessoaService.adicionaParticipacao(cpfPessoa, codigoProjeto, participacao);
	}

	private void adicionaParticipacaoAoProjeto(String cpfPessoa, String codigoProjeto, Participacao participacao) throws Exception {
		projetoService.adicionaParticipacao(cpfPessoa, codigoProjeto, participacao);
	}

}
