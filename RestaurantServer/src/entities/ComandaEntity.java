package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "comandas")
public class ComandaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4210998493538755099L;

	@Id
	@GeneratedValue
	private Long id;
	
	private boolean paraFacturar;
	private boolean realizada;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idComanda")
	private List<ItemPedidoEntity> itemsPedido;

	public ComandaEntity(boolean paraFacturar, boolean realizada, List<ItemPedidoEntity> itemsPedido) {
		this.paraFacturar = paraFacturar;
		this.realizada = realizada;
		this.itemsPedido = itemsPedido;
	}
	
	public ComandaEntity() {}

	public boolean isParaFacturar() {
		return paraFacturar;
	}

	public boolean isRealizada() {
		return realizada;
	}

	public List<ItemPedidoEntity> getItemsPedido() {
		return itemsPedido;
	}

}
