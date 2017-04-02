package validacao;

import java.io.Serializable;

import exception.ParametroInvalidoException;
import exception.StringInvalidaException;

public class ValidaPessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModuloDeValidacao moduloDeValidacao;

	public ValidaPessoa() {
		this.moduloDeValidacao = new ModuloDeValidacao();
	}

	/**
	 * O metodo eh responsavel por verificar se o nome recebido eh valido.
	 * 
	 * @param nome
	 *            - Nome a ser testado.
	 * @throws stringinvalidaexception
	 *             - Excecao a ser lancada.
	 */
	public void validaNome(String nome) throws ParametroInvalidoException {
		try {
			this.moduloDeValidacao.stringInvalida(nome);
		} catch (StringInvalidaException e) {
			throw new ParametroInvalidoException("Nome " + e.getMessage());
		}
	}

	/**
	 * O metodo eh responsavel por verificar se o email recebido esta no padrao
	 * de email.
	 * 
	 * @param email
	 *            - Email a ser testado.
	 * @throws stringinvalidaexception
	 *             - Excecao a ser lancada.
	 */
	public void validaEmail(String email) throws ParametroInvalidoException {
		try {
			this.moduloDeValidacao.stringInvalida(email);
		} catch (StringInvalidaException e) {
			throw new ParametroInvalidoException("Email " + e.getMessage());
		}

		String formaEmail = "[\\._a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-z]+)+";
		if (!email.matches(formaEmail)) {
			throw new ParametroInvalidoException("Email invalido");
		}
	}

	/**
	 * O metodo eh responsavel por verificar se o cpf recebido esta no padrao de
	 * cpf.
	 * 
	 * @param cpf
	 *            - Cpf a ser testado.
	 * @throws stringinvalidaexception
	 *             - Excecao a ser lancada.
	 */
	public void validaCpf(String cpf) throws ParametroInvalidoException {
		try {
			this.moduloDeValidacao.stringInvalida(cpf);
		} catch (StringInvalidaException e) {
			throw new ParametroInvalidoException("CPF " + e.getMessage());
		}

		String formaCpf = "\\d{3}.\\d{3}.\\d{3}-\\d{2}";
		if (!cpf.matches(formaCpf)) {
			throw new ParametroInvalidoException("CPF invalido");
		}
	}

}
