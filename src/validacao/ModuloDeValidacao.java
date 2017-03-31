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
	 * O método é responsável por verificar se a string recebida é válida, no
	 * caso se ela é vazia ou nula.
	 * 
	 * @param str
	 *            - String a ser testada.
	 * @throws stringinvalidaexception
	 *             - exceção a ser lançada.
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
	 * O método é responsável por verificar se a string recebida está no padrão
	 * de data.
	 * 
	 * @param str
	 *            - String a ser testada.
	 * @throws exception
	 *             - Exceção a ser lançada.
	 */
	public void dataInvalida(String str) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		if (!(str.length() == 10)) {
			throw new Exception("Formato de data invalido");
		}
		try {
			sdf.parse(str);
		} catch (ParseException e) {
			throw new Exception("Formato de data invalido");
		}
	}

	public void numeroNegativo(double numero) throws ParametroInvalidoException {
		if (numero < 0){
			throw new ParametroInvalidoException("valor negativo");
		}
	}

}