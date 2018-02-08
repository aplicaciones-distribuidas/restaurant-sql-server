package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mesas_ocupacion")
public class MesaOcupacionEntity implements Serializable {
	private static final long serialVersionUID = -5058963836374858052L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;

	@Column(name = "fecha_egreso")
	private Date fechaEgreso;

	@Column(name = "proxima_liberarse")
	private boolean proximaLiberarse;

	@Column(name = "cantidad_personas")
	private int cantidadPersonas;

	@OneToOne(cascade = CascadeType.ALL)
	private EmpleadoEntity empleado;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		name = "mesas_ocupacion_mesas",
		joinColumns = {
			@JoinColumn(table = "mesas_ocupacion", name = "mesas_ocupacion_id", referencedColumnName = "id")
		},
		inverseJoinColumns = {
			@JoinColumn(table = "mesas", name = "mesa_id", referencedColumnName = "id")
		}
	)
	private List<MesaEntity> mesaItems;

	@OneToOne(cascade = CascadeType.ALL)
	private FacturaEntity factura;

	public MesaOcupacionEntity() {
	}

	public MesaOcupacionEntity(Long id, Date fechaIngreso, Date fechaEgreso, boolean proximaLiberarse,
			int cantidadPersonas, List<MesaEntity> mesaItems, FacturaEntity factura, EmpleadoEntity empleado) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.proximaLiberarse = proximaLiberarse;
		this.cantidadPersonas = cantidadPersonas;
		this.mesaItems = mesaItems;
		this.factura = factura;
		this.empleado = empleado;
	}

	public Long getId() {
		return id;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public boolean isProximaLiberarse() {
		return proximaLiberarse;
	}

	public void setProximaLiberarse(boolean proximaLiberarse) {
		this.proximaLiberarse = proximaLiberarse;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public int getCantidadPersonas() {
		return cantidadPersonas;
	}

	public List<MesaEntity> getMesaItems() {
		return mesaItems;
	}

	public FacturaEntity getFactura() {
		return factura;
	}

	public EmpleadoEntity getEmpleado() {
		return this.empleado;
	}

}
