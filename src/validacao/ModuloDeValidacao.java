package validacao;

import exceptions.*;

public class ModuloDeValidacao {

	public static void objetoNulo(Object obj) throws ObjetoNuloException {
		if (obj == null) {
			throw new ObjetoNuloException("Objeto nulo.");
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
	
}
