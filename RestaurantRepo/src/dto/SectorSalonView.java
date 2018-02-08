package dto;

import java.io.Serializable;

public class SectorSalonView implements Serializable {
	private static final long serialVersionUID = -1857423351098661434L;
	private Long id;
	private String nombre;

	public SectorSalonView(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.format("SectorSalonView [id => %d, nombre => %s]", this.getId(), this.getNombre());
	}

}
