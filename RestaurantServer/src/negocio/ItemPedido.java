package negocio;

public class ItemPedido {

	private int cantidad;
	private ItemCarta item;


	public ItemPedido(int cantidad, ItemCarta item) {
		super();
		this.cantidad = cantidad;
		this.item = item;
	}

	public int getCantidad() {
		return cantidad;
	}

	public ItemCarta getItem() {
		return item;
	}
	
}
