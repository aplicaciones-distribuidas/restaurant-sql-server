package excepciones;

public class NoHayMesasDisponiblesException extends Exception {
	private static final long serialVersionUID = 7578195966873156954L;
	private static final String MESSAGE = "No hay mesas disponibles";

	public String getMessage() {
		return MESSAGE;
	}
}
