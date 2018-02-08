package excepciones;

public class EmpleadoNoExisteException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "El empleado seleccionado no existe";

	public String getMessage() {
		return MESSAGE;
	}
}
