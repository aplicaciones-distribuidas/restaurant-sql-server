package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formasDePago")
public class FormaPagoEntity implements Serializable {
	private static final long serialVersionUID = -5719547576076336171L;

	@Id
	@GeneratedValue
	private Long id;

	private String tipo;
	private int numeroCupon;
	private String banco;
	private float monto;

	public FormaPagoEntity(Long id, String tipo, int numeroCupon, String banco, float monto) {
		this.id = id;
		this.tipo = tipo;
		this.numeroCupon = numeroCupon;
		this.banco = banco;
		this.monto = monto;
	}

	public FormaPagoEntity() {
	}

	public Long getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}


	public int getNumeroCupon() {
		return numeroCupon;
	}


	public String getBanco() {
		return banco;
	}


	public float getMonto() {
		return monto;
	}

}
