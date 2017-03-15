package validacao;

import exception.StringInvalidaException;

public class validaPessoa {

	public static void validaNome(String nome) throws StringInvalidaException {
		if (nome == null) {
			throw new StringInvalidaException("Nome nulo ou vazio");
		}
		if (nome.trim().equals("")){
			throw new StringInvalidaException("Nome nulo ou vazio");			
		}
	}

	public static void validaEmail(String email) throws Exception {
		String formaEmail = "[a-zA-Z0-9]@[a-zA-Z0-9].com";
		if (!(email.matches("[a-zA-Z0-9]@[a-zA-Z0-9].com"))) {
			throw new Exception("Email invalido");
		}
	}

}
