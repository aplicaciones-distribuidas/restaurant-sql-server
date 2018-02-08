package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservas")
public class ReservaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5272137260181488257L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date fecha;
	private int cantPersonas;
	
	
	public ReservaEntity(Date fecha, int cantPersonas) {
		this.fecha = fecha;
		this.cantPersonas = cantPersonas;
	}

	public ReservaEntity() {}

	public Date getFecha() {
		return fecha;
	}


	public int getCantPersonas() {
		return cantPersonas;
	}

}
