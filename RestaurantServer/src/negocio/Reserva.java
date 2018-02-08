package negocio;

import java.util.Date;

public class Reserva {
	
	private Date fecha;
	private int cantPersonas;
	
	
	public Reserva(Date fecha, int cantPersonas) {
		super();
		this.fecha = fecha;
		this.cantPersonas = cantPersonas;
	}


	public Date getFecha() {
		return fecha;
	}


	public int getCantPersonas() {
		return cantPersonas;
	}

}
