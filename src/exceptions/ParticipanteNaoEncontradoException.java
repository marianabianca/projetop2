package exceptions;

@SuppressWarnings("serial")
public class ParticipanteNaoEncontradoException extends Exception {
	public ParticipanteNaoEncontradoException(String msg) {
		super(msg);
	}
}
