package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "areas")
public class AreaEntity implements Serializable {
	private static final long serialVersionUID = -2404141548058946672L;

	@Id
	@GeneratedValue
	private Long id;

	private String nombre;

	public AreaEntity(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public AreaEntity() {
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

}
