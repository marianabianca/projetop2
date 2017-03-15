package pessoa;

import exception.StringInvalidaException;
import validacao.ModuloDeValidacao;

public class PessoaController {
	private PessoaService pessoaService;
	
	public PessoaController(){
		pessoaService = new PessoaService();
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
	
	
}
