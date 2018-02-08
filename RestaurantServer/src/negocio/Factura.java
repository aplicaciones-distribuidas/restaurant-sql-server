package negocio;

import dao.FacturaDAO;
import excepciones.BaseDeDatosException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
	private Long id;
	private Date fecha;
	private float comisionMozo;
	private boolean cobrado;
	private float monto;
	private List<ItemFactura> itemsFactura;
	private FormaPago formaPago;

	public Factura(Long id, Date fecha, float comisionMozo, boolean cobrado, float monto, List<ItemFactura> itemsFactura, FormaPago formaPago) {
		this.id = id;
		this.fecha = fecha;
		this.comisionMozo = comisionMozo;
		this.cobrado = cobrado;
		this.monto = monto;
		this.itemsFactura = itemsFactura;
		this.formaPago = formaPago;
	}

	public Factura() {
		this.fecha = new Date();
		this.comisionMozo = 0;
		this.cobrado = false;
		this.monto = 0;
		this.itemsFactura = new ArrayList<>();
		this.formaPago = null;
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

	public boolean isCobrado() {
		return cobrado;
	}

	public float getMonto() {
		return monto;
	}

	public List<ItemFactura> getItemsFactura() {
		return itemsFactura;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setComisionMozo(float comisionMozo) {
		this.comisionMozo = comisionMozo;
	}

	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public void setItemsFactura(List<ItemFactura> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public void agregarItem(Producto producto, int cantidadProducto) {
		this.itemsFactura.add(new ItemFactura(producto, cantidadProducto, producto.getPrecio()));
		this.comisionMozo += producto.getComisionMozo() * cantidadProducto;
		this.monto += producto.getPrecio() * cantidadProducto;
	}

	public void cobrar(FormaPago formaDePago, Empleado empleado) throws BaseDeDatosException {
		this.setCobrado(true);
		this.setFormaPago(formaDePago);
		this.setFecha(new Date());
		this.update();

		Comision comision = new Comision(empleado, this.calcularComisionMozo(empleado));
		comision.save();
	}

	private float calcularComisionMozo(Empleado empleado) {
		return this.comisionMozo + (this.monto * empleado.getPorcentajeComision() / 100);
	}

	public void save() throws BaseDeDatosException {
		this.id = FacturaDAO.getInstancia().save(this);
	}

	public void update() throws BaseDeDatosException {
		FacturaDAO.getInstancia().update(this);
	}

}
