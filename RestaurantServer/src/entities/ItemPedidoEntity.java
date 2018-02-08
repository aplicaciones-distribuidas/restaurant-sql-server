package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemsPedido")
public class ItemPedidoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3495192063902366798L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int cantidad;
	@OneToOne
	private ItemCartaEntity item;


	public ItemPedidoEntity(int cantidad, ItemCartaEntity item) {
		this.cantidad = cantidad;
		this.item = item;
	}
	
	public ItemPedidoEntity() {}

	public int getCantidad() {
		return cantidad;
	}

	public ItemCartaEntity getItem() {
		return item;
	}
	
}
