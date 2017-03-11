package centralDeProjeto;

import com.sun.org.apache.xpath.internal.operations.Mod;

import exceptions.DuracaoInvalidaException;
import exceptions.StringInvalidaException;
import pessoa.Pessoa;
import validacao.ModuloDeValidacao;

public class CentralDeProjeto {
	
	PessoaService pessoaService;
	ProjetoService projetoService;
	ParticipacaoService participacaoService;

	public String cadastraPessoa(String cpf, String nome, String email) throws StringInvalidaException{
		ModuloDeValidacao.cpfInvalido(cpf);
		ModuloDeValidacao.stringInvalida(nome);
		ModuloDeValidacao.stringInvalida(email);
		
		return pessoaService.cadastraPessoa(cpf, nome, email);
	}
	
	public void removePessoa(String cpf){
		// TODO
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws Exception{
		ModuloDeValidacao.cpfInvalido(cpf);
		ModuloDeValidacao.stringInvalida(atributo);
		atributo.toLowerCase();
		if (!(atributo.equals("nome") || atributo.equals("email"))){
			throw new Exception("Atributo inválido.");
		}
		
		return pessoaService.getInfoPessoa(cpf, atributo);
	}
	
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			String periodo, String dataInicio, int duracao) throws StringInvalidaException, DuracaoInvalidaException{
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
			int prodAcademica, int patentes, String dataInicio, int duracao){
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
	
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao){
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
			String objetivo, String dataInicio, int duracao){
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
	
	public void editaProjeto(String codigo, String atributo, String valor){
		try {
			if (atributo.equals("Objetivo")){
				ModuloDeValidacao.objetivoInvalido(valor);
			} else {
				ModuloDeValidacao.dataInvalida(valor);
			}
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de projeto: " + e.getMessage());
		}
	}
	
	public void removeProjeto(String codigo){
		projetoService.removeProjeto(codigo);
	}
	
	public String getInfoProjeto(String codigo, String atributo){
		projetoService.getInfoProjeto(codigo, atributo);
	}
	
}
