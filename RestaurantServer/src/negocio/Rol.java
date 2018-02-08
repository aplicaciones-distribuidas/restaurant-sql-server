package negocio;

import dao.RolDAO;
import excepciones.BaseDeDatosException;

public class Rol {
	private Long id;
	private String nombre;

	public Rol(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void save() throws BaseDeDatosException {
		this.id = RolDAO.getInstancia().save(this);
	}
}
