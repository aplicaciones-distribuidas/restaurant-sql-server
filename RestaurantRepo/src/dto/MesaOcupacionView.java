package dto;

import java.io.Serializable;
import java.util.List;

public class MesaOcupacionView implements Serializable {
	private static final long serialVersionUID = -8294287495190172666L;
	private Long id;
	private List<MesaView> mesaItems;
	private FacturaView factura;

	public MesaOcupacionView(Long id, List<MesaView> mesaItems, FacturaView factura) {
		this.id = id;
		this.mesaItems = mesaItems;
		this.factura = factura;
	}

	public Long getId() {
		return id;
	}

	public List<MesaView> getMesaItems() {
		return mesaItems;
	}

	@Override
	public String toString() {
		return String.format("MesaOcupacionView [id => %d]", this.getId());
	}

	public FacturaView getFactura() {
		return factura;
	}
	
}
