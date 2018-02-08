package negocio;

import dao.AreaDAO;
import excepciones.BaseDeDatosException;

public class Area {
	private Long id;
	private String nombre;

	public Area(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public Long getId() {
		return id;
	}

	public void save() throws BaseDeDatosException {
		this.id = AreaDAO.getInstancia().save(this);
	}
}
