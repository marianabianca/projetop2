package validacao;

import exception.StringInvalidaException;

public class ValidaPessoa {

	public static void validaNome(String nome) throws StringInvalidaException {
		try {
			ModuloDeValidacao.stringInvalida(nome);			
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Nome " + e.getMessage());
		}
	}

	public static void validaEmail(String email) throws Exception {
		try {
			ModuloDeValidacao.stringInvalida(email);			
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Email " + e.getMessage());
		}
		
		String formaEmail = "[\\._a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-z]+)+";
		if (!email.matches(formaEmail)) {
			throw new Exception("Email invalido");
		}
	}

	public static void validaCpf(String cpf) throws StringInvalidaException {
		try {
			ModuloDeValidacao.stringInvalida(cpf);			
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("CPF " + e.getMessage());
		}		
		
		String formaCpf = "\\d{3}.\\d{3}.\\d{3}-\\d{2}";
		if (!cpf.matches(formaCpf)){
			throw new StringInvalidaException("CPF invalido");
		}
	}

}
