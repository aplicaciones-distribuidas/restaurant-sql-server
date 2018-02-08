package excepciones;

public class ProductoSinStockException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "No hay stock del producto seleccionado";

	public String getMessage() {
		return MESSAGE;
	}
}
