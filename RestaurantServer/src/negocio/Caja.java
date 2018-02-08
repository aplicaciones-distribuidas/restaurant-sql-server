package negocio;

import java.util.Date;

public class Caja {

	private float monto;
	private Date ultimoCierre;

	public Caja(float monto, Date ultimoCierre) {
		super();
		this.monto = monto;
		this.ultimoCierre = ultimoCierre;
	}

	public float getMonto() {
		return monto;
	}

	public Date getUltimoCierre() {
		return ultimoCierre;
	}

}
