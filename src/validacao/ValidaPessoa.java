package validacao;

import exception.CPFInvalidoException;
import exception.EmailInvalidoException;
import exception.NomeInvalidoException;
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
	public static void validaNome(String nome) throws NomeInvalidoException {
		try {
			ModuloDeValidacao.stringInvalida(nome);
		} catch (StringInvalidaException e) {
			throw new NomeInvalidoException(e.getMessage());
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
	public static void validaEmail(String email) throws EmailInvalidoException {
		try {
			ModuloDeValidacao.stringInvalida(email);
		} catch (StringInvalidaException e) {
			throw new EmailInvalidoException(e.getMessage());
		}

		String formaEmail = "[\\._a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-z]+)+";
		if (!email.matches(formaEmail)) {
			throw new EmailInvalidoException("invalido");
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
	public static void validaCpf(String cpf) throws CPFInvalidoException {
		try {
			ModuloDeValidacao.stringInvalida(cpf);
		} catch (StringInvalidaException e) {
			throw new CPFInvalidoException(e.getMessage());
		}

		String formaCpf = "\\d{3}.\\d{3}.\\d{3}-\\d{2}";
		if (!cpf.matches(formaCpf)) {
			throw new CPFInvalidoException("invalido");
		}
	}

}
