package centraldeprojeto;

import exception.StringInvalidaException;
import service.ParticipacaoService;
import service.PessoaService;
import service.ProjetoService;
import validacao.ModuloDeValidacao;

public class CentralDeProjeto {

	private PessoaService pessoaService;
	private ProjetoService projetoService;
	private ParticipacaoService participacaoService;

	public CentralDeProjeto() {
		pessoaService = new PessoaService();
		projetoService = new ProjetoService();
		participacaoService = new ParticipacaoService();
	}

	public String cadastraPessoa(String cpf, String nome, String email) throws Exception {
		String padrao = "Erro no cadastro de pessoa: ";
		try {
			ModuloDeValidacao.cpfInvalido(cpf);
		} catch (Exception e) {
			throw new StringInvalidaException(padrao + "Cpf " + e.getMessage());
		}
		try {
			ModuloDeValidacao.stringInvalida(nome);
		} catch (Exception e) {
			throw new StringInvalidaException(padrao + "Nome " + e.getMessage());
		}
		try {
			ModuloDeValidacao.stringInvalida(email);
		} catch (Exception e) {
			throw new StringInvalidaException(padrao + "Email " + e.getMessage());
		}
		try {
			pessoaService.cadastraPessoa(cpf, nome, email);
		} catch (Exception e) {
			throw new Exception(padrao + e.getMessage());
		}
		return cpf;
	}

	public void removePessoa(String cpf) {
		pessoaService.removePessoa(cpf);
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {

		ModuloDeValidacao.cpfInvalido(cpf);
		atributo = atributo.toLowerCase();
		if (!(atributo.equals("nome") || atributo.equals("email"))) {
			throw new Exception("Erro na consulta de pessoa: Pessoa nao encontrada");
		}
		try {
			return pessoaService.getInfoPessoa(cpf, atributo);
		} catch (Exception e) {
			throw new Exception("Erro na consulta de pessoa: " + e.getMessage());
		}
	}

	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		try {
			ModuloDeValidacao.stringInvalida(nome);
			ModuloDeValidacao.stringInvalida(disciplina);
			ModuloDeValidacao.rendimentoInvalido(rendimento);
			ModuloDeValidacao.stringInvalida(objetivo);
			ModuloDeValidacao.stringInvalida(periodo);
			ModuloDeValidacao.stringInvalida(dataInicio);
			ModuloDeValidacao.duracaoInvalida(duracao);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

		return projetoService.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao);
	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		try {
			ModuloDeValidacao.stringInvalida(nome);
			ModuloDeValidacao.stringInvalida(objetivo);
			ModuloDeValidacao.impactoInvalido(impacto);
			ModuloDeValidacao.rendimentoInvalido(rendimento);
			ModuloDeValidacao.inteiroInvalido(prodTecnica);
			ModuloDeValidacao.inteiroInvalido(prodAcademica);
			ModuloDeValidacao.inteiroInvalido(patentes);
			ModuloDeValidacao.dataInvalida(dataInicio);
			ModuloDeValidacao.duracaoInvalida(duracao);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

		return projetoService.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao);
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		try {
			ModuloDeValidacao.stringInvalida(nome);
			ModuloDeValidacao.stringInvalida(objetivo);
			ModuloDeValidacao.impactoInvalido(impacto);
			ModuloDeValidacao.dataInvalida(dataInicio);
			ModuloDeValidacao.duracaoInvalida(duracao);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

		return projetoService.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		try {
			ModuloDeValidacao.stringInvalida(nome);
			ModuloDeValidacao.stringInvalida(categoria);
			ModuloDeValidacao.inteiroInvalido(prodTecnica);
			ModuloDeValidacao.inteiroInvalido(prodAcademica);
			ModuloDeValidacao.inteiroInvalido(patentes);
			ModuloDeValidacao.stringInvalida(objetivo);
			ModuloDeValidacao.dataInvalida(dataInicio);
			ModuloDeValidacao.duracaoInvalida(duracao);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de projeto: " + e.getMessage());
		}

		return projetoService.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
				duracao);
	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		try {
			if (atributo.equalsIgnoreCase("Objetivo")) {
				ModuloDeValidacao.objetivoInvalido(valor);
			} else {
				ModuloDeValidacao.dataInvalida(valor);
			}
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de projeto: " + e.getMessage());
		}
		projetoService.editaProjeto(codigo, atributo, valor);
	}

	public void removeProjeto(String codigo) {
		projetoService.removeProjeto(codigo);
	}

	// TODO AJEITAR OS TESTES
	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		ModuloDeValidacao.codigoInvalido(codigo);
		atributo.toLowerCase();
		if (!(atributo.equals("data de inicio") || atributo.equals("objetivo"))) {
			throw new Exception("Atributo invalido.");
		}

		return pessoaService.getInfoPessoa(codigo, atributo);
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

		participacaoService.associaProfissional(cpfPessoa, valorHora, qntHoras);
	}

	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		ModuloDeValidacao.cpfInvalido(cpfPessoa);
		ModuloDeValidacao.stringInvalida(atributo);
		ModuloDeValidacao.stringInvalida(valor);
		atributo = atributo.toLowerCase();

		pessoaService.editaPessoa(cpfPessoa, atributo, valor);
	}

}
