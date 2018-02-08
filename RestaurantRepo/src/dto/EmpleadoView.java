package dto;

import java.io.Serializable;

public class EmpleadoView implements Serializable {
	private static final long serialVersionUID = 3587901160249504518L;

	private Long id;
	private String nombre;
	private String apellido;

	public EmpleadoView(Long id, String nombre, String apellido) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
}
