package validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import exception.*;

public class ModuloDeValidacao {

	/**
	 * O MÉTODO É RESPONSÁVEL POR VERIFICAR SE A STRING RECEBIDA É VÁLIDA, NO
	 * CASO SE ELA É VAZIA OU NULA.
	 * 
	 * @param str
	 *            STRING A SER TESTADA.
	 * @throws StringInvalidaException
	 *             EXCEÇÃO A SER LANÇADA.
	 */
	public static void stringInvalida(String str) throws StringInvalidaException {
		if (str == null) {
			throw new StringInvalidaException("nulo ou vazio");
		}
		if (str.trim().equals("")) {
			throw new StringInvalidaException("nulo ou vazio");
		}
	}

	/**
	 * O MÉTODO É RESPONSÁVEL POR VERIFICAR SE A STRING RECEBIDA ESTÁ NO PADRÃO
	 * DE DATA.
	 * 
	 * @param str
	 *            STRING A SER TESTADA.
	 * @throws Exception
	 *             EXCEÇÃO A SER LANÇADA.
	 */
	public static void dataInvalida(String str) throws Exception {
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

}