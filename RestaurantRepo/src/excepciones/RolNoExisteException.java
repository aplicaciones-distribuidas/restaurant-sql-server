package excepciones;

public class RolNoExisteException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "El rol no existe";

	public String getMessage() {
		return MESSAGE;
	}
}
