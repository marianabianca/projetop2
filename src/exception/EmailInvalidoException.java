package exception;

@SuppressWarnings("serial")
public class EmailInvalidoException extends ParametroInvalidoException {

	public EmailInvalidoException(String msg) {
		super("Email " + msg);
	}
}
