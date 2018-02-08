package excepciones;

public class ProveedorNoExisteException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "El proveedor %s no existe";
	private String proveedor;

	public ProveedorNoExisteException(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getMessage() {
		return String.format(MESSAGE, this.proveedor);
	}
}
