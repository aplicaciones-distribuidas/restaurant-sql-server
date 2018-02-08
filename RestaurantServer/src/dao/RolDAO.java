package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.RolEntity;
import excepciones.BaseDeDatosException;
import excepciones.RolNoExisteException;
import hibernate.HibernateUtil;
import negocio.Rol;

public class RolDAO {
	private static RolDAO instancia;

	private RolDAO() {
	}

	public static RolDAO getInstancia() {
		if (instancia == null)
			instancia = new RolDAO();
		return instancia;
	}

	public Rol toBusiness(RolEntity entity) {
		return new Rol(entity.getId(), entity.getNombre());
	}

	public RolEntity toEntity(Rol business) {
		return new RolEntity(business.getId(), business.getNombre());
	}

	public Rol getByNombre(String nombre) throws BaseDeDatosException, RolNoExisteException {
		RolEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (RolEntity) session.createQuery("from RolEntity s where s.nombre = :nombre")
					.setParameter("nombre", nombre).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new RolNoExisteException();
		}

		return this.toBusiness(entity);
	}

	public Long save(Rol rol) throws BaseDeDatosException {
		RolEntity entity = this.toEntity(rol);
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

}
