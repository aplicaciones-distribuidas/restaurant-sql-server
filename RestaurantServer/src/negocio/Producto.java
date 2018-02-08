package negocio;

import excepciones.BaseDeDatosException;
import excepciones.ProductoSinStockException;

import java.util.Date;

public abstract class Producto {
	private Long id;
	private String rubro;
	private int caducidad;
	private float comisionMozo;
	private Date fecha;
	private float precio;
	private Area area;


	public Producto(Long id, String rubro, int caducidad, float comisionMozo, Date fecha, float precio, Area area) {
		this.id = id;
		this.rubro = rubro;
		this.caducidad = caducidad;
		this.comisionMozo = comisionMozo;
		this.fecha = fecha;
		this.precio = precio;
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRubro() {
		return rubro;
	}


	public int getCaducidad() {
		return caducidad;
	}


	public float getComisionMozo() {
		return comisionMozo;
	}


	public Date getFecha() {
		return fecha;
	}


	public float getPrecio() {
		return precio;
	}


	public Area getArea() {
		return area;
	}

	public abstract void save() throws BaseDeDatosException;

	public abstract void descontarStock() throws BaseDeDatosException, ProductoSinStockException;

}
