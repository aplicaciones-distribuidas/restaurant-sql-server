package dto;

import java.io.Serializable;

public class MesaView implements Serializable {
	private static final long serialVersionUID = -7716280088967048996L;
	private Long id;
	private int numero;
	private boolean ocupada;
	private SectorSalonView sectorSalon;

	public MesaView() {
		this.numero = 0;
		this.ocupada = false;
	}

	public MesaView(Long id, int numero, boolean ocupada, SectorSalonView sectorSalon) {
		this.id = id;
		this.numero = numero;
		this.ocupada = ocupada;
		this.sectorSalon = sectorSalon;
	}

	public Long getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public SectorSalonView getSectorSalon() {
		return sectorSalon;
	}

	@Override
	public String toString() {
		return String.format("MesaView [id => %d, numero => %d, ocupada => %s, sectorSalon => %s]", this.getId(), this
				.getNumero(), this.isOcupada(), this.sectorSalon.getNombre());
	}
}
