package validacao;

import exceptions.*;
import gastos.Gasto;
import pessoas.Pessoa;

public class ModuloDeValidacao {

	public static void ParticipanteNulo(Pessoa participante) throws ParticipanteNulo {
		if (participante == null) {
			throw new ParticipanteNulo("Participante nulo.");
		}
	}

	public static void ParticipanteNaoEncontrado() throws ParticipanteNaoEncontrado {
		throw new ParticipanteNaoEncontrado("Participante nao encontrado.");
	}
	
	public static void StringInvalida(String str) throws StringInvalida {
		if (str == null) {
			throw new StringInvalida("String nula.");
		}
		if (str.trim().equals("")) {
			throw new StringInvalida("String vazia.");
		} 
	}
	
	public static void DuracaoInvalida(int duracao) throws DuracaoInvalida {
		if (duracao <= 0) {
			throw new DuracaoInvalida("Duracao nao pode ser menor ou igual a zero.");
		}
	}
	
	public static void GastoNulo(Gasto gasto) throws GastoNulo {
		if (gasto == null) {
			throw new GastoNulo("Gasto nulo.");
		}
	}
	
}
