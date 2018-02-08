package excepciones;

public class RubroNoExisteException extends Exception {
	private static final long serialVersionUID = -7966528013411566284L;
	private static final String MESSAGE = "El rubro no existe";

	public String getMessage() {
		return MESSAGE;
	}
}
