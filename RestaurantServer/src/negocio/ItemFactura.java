package negocio;

public class ItemFactura {
	
	private Producto producto;
	private int cantidad;
	private float monto;
	
	
	public ItemFactura(Producto producto, int cantidad, float monto) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.monto = monto;
	}


	public Producto getProducto() {
		return producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public float getMonto() {
		return monto;
	}

}
