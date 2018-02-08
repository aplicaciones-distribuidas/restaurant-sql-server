package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "facturas")
public class FacturaEntity implements Serializable {
	private static final long serialVersionUID = -798692605392125434L;

	@Id
	@GeneratedValue
	private Long id;

	private Date fecha;
	private float comisionMozo;
	private boolean cobrado;
	private float monto;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idFactura")
	private List<ItemFacturaEntity> itemsFactura;
	@ManyToOne(cascade = CascadeType.ALL)
	private FormaPagoEntity formaPago;

	public FacturaEntity(Long id, Date fecha, float comisionMozo, boolean cobrado, float monto,
						 List<ItemFacturaEntity> itemsFactura, FormaPagoEntity formaPago) {
		this.id = id;
		this.fecha = fecha;
		this.comisionMozo = comisionMozo;
		this.cobrado = cobrado;
		this.monto = monto;
		this.itemsFactura = itemsFactura;
		this.formaPago = formaPago;
	}

	public FacturaEntity() {
	}

	public Long getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public float getComisionMozo() {
		return comisionMozo;
	}

	public boolean getCobrado() {
		return cobrado;
	}

	public float getMonto() {
		return monto;
	}

	public List<ItemFacturaEntity> getItemsFactura() {
		return itemsFactura;
	}

	public FormaPagoEntity getFormaPago() {
		return formaPago;
	}

}
