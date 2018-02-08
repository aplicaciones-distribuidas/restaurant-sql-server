package negocio;

import java.util.Date;
import java.util.List;

public class Carta {
	
	private Date fechaDesde;
	private Date fechaHasta;
	private List<ItemCarta> itemsCarta;

	public Carta(Date fechaDesde, Date fechaHasta, List<ItemCarta> itemsCarta) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.itemsCarta = itemsCarta;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public List<ItemCarta> getItemsCarta() {
		return itemsCarta;
	}
	
}
