package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("semiElaborado")
public class SemiElaboradoEntity extends ProductoEntity {
	private static final long serialVersionUID = 8502227926996442892L;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idProducto")
	private List<InsumoProductoEntity> insumosProducto;

	public SemiElaboradoEntity(Long id, String rubro, int caducidad, float comisionMozo, Date fecha, float precio, List<InsumoProductoEntity> insumosProducto, AreaEntity area) {
		super(id, rubro, caducidad, comisionMozo, fecha, precio, area);
		this.insumosProducto = insumosProducto;
	}

	public SemiElaboradoEntity() {
	}

	public List<InsumoProductoEntity> getInsumosProducto() {
		return insumosProducto;
	}

}
