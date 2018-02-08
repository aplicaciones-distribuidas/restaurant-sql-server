package dao;

import entities.EmpleadoEntity;
import negocio.Empleado;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.ComisionEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Comision;

public class ComisionDAO {
	private static ComisionDAO instancia;

	private ComisionDAO() {
	}

	public static ComisionDAO getInstancia() {
		if (instancia == null)
			instancia = new ComisionDAO();
		return instancia;
	}

	public Comision toBusiness(ComisionEntity entity) {
		return toBusiness(entity, true);
	}

	public Comision toBusiness(ComisionEntity entity, boolean includeEmpleado) {
		Empleado empleado = null;
		if (includeEmpleado) {
			empleado = EmpleadoDAO.getInstancia().toBusiness(entity.getEmpleado());
		}
		return new Comision(entity.getId(), empleado, entity.getMonto(), entity.getFecha());
	}

	public ComisionEntity toEntity(Comision business) {
		return this.toEntity(business, true);
	}

	public ComisionEntity toEntity(Comision business, boolean includeEmpleado) {
		EmpleadoEntity empleado = null;

		if (includeEmpleado) {
			empleado = EmpleadoDAO.getInstancia().toEntity(business.getEmpleado(), false);
		}

		return new ComisionEntity(business.getId(), empleado, business.getMonto(), business.getFecha());
	}

	public Long save(Comision comision) throws BaseDeDatosException {
		ComisionEntity entity = this.toEntity(comision);
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
