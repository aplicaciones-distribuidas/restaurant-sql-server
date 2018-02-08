package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.AreaEntity;
import entities.AreaEnumEntity;
import excepciones.AreaNoExisteException;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Area;
import negocio.AreaEnum;

public class AreaDAO {
	private static AreaDAO instancia;

	private AreaDAO() {
	}

	public static AreaDAO getInstancia() {
		if (instancia == null)
			instancia = new AreaDAO();
		return instancia;
	}

	public Area toBusiness(AreaEntity entity) {
		return new Area(entity.getId(), entity.getNombre());
	}

	public AreaEntity toEntity(Area business) {
		return new AreaEntity(business.getId(), business.getNombre());
	}

	public Area getByNombre(String nombre) throws BaseDeDatosException, AreaNoExisteException {
		AreaEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (AreaEntity) session.createQuery("from AreaEntity s where s.nombre = :nombre")
					.setParameter("nombre", nombre).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new AreaNoExisteException();
		}

		return this.toBusiness(entity);
	}

	public Long save(Area area) throws BaseDeDatosException {
		AreaEntity entity = this.toEntity(area);
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
