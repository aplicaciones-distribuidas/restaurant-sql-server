package negocio;

import java.util.Date;

public class Tarea {
	
	private int cantidad;
	private boolean realizada;
	private Date fecha;
	private Producto producto;
	
	
	public Tarea(int cantidad, boolean realizada, Date fecha, Producto producto) {
		this.cantidad = cantidad;
		this.realizada = realizada;
		this.fecha = fecha;
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public boolean isRealizada() {
		return realizada;
	}


	public Date getFecha() {
		return fecha;
	}


	public Producto getProducto() {
		return producto;
	}

}
