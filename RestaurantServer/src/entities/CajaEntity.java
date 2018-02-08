package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cajas")
public class CajaEntity implements Serializable {
	private static final long serialVersionUID = -7159958767282139384L;

	@Id
	@GeneratedValue
	private Long id;

	private float monto;
	private Date ultimoCierre;

	public CajaEntity(float monto, Date ultimoCierre) {
		this.monto = monto;
		this.ultimoCierre = ultimoCierre;
	}

	public CajaEntity() {
	}

	public float getMonto() {
		return monto;
	}

	public Date getUltimoCierre() {
		return ultimoCierre;
	}

}
