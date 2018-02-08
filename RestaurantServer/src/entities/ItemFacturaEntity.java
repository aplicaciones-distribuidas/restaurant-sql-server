package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemsFactura")
public class ItemFacturaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8185023290187465790L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private ProductoEntity producto;
	private int cantidad;
	private float monto;
	
	
	public ItemFacturaEntity(ProductoEntity producto, int cantidad, float monto) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.monto = monto;
	}
	
	public ItemFacturaEntity() {}


	public ProductoEntity getProducto() {
		return producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public float getMonto() {
		return monto;
	}

}
