package negocio;

public class InsumoProducto {
	
	private Long id;
	private float cantidad;
	private Insumo insumo;

	public InsumoProducto(float cantidad, Insumo insumo) {
		this.cantidad = cantidad;
		this.insumo = insumo;
	}
	
	public InsumoProducto(Long id, float cantidad, Insumo insumo) {
		this.cantidad = cantidad;
		this.insumo = insumo;
		this.id = id;
	}

	public float getCantidad() {
		return cantidad;
	}

	public Insumo getInsumo() {
		return insumo;
	}
	
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	public Long getId() {
		return this.id;
	}

}
