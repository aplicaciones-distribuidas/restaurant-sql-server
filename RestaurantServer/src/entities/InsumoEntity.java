package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "insumos")
public class InsumoEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4417390382531223020L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String clasificacion;
	private String nombre;
	private int cantidadMinima;
	private Date fechaVencimiento;
	private Date fechaCompra;
	@OneToOne(cascade = CascadeType.ALL)
	private ProveedorEntity proveedor;
	private float cantidad;
	

	public InsumoEntity(Long id, String clasificacion, String nombre, int cantidadMinima, Date fechaVencimiento, Date fechaCompra, ProveedorEntity proveedor, float cantidad) {
		this.clasificacion = clasificacion;
		this.nombre = nombre;
		this.cantidadMinima = cantidadMinima;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaCompra = fechaCompra;
		this.proveedor = proveedor;
		this.cantidad = cantidad;
		this.id = id;
	}
	
	public InsumoEntity() {}

	public String getClasificacion() {
		return clasificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidadMinima() {
		return cantidadMinima;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public ProveedorEntity getProveedor() {
		return proveedor;
	}

	public float getCantidad() {
		return cantidad;
	}
	
	public Long getId() {
		return this.id;
	}

}
