package negocio;

import dao.MesasDAO;
import dto.MesaView;
import excepciones.BaseDeDatosException;

public class Mesa {
	private Long id;
	private int numero;
	private boolean ocupada;
	private int capacidad;
	private SectorSalon sectorSalon;

	public Mesa(Long id, int numero, boolean ocupada, int capacidad, SectorSalon sectorSalon) {
		this.id = id;
		this.numero = numero;
		this.ocupada = ocupada;
		this.capacidad = capacidad;
		this.sectorSalon = sectorSalon;
	}

	public Long getId() {
		return id;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public boolean isOcupada() {
		return this.ocupada;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public SectorSalon getSectorSalon() {
		return this.sectorSalon;
	}

	public void setSectorSalon(SectorSalon sectorSalon) {
		this.sectorSalon = sectorSalon;
	}

	public void ocupar() throws BaseDeDatosException {
		this.setOcupada(true);
		this.update();
	}

	public MesaView toView() {
		return new MesaView(this.id, this.numero, this.ocupada, this.sectorSalon != null ? this.sectorSalon.toView() : null);
	}

	@Override
	public String toString() {
		return String.format("Mesa [id => %d, numero => %d, sectorSalon => %s]", this.getId(), this.getNumero(), this
				.getSectorSalon().getNombre());
	}

	public void save() throws BaseDeDatosException {
		this.id = MesasDAO.getInstancia().save(this);
	}

	public void update() throws BaseDeDatosException {
		MesasDAO.getInstancia().update(this);
	}

}
