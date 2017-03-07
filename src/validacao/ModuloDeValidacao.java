package validacao;

import exceptions.*;
import gastos.Despesas;
import pessoas.Pessoa;

public class ModuloDeValidacao {

	public static void ParticipanteNulo(Pessoa participante) throws ParticipanteNuloException {
		if (participante == null) {
			throw new ParticipanteNuloException("Participante nulo.");
		}
	}

	public static void ParticipanteNaoEncontrado() throws ParticipanteNaoEncontradoException {
		throw new ParticipanteNaoEncontradoException("Participante nao encontrado.");
	}
	
	public static void StringInvalida(String str) throws StringInvalidaException {
		if (str == null) {
			throw new StringInvalidaException("String nula.");
		}
		if (str.trim().equals("")) {
			throw new StringInvalidaException("String vazia.");
		} 
	}
	
	public static void DuracaoInvalida(int duracao) throws DuracaoInvalidaException {
		if (duracao <= 0) {
			throw new DuracaoInvalidaException("Duracao nao pode ser menor ou igual a zero.");
		}
	}
	
	public static void GastoNulo(Despesas gasto) throws GastoNuloException {
		if (gasto == null) {
			throw new GastoNuloException("Gasto nulo.");
		}
	}
	
}
