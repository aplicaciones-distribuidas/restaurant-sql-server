package negocio;

import java.util.Date;

public class PedidoReposicion {
	
	private Date fecha;
	private String solicitante;
	private String motivo;
	private String estado;
	private Insumo insumo;
	
	
	public PedidoReposicion(Date fecha, String solicitante, String motivo, String estado, Insumo insumo) {
		super();
		this.fecha = fecha;
		this.solicitante = solicitante;
		this.motivo = motivo;
		this.estado = estado;
		this.insumo = insumo;
	}


	public Date getFecha() {
		return fecha;
	}


	public String getSolicitante() {
		return solicitante;
	}


	public String getMotivo() {
		return motivo;
	}


	public String getEstado() {
		return estado;
	}


	public Insumo getInsumo() {
		return insumo;
	}

}
