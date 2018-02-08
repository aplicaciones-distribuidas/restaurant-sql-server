package negocio;

import java.util.List;

public class Comanda {
	
	private boolean paraFacturar;
	private boolean realizada;
	private List<ItemPedido> itemsPedido;

	public Comanda(boolean paraFacturar, boolean realizada, List<ItemPedido> itemsPedido) {
		super();
		this.paraFacturar = paraFacturar;
		this.realizada = realizada;
		this.itemsPedido = itemsPedido;
	}

	public boolean isParaFacturar() {
		return paraFacturar;
	}

	public boolean isRealizada() {
		return realizada;
	}

	public List<ItemPedido> getItemsPedido() {
		return itemsPedido;
	}

}
