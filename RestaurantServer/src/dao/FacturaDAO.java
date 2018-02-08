package dao;

import java.util.ArrayList;
import java.util.List;

import entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Directo;
import negocio.Factura;
import negocio.ItemFactura;
import negocio.SemiElaborado;

public class FacturaDAO {
	private static FacturaDAO instancia;

	private FacturaDAO() {
	}

	public static FacturaDAO getInstancia() {
		if (instancia == null)
			instancia = new FacturaDAO();
		return instancia;
	}

	public Factura toBusiness(FacturaEntity entity) {
		List<ItemFactura> itemsFactura = new ArrayList<>();
		for (ItemFacturaEntity ife : entity.getItemsFactura()) {
			Directo directo = null;
			SemiElaborado semiElaborado = null;
			try {
				directo = ProductoDAO.getInstancia().toBusiness((DirectoEntity) ife.getProducto());
			} catch (Exception e) {
				semiElaborado = ProductoDAO.getInstancia().toBusiness((SemiElaboradoEntity) ife.getProducto());
			}

			ItemFactura itemFactura = new ItemFactura(directo != null ? directo : semiElaborado, ife.getCantidad(), ife.getMonto());
			itemsFactura.add(itemFactura);
		}
		return new Factura(entity.getId(), entity.getFecha(), entity.getComisionMozo(), entity.getCobrado(), entity.getMonto(), itemsFactura, entity.getFormaPago() == null ? null : FormaPagoDAO.getInstancia().toBusiness(entity.getFormaPago()));
	}

	public FacturaEntity toEntity(Factura business) {
		if (business == null) {
			return null;
		}

		List<ItemFacturaEntity> itemsFactura = new ArrayList<>();
		if (business.getItemsFactura() != null) {
			for (ItemFactura ife : business.getItemsFactura()) {
				DirectoEntity directo = null;
				SemiElaboradoEntity semiElaborado = null;
				try {
					directo = ProductoDAO.getInstancia().toEntity((Directo) ife.getProducto());
				} catch (Exception e) {
					semiElaborado = ProductoDAO.getInstancia().toEntity((SemiElaborado) ife.getProducto());
				}

				ItemFacturaEntity itemFactura = new ItemFacturaEntity(directo != null ? directo : semiElaborado, ife.getCantidad(), ife.getMonto());
				itemsFactura.add(itemFactura);
			}
		}

		FormaPagoEntity formaDePago = FormaPagoDAO.getInstancia().toEntity(business.getFormaPago());
		return new FacturaEntity(business.getId(), business.getFecha(), business.getComisionMozo(), business.isCobrado(), business.getMonto(), itemsFactura, formaDePago);
	}

	public Long save(Factura factura) throws BaseDeDatosException {
		FacturaEntity entity = this.toEntity(factura);
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return entity.getId();
	}

	public void update(Factura factura) throws BaseDeDatosException {
		FacturaEntity entity = this.toEntity(factura);
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
	}

}
