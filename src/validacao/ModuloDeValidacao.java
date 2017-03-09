package validacao;

import custos.Custo;
import exceptions.*;

public class ModuloDeValidacao {

	public static void ParticipanteNaoEncontrado() throws ParticipanteNaoEncontradoException {
		throw new ParticipanteNaoEncontradoException("Participante nao encontrado.");
	}
	
	public static void stringInvalida(String str) throws StringInvalidaException {
		if (str == null) {
			throw new StringInvalidaException("String nula.");
		}
		if (str.trim().equals("")) {
			throw new StringInvalidaException("String vazia.");
		} 
	}
	
	public static void duracaoInvalida(int duracao) throws DuracaoInvalidaException {
		if (duracao <= 0) {
			throw new DuracaoInvalidaException("Duracao nao pode ser menor ou igual a zero.");
		}
	}
	
	public static void inteiroNegativo(int inteiro) throws InteiroNegativoException {
		if (inteiro < 0) {
			throw new InteiroNegativoException("O número não pode ser negativo.");
		}
	}
	
	public static void objetoNulo(Custo gasto) throws GastoNuloException {
		if (gasto == null) {
			throw new GastoNuloException("Gasto nulo.");
		}
	}
	
}
