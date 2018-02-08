package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "comisiones")
public class ComisionEntity implements Serializable {
	private static final long serialVersionUID = 5705275033395515062L;

	@Id
	@GeneratedValue
	private Long id;
	private float monto;
	private Date fecha;

	@ManyToOne(cascade = CascadeType.ALL)
	private EmpleadoEntity empleado;

	public ComisionEntity(Long id, EmpleadoEntity empleado, float monto, Date fecha) {
		this.id = id;
		this.empleado = empleado;
		this.monto = monto;
		this.fecha = fecha;
	}

	public ComisionEntity() {
	}

	public Long getId() {
		return id;
	}

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	public float getMonto() {
		return monto;
	}

	public Date getFecha() {
		return fecha;
	}

}
