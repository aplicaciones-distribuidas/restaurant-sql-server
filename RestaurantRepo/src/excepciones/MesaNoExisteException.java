package excepciones;

public class MesaNoExisteException extends Exception {
	private static final long serialVersionUID = 1901626702360422810L;
	private static final String MESSAGE = "La mesa %d no existe";
	private int numero;

	public MesaNoExisteException(int numero) {
		this.numero = numero;
	}

	public String getMessage() {
		return String.format(MESSAGE, this.numero);
	}
}
