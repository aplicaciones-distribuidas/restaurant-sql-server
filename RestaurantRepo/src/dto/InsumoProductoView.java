package dto;

public class InsumoProductoView {
	private float cantidad;
	private Long insumoId;


	public InsumoProductoView(float cantidad, Long insumoId) {
		super();
		this.cantidad = cantidad;
		this.insumoId = insumoId;
	}


	public float getCantidad() {
		return cantidad;
	}


	public Long getInsumoId() {
		return insumoId;
	}

}
