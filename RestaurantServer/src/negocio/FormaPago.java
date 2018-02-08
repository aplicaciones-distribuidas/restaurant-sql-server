package negocio;

import dao.FormaPagoDAO;
import dto.FormaPagoView;
import excepciones.BaseDeDatosException;

public class FormaPago {
	private Long id;
	private String tipo;
	private int numeroCupon;
	private String banco;
	private float monto;

	public FormaPago(Long id, String tipo, int numeroCupon, String banco, float monto) {
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

	public void save() throws BaseDeDatosException {
		this.id = FormaPagoDAO.getInstancia().save(this);
	}

	public FormaPagoView toView() {
		return new FormaPagoView(this.id, this.tipo, this.numeroCupon, this.banco, this.monto);
	}

}
