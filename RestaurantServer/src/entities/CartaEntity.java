package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cartas")
public class CartaEntity implements Serializable {
	private static final long serialVersionUID = -8885061183470400329L;

	@Id
	@GeneratedValue
	private Long id;

	private Date fechaDesde;
	private Date fechaHasta;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCarta")
	private List<ItemCartaEntity> itemsCarta;

	public CartaEntity(Date fechaDesde, Date fechaHasta, List<ItemCartaEntity> itemsCarta) {
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.itemsCarta = itemsCarta;
	}

	public CartaEntity() {
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public List<ItemCartaEntity> getItemsCarta() {
		return itemsCarta;
	}

}
