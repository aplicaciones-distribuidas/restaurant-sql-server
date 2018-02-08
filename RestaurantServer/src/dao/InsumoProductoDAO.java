package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.InsumoProductoEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.InsumoProducto;

public class InsumoProductoDAO {
	private static InsumoProductoDAO instancia;

	private InsumoProductoDAO() {
	}

	public static InsumoProductoDAO getInstancia() {
		if (instancia == null)
			instancia = new InsumoProductoDAO();
		return instancia;
	}

	public InsumoProducto toBusiness(InsumoProductoEntity entity) {
		return new InsumoProducto(entity.getId(), entity.getCantidad(), InsumoDAO.getInstancia().toBusiness(entity.getInsumo()));
	}

	public InsumoProductoEntity toEntity(InsumoProducto business) {
		return new InsumoProductoEntity(business.getId(), business.getCantidad(), InsumoDAO.getInstancia().toEntity(business.getInsumo()));
	}

	public void save(InsumoProducto ip) throws BaseDeDatosException {
		InsumoProductoEntity entity = this.toEntity(ip);
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
	}

}
