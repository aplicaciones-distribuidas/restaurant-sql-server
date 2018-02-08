package negocio;

import dao.InsumoDAO;
import dao.ProductoDAO;
import excepciones.BaseDeDatosException;
import excepciones.ProductoSinStockException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SemiElaborado extends Producto {

	private List<InsumoProducto> insumosProducto;

	public SemiElaborado(Long id, String rubro, int caducidad, float comisionMozo, Date fecha, float precio,
						 List<InsumoProducto> insumoProducto, Area area) {
		super(id, rubro, caducidad, comisionMozo, fecha, precio, area);
		this.insumosProducto = insumoProducto;
	}

	public List<InsumoProducto> getInsumosProducto() {
		return insumosProducto;
	}


	public List<InsumoProducto> getInsumosFaltantes() {
		List<InsumoProducto> resultado = new ArrayList<>();

		for (InsumoProducto ip : this.getInsumosProducto()) if (ip.getCantidad() == 0) resultado.add(ip);

		return resultado;
	}

	public void actualizarStockInsumo(String nombre, float cantidad) {
		for (InsumoProducto ip : this.getInsumosProducto()) {
			if (ip.getInsumo().getNombre().equals(nombre)) {
				ip.setCantidad(cantidad);
				break;
			}
		}
	}

	@Override
	public void save() throws BaseDeDatosException {
		this.setId(ProductoDAO.getInstancia().save(this));
	}

	@Override
	public void descontarStock() throws BaseDeDatosException, ProductoSinStockException {
		boolean tieneStock = true;

		for (InsumoProducto ip : this.getInsumosProducto()) {
			if (ip.getInsumo().getCantidad() < ip.getCantidad()) {
				tieneStock = false;
				break;
			}
		}

		if (!tieneStock) throw new ProductoSinStockException();

		// si llega aca es porque todos los insumos del semielaborado tienen stock suficiente, entonces descuento stock y actualizo insumos
		for (InsumoProducto ip : this.getInsumosProducto()) {
			ip.getInsumo().setCantidad(ip.getInsumo().getCantidad() - ip.getCantidad());
			InsumoDAO.getInstancia().update(ip.getInsumo());
		}
	}
}
