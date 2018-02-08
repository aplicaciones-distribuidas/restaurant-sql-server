package negocio;

import java.sql.Date;
import java.util.List;

public class ItemCarta {

	private float precio;
	private Date fecha;
	private List<Producto> productos;


	public ItemCarta(float precio, Date fecha, List<Producto> productos) {
		super();
		this.precio = precio;
		this.fecha = fecha;
		this.productos = productos;
	}

	public float getPrecio() {
		return precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public List<Producto> getProductos() {
		return productos;
	}
	
}
