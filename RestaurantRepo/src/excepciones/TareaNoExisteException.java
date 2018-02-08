package excepciones;

public class TareaNoExisteException extends Exception {
	private static final long serialVersionUID = -7966325063271566284L;
	private static final String MESSAGE = "La tarea no existe";

	public String getMessage() {
		return MESSAGE;
	}
}
