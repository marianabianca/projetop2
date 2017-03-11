package centralDeProjeto;


import Services.ParticipacaoService;
import Services.PessoaService;
import Services.ProjetoService;
import exceptions.StringInvalidaException;
import validacao.ModuloDeValidacao;

public class CentralDeProjeto {
	
	PessoaService pessoaService;
	ProjetoService projetoService;
	ParticipacaoService participacaoService;

	public String cadastraPessoa(String cpf, String nome, String email) throws StringInvalidaException{
		try {
			ModuloDeValidacao.cpfInvalido(cpf);
		} catch (Exception e) {
			throw new StringInvalidaException("Cpf " + e.getMessage());
		}
		try {
			ModuloDeValidacao.stringInvalida(nome);
		} catch (Exception e) {
			throw new StringInvalidaException("Nome " + e.getMessage());
		}try {
			ModuloDeValidacao.stringInvalida(email);
		} catch (Exception e) {
			throw new StringInvalidaException("Email " + e.getMessage());
		}
		
		return pessoaService.cadastraPessoa(cpf, nome, email);
	}
	
	public void removePessoa(String cpf){
		pessoaService.removePessoa(cpf);
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws Exception{
		ModuloDeValidacao.cpfInvalido(cpf);
		atributo.toLowerCase();
		if (!(atributo.equals("nome") || atributo.equals("email"))){
			throw new Exception("Atributo inválido.");
		}
		
		return pessoaService.getInfoPessoa(cpf, atributo);
	}
	
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			String periodo, String dataInicio, int duracao) throws Exception{
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
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception{
		try{
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
		
		return projetoService.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes, dataInicio, duracao);
	}
	
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) throws Exception{
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
			String objetivo, String dataInicio, int duracao) throws Exception{
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
		
		return projetoService.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao);
	}
	
	public void editaProjeto(String codigo, String atributo, String valor) throws Exception{
		try {
			if (atributo.equalsIgnoreCase("Objetivo")){
				ModuloDeValidacao.objetivoInvalido(valor);
			} else {
				ModuloDeValidacao.dataInvalida(valor);
			}
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de projeto: " + e.getMessage());
		}
		projetoService.editaProjeto(codigo, atributo, valor);
	}
	
	public void removeProjeto(String codigo){
		projetoService.removeProjeto(codigo);
	}
	
	// TODO AJEITAR OS TESTES
	public String getInfoProjeto(String codigo, String atributo) throws Exception{
		ModuloDeValidacao.codigoInvalido(codigo);
		atributo.toLowerCase();
		if (!(atributo.equals("data de inicio") || atributo.equals("objetivo"))){
			throw new Exception("Atributo inválido.");
		}
		
		return pessoaService.getInfoPessoa(codigo, atributo);
	}
	
	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora,
			int qntHoras) {
		try {
			ModuloDeValidacao.cpfInvalido(cpf);
			ModuloDeValidacao.codigoInvalido(codigo);
			if (valorHora <= 0) {
				throw new NumeroInvalidoException("Valor da hora Inválido");
			}
			if (qntHoras <= 0) {
				throw new NumeroInvalidoException("Quantidade de horas invalida");
			}
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
		participacaoService.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);		
	}

	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras) {
		try {
			ModuloDeValidacao.cpfInvalido(cpf);
			ModuloDeValidacao.codigoInvalido(codigo);
			if (valorHora <= 0) {
				throw new NumeroInvalidoException("Valor da hora Inválido");
			}
			if (qntHoras <= 0) {
				throw new NumeroInvalidoException("Quantidade de horas invalida");
			}
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
		participacaoService.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);		
	}

	// TODO TESTAR O CARGO
	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) {
		try {
			ModuloDeValidacao.cpfInvalido(cpf);
			ModuloDeValidacao.codigoInvalido(codigo);
			if (valorHora <= 0) {
				throw new NumeroInvalidoException("Valor da hora Inválido");
			}
			if (qntHoras <= 0) {
				throw new NumeroInvalidoException("Quantidade de horas invalida");
			}
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
		participacaoService.associaProfissional(cpfPessoa, codigoProjeto, cargo, valorHora, qntHoras);
	}
	
}
