package validacao;

import exceptions.*;

public class ModuloDeValidacao {

	public static void objetoNulo(Object obj) throws ObjetoNuloException {
		if (obj == null) {
			throw new ObjetoNuloException("Objeto nulo.");
		}
	}

	public static void participanteNaoEncontrado() throws ParticipanteNaoEncontradoException {
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

	public static void cpfInvalido(String cpf) throws StringInvalidaException {
		ModuloDeValidacao.stringInvalida(cpf);
		
		String ponto1 = Character.toString(cpf.charAt(3));
		String ponto2 = Character.toString(cpf.charAt(7));
		String hifen = Character.toString(cpf.charAt(11));
		
		if (!(ponto1.equals(".") & ponto2.equals(".") & hifen.equals("-"))){
			throw new StringInvalidaException("O formato do cpf é inválido.");
		}
		
	}
	
}
