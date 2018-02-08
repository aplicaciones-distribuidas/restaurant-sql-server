package negocio;

import dao.InsumoDAO;
import dao.ProductoDAO;
import excepciones.BaseDeDatosException;
import excepciones.ProductoSinStockException;

import java.util.Date;

public class Directo extends Producto {

	private InsumoProducto insumoProducto;

	public Directo(Long id, String rubro, int caducidad, float comisionMozo, Date fecha, float precio, Area area, InsumoProducto insumoProducto) {
		super(id, rubro, caducidad, comisionMozo, fecha, precio, area);
		this.insumoProducto = insumoProducto;
	}

	public InsumoProducto getInsumoProducto() {
		return insumoProducto;
	}

	@Override
	public void save() throws BaseDeDatosException {
		this.setId(ProductoDAO.getInstancia().save(this));
	}

	@Override
	public void descontarStock() throws BaseDeDatosException, ProductoSinStockException {
		if (this.getInsumoProducto().getInsumo().getCantidad() >= this.getInsumoProducto().getCantidad()) { // hay stock
			// descuento stock y actualizo insumo
			this.getInsumoProducto().getInsumo().setCantidad(this.getInsumoProducto().getInsumo().getCantidad() - this.getInsumoProducto().getCantidad());
			InsumoDAO.getInstancia().update(this.getInsumoProducto().getInsumo());
		} else { // no hay stock
			throw new ProductoSinStockException();
		}
	}
}
