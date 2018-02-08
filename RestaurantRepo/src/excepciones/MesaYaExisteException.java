package excepciones;

public class MesaYaExisteException extends Exception {
	private static final long serialVersionUID = 1901626702360422810L;
	private static final String MESSAGE = "La mesa %d ya existe";
	private int numero;

	public MesaYaExisteException(int numero) {
		this.numero = numero;
	}

	public String getMessage() {
		return String.format(MESSAGE, this.numero);
	}
}
