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
@Table(name = "pedidos")
public class PedidoEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5286862390801671558L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private boolean realizado;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedidos")
	private List<ComandaEntity> comanda;
	

	public PedidoEntity(boolean realizado, List<ComandaEntity> comanda) {
		this.realizado = realizado;
		this.comanda = comanda;
	}
	
	public PedidoEntity() {}

	public boolean getRealizado() {
		return realizado;
	}

	public List<ComandaEntity> getComanda() {
		return comanda;
	}

}