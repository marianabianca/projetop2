package validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import exception.*;

public class ModuloDeValidacao {

	public static void stringInvalida(String str) throws StringInvalidaException {
		if (str == null) {
			throw new StringInvalidaException("nulo ou vazio");
		}
		if (str.trim().equals("")) {
			throw new StringInvalidaException("nulo ou vazio");
		}
	}

	public static void dataInvalida(String str) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		if (!(str.length() == 10)){
			throw new Exception("Formato de data invalido");
		}
		try {
			sdf.parse(str);
		} catch (ParseException e) {
			throw new Exception("Formato de data invalido");
		}
	}

}