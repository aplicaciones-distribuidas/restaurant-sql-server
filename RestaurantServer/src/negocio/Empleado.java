package negocio;

import dao.EmpleadoDAO;
import dto.EmpleadoView;
import excepciones.BaseDeDatosException;

import java.util.ArrayList;
import java.util.List;

public class Empleado {
	private Long id;
	private String nombre;
	private String apellido;
	private int porcentajeComision;
	private Rol rol;
	private List<Comision> comisiones = new ArrayList<>();
	private SectorSalon sectorSalon;


	public Empleado(Long id, String nombre, String apellido, int porcentajeComision, Rol rol, List<Comision> comisiones, SectorSalon sectorSalon) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.porcentajeComision = porcentajeComision;
		this.rol = rol;
		this.comisiones = comisiones;
		this.sectorSalon = sectorSalon;
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


	public int getPorcentajeComision() {
		return porcentajeComision;
	}


	public Rol getRol() {
		return rol;
	}


	public List<Comision> getComisiones() {
		return comisiones;
	}

	public SectorSalon getSectorSalon() {
		return sectorSalon;
	}

	public void save() throws BaseDeDatosException {
		this.id = EmpleadoDAO.getInstancia().save(this);
	}

	public EmpleadoView toView() {
		return new EmpleadoView(this.id, this.nombre, this.apellido);
	}
}
