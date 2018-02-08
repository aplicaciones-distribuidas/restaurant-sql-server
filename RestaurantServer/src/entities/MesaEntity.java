package entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "mesas")
public class MesaEntity implements Serializable {
	private static final long serialVersionUID = 3970956696784630019L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	private int numero;
	private boolean ocupada;
	private int capacidad;

	@ManyToOne
	private SectorSalonEntity sectorSalon;

	public MesaEntity() {
	}

	public MesaEntity(Long id, int numero, boolean ocupada, int capacidad, SectorSalonEntity sectorSalon) {
		this.id = id;
		this.numero = numero;
		this.ocupada = ocupada;
		this.capacidad = capacidad;
		this.sectorSalon = sectorSalon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isOcupada() {
		return this.ocupada;
	}

	public int getNumero() {
		return this.numero;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public SectorSalonEntity getSectorSalon() {
		return this.sectorSalon;
	}

}
