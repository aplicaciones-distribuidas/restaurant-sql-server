package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "itemsCarta")
public class ItemCartaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8868000300967369328L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private float precio;
	private Date fecha;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idItemCarta")
	private List<ProductoEntity> productos;


	public ItemCartaEntity(float precio, Date fecha, List<ProductoEntity> productos) {
		this.precio = precio;
		this.fecha = fecha;
		this.productos = productos;
	}
	
	public ItemCartaEntity() {}

	public float getPrecio() {
		return precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public List<ProductoEntity> getProductos() {
		return productos;
	}
	
}
