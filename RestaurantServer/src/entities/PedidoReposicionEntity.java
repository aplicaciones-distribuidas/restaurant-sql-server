package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidosReposicion")
public class PedidoReposicionEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1209736219040137372L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date fecha;
	private String solicitante;
	private String motivo;
	private String estado;
	
	@OneToOne
	private InsumoEntity insumo;
	
	
	public PedidoReposicionEntity(Date fecha, String solicitante, String motivo, String estado, InsumoEntity insumo) {
		this.fecha = fecha;
		this.solicitante = solicitante;
		this.motivo = motivo;
		this.estado = estado;
		this.insumo = insumo;
	}
	
	public PedidoReposicionEntity() {}


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


	public InsumoEntity getInsumo() {
		return insumo;
	}

}
