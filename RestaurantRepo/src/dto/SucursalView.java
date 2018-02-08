package dto;

import java.io.Serializable;

public class SucursalView implements Serializable {
	private static final long serialVersionUID = -1877463355094662434L;
	private Long id;
	private String nombre;
	private String ubicacion;
	private int capacidad;

	public SucursalView(Long id, String nombre, String ubicacion, int capacidad) {
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	@Override
	public String toString() {
		return String.format("SucursalView [id => %d, nombre => %s, ubicacion => %s, capacidad => %d]", this.getId(), this.getNombre(), this.getUbicacion(), this.getCapacidad());
	}
}
