package excepciones;

public class AreaNoExisteException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "El area no existe";

	public String getMessage() {
		return MESSAGE;
	}
}
