package dto;

import java.io.Serializable;

public class FacturaView implements Serializable {
	private static final long serialVersionUID = -7716280088967048996L;

	private float monto;

	public FacturaView(float monto) {
		this.monto = monto;
	}
	
	public float getMonto() {
		return monto;
	}
}
