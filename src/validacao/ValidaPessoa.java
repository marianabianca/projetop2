package validacao;

import exception.StringInvalidaException;

public class ValidaPessoa {

	/**
	 * O MÉTODO É RESPONSÁVEL POR VERIFICAR SE O NOME RECEBIDO É VÁLIDO.
	 * 
	 * @param nome
	 *            NOME A SER TESTADO.
	 * @throws StringInvalidaException
	 *             EXCEÇÃO A SER LANÇADA.
	 */
	public static void validaNome(String nome) throws StringInvalidaException {
		try {
			ModuloDeValidacao.stringInvalida(nome);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("Nome " + e.getMessage());
		}
	}

	/**
	 * O MÉTODO É RESPONSÁVEL POR VERIFICAR SE O EMAIL RECEBIDO ESTÁ NO PADRÃO
	 * DE EMAIL.
	 * 
	 * @param email
	 *            EMAIL A SER TESTADO.
	 * @throws StringInvalidaException
	 *             EXCEÇÃO A SER LANÇADA.
	 */
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

	/**
	 * O MÉTODO É RESPONSÁVEL POR VERIFICAR SE O CPF RECEBIDO ESTÁ NO PADRÃO DE
	 * CPF.
	 * 
	 * @param cpf
	 *            CPF A SER TESTADO.
	 * @throws StringInvalidaException
	 *             EXCEÇÃO A SER LANÇADA.
	 */
	public static void validaCpf(String cpf) throws StringInvalidaException {
		try {
			ModuloDeValidacao.stringInvalida(cpf);
		} catch (StringInvalidaException e) {
			throw new StringInvalidaException("CPF " + e.getMessage());
		}

		String formaCpf = "\\d{3}.\\d{3}.\\d{3}-\\d{2}";
		if (!cpf.matches(formaCpf)) {
			throw new StringInvalidaException("CPF invalido");
		}
	}

}
