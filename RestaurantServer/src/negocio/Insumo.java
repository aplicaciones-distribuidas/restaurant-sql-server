package negocio;

import java.util.Date;

public class Insumo {
	
	private String clasificacion;
	private String nombre;
	private int cantidadMinima;
	private Date fechaVencimiento;
	private Date fechaCompra;
	private Proveedor proveedor;
	private float cantidad;
	private Long id;
	

	public Insumo(String clasificacion, String nombre, int cantidadMinima, Date fechaVencimiento, Date fechaCompra, Proveedor proveedor, float cantidad) {
		this.clasificacion = clasificacion;
		this.nombre = nombre;
		this.cantidadMinima = cantidadMinima;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaCompra = fechaCompra;
		this.proveedor = proveedor;
		this.cantidad = cantidad;
	}
	
	public Insumo(Long id, String clasificacion, String nombre, int cantidadMinima, Date fechaVencimiento, Date fechaCompra, Proveedor proveedor, float cantidad) {
		this.clasificacion = clasificacion;
		this.nombre = nombre;
		this.cantidadMinima = cantidadMinima;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaCompra = fechaCompra;
		this.proveedor = proveedor;
		this.cantidad = cantidad;
		this.id = id;
	}

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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	public Long getId() {
		return this.id;
	}

}
