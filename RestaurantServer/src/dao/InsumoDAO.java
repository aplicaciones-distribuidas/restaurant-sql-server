package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.InsumoEntity;
import excepciones.BaseDeDatosException;
import excepciones.InsumoNoExisteException;
import hibernate.HibernateUtil;
import negocio.Insumo;

public class InsumoDAO {
	private static InsumoDAO instancia;

	private InsumoDAO() {
	}

	public static InsumoDAO getInstancia() {
		if (instancia == null)
			instancia = new InsumoDAO();
		return instancia;
	}

	public Insumo toBusiness(InsumoEntity entity) {
		return new Insumo(entity.getId(), entity.getClasificacion(), entity.getNombre(), entity.getCantidadMinima(), entity.getFechaVencimiento(), entity.getFechaCompra(), ProveedorDAO.getInstancia().toBusiness(entity.getProveedor()), entity.getCantidad());
	}

	public InsumoEntity toEntity(Insumo business) {
		return new InsumoEntity(business.getId(), business.getClasificacion(), business.getNombre(), business.getCantidadMinima(), business.getFechaVencimiento(), business.getFechaCompra(), ProveedorDAO.getInstancia().toEntity(business.getProveedor()), business.getCantidad());
	}

	public Insumo getById(Long id) throws BaseDeDatosException, InsumoNoExisteException {
		InsumoEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (InsumoEntity) session.createQuery("from InsumoEntity s where s.id = :id")
					.setParameter("id", id).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new InsumoNoExisteException();
		}

		return this.toBusiness(entity);
	}

	public void save(Insumo insumo) throws BaseDeDatosException {
		InsumoEntity entity = this.toEntity(insumo);
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

	public void update(Insumo insumo) throws BaseDeDatosException {
		InsumoEntity entity = this.toEntity(insumo);
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
