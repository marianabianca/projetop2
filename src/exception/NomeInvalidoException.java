package exception;

@SuppressWarnings("serial")
public class NomeInvalidoException extends ParametroInvalidoException {

	public NomeInvalidoException(String msg) {
		super("Nome " + msg);
	}
}
