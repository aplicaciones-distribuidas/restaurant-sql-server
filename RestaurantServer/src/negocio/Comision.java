package negocio;

import dao.ComisionDAO;
import dto.ComisionView;
import excepciones.BaseDeDatosException;

import java.util.Date;

public class Comision {
	private Long id;
	private Empleado empleado;
	private float monto;
	private Date fecha;

	public Comision(Long id, Empleado empleado, float monto, Date fecha) {
		this.id = id;
		this.empleado = empleado;
		this.monto = monto;
		this.fecha = fecha;
	}

	public Comision(Empleado empleado, float monto) {
		this.empleado = empleado;
		this.monto = monto;
		this.fecha = new Date();
	}

	public Long getId() {
		return id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public float getMonto() {
		return monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public ComisionView toView() {
		return new ComisionView(this.empleado.getNombre(), this.empleado.getApellido(), this.monto);
	}

	public void save() throws BaseDeDatosException {
		this.id = ComisionDAO.getInstancia().save(this);
	}

}
