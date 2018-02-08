package excepciones;

public class SucursalNoExisteException extends Exception {
	private static final long serialVersionUID = -3991361190186782457L;
	private static final String MESSAGE = "La sucursal %s no existe";
	private String nombre;

	public SucursalNoExisteException(String nombre) {
		this.nombre = nombre;
	}

	public String getMessage() {
		return String.format(MESSAGE, this.nombre);
	}

}
