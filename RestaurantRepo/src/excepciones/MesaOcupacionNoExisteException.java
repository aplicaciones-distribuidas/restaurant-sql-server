package excepciones;

public class MesaOcupacionNoExisteException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "La mesa seleccionada no está ocupada";

	public String getMessage() {
		return MESSAGE;
	}
}
