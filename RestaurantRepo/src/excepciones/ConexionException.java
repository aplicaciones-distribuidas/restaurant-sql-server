package excepciones;

public class ConexionException extends Exception {
	private static final long serialVersionUID = 6361153184348156221L;
	private static final String MESSAGE = "Error al conectarse al servidor";

	public String getMessage() {
		return MESSAGE;
	}
}
