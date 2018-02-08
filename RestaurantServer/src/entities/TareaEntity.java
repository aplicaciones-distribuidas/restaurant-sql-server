package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tareas")
public class TareaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9212805404355377186L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int cantidad;
	private boolean realizada;
	private Date fecha;
	@OneToOne
	private ProductoEntity producto;
	
	
	public TareaEntity(int cantidad, boolean realizada, Date fecha, ProductoEntity producto) {
		this.cantidad = cantidad;
		this.realizada = realizada;
		this.fecha = fecha;
		this.producto = producto;
	}
	
	public TareaEntity() {}

	
	public int getCantidad() {
		return cantidad;
	}


	public boolean getRealizada() {
		return realizada;
	}


	public Date getFecha() {
		return fecha;
	}


	public ProductoEntity getProducto() {
		return producto;
	}

}
