package dto;

import java.io.Serializable;

public class ComisionView implements Serializable {
	private static final long serialVersionUID = 3784902760689504411L;
	
	private String nombre;
	private String apellido;
	private float comision;
	
	public ComisionView(String nombre, String apellido, float comision) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.comision = comision;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public float getComision() {
		return comision;
	}

}
