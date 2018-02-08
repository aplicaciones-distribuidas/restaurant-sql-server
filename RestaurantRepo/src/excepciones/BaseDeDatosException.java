package excepciones;

public class BaseDeDatosException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "Error al interactuar con la base de datos";

	public BaseDeDatosException(Exception original) {
		System.out.printf("BaseDeDatosException: %s", original.toString());
	}

	public String getMessage() {
		return MESSAGE;
	}
}
