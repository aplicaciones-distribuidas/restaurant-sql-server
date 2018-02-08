package dto;

import java.io.Serializable;

public class FormaPagoView implements Serializable {
	private static final long serialVersionUID = 3784504762649506411L;

	private Long id;
	private String tipo;
	private int numeroCupon;
	private String banco;
	private float monto;

	public FormaPagoView(Long id, String tipo, int numeroCupon, String banco, float monto) {
		this.id = id;
		this.tipo = tipo;
		this.numeroCupon = numeroCupon;
		this.banco = banco;
		this.monto = monto;
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
