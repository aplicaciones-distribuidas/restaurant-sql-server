package excepciones;

public class InsumoNoExisteException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "El insumo seleccionado no existe";

	public String getMessage() {
		return MESSAGE;
	}
}
