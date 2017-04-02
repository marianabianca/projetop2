package validacao;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import exception.*;

public class ModuloDeValidacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O metodo eh responsavel por verificar se a string recebida eh valida, no
	 * caso se ela eh vazia ou nula.
	 * 
	 * @param str
	 *            - String a ser testada.
	 * @throws StringInvalidaException
	 *             - Excecao a ser lancada.
	 */
	public void stringInvalida(String str) throws StringInvalidaException {
		if (str == null) {
			throw new StringInvalidaException("nulo ou vazio");
		}
		if (str.trim().equals("")) {
			throw new StringInvalidaException("nulo ou vazio");
		}
	}

	/**
	 * O metodo eh responsavel por verificar se a string recebida esta no padrao
	 * de data.
	 * 
	 * @param str
	 *            - String a ser testada.
	 * @throws LogicaException
	 *             - Excecao a ser lancada.
	 */
	public void dataInvalida(String str) throws LogicaException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		if (!(str.length() == 10)) {
			throw new LogicaException("Formato de data invalido");
		}
		try {
			sdf.parse(str);
		} catch (ParseException e) {
			throw new LogicaException("Formato de data invalido");
		}
	}

	/**
	 * O metodo eh responsavel por verificar se o numero enviado eh negativo.
	 * 
	 * @param numero
	 *            - Numero a ser testado.
	 * @throws ParametroInvalidoException
	 *             - Excecao a ser lancada.
	 */
	public void numeroNegativo(double numero) throws ParametroInvalidoException {
		if (numero < 0) {
			throw new ParametroInvalidoException("valor negativo");
		}
	}

}