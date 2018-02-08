package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "insumosProducto")
public class InsumoProductoEntity implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -2839065885634172603L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private float cantidad;
	@OneToOne(cascade = CascadeType.ALL)
	private InsumoEntity insumo;

	public InsumoProductoEntity(Long id, float cantidad, InsumoEntity insumo) {
		this.cantidad = cantidad;
		this.insumo = insumo;
	}
	
	public InsumoProductoEntity() {}

	public float getCantidad() {
		return cantidad;
	}

	public InsumoEntity getInsumo() {
		return insumo;
	}
	
	public Long getId() {
		return this.id;
	}

}
