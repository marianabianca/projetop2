package exception;

@SuppressWarnings("serial")
public class CPFInvalidoException extends ParametroInvalidoException {
	
	public CPFInvalidoException(String msg) {
		super("CPF " + msg);
	}
	
}
