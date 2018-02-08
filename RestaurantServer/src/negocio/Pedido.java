package negocio;

import java.util.List;

public class Pedido {
	
	private boolean realizado;
	private List<Comanda> comanda;
	

	public Pedido(boolean realizado, List<Comanda> comanda) {
		super();
		this.realizado = realizado;
		this.comanda = comanda;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public List<Comanda> getComanda() {
		return comanda;
	}

}