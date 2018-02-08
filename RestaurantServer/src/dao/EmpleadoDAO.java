package dao;

import java.util.ArrayList;
import java.util.List;

import entities.SectorSalonEntity;
import negocio.SectorSalon;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.ComisionEntity;
import entities.EmpleadoEntity;
import excepciones.BaseDeDatosException;
import excepciones.EmpleadoNoExisteException;
import hibernate.HibernateUtil;
import negocio.Comision;
import negocio.Empleado;

public class EmpleadoDAO {
	private static EmpleadoDAO instancia;

	private EmpleadoDAO() {
	}

	public static EmpleadoDAO getInstancia() {
		if (instancia == null)
			instancia = new EmpleadoDAO();
		return instancia;
	}

	public Empleado toBusiness(EmpleadoEntity entity) {
		List<Comision> comisiones = new ArrayList<>();

		for (ComisionEntity comision : entity.getComisiones()) {
			comisiones.add(ComisionDAO.getInstancia().toBusiness(comision, false));
		}

		return new Empleado(entity.getId(), entity.getNombre(), entity.getApellido(), entity.getPorcentajeComision(), RolDAO.getInstancia().toBusiness(entity.getRol()), comisiones, SectoresSalonDAO.getInstancia().toBusiness(entity.getSectorSalon()));
	}

	public Empleado toBusinessWithoutSectoresSalon(EmpleadoEntity entity) {
		List<Comision> comisiones = new ArrayList<>();

		for (ComisionEntity comision : entity.getComisiones()) {
			comisiones.add(ComisionDAO.getInstancia().toBusiness(comision, false));
		}

		return new Empleado(entity.getId(), entity.getNombre(), entity.getApellido(), entity.getPorcentajeComision(), RolDAO.getInstancia().toBusiness(entity.getRol()), comisiones, null);
	}

	public EmpleadoEntity toEntity(Empleado business) {
		return this.toEntity(business, true);
	}

	public EmpleadoEntity toEntity(Empleado business, boolean includeComision) {
		List<ComisionEntity> comisiones = new ArrayList<>();

		if (includeComision) {
			for (Comision comision : business.getComisiones()) {
				comisiones.add(ComisionDAO.getInstancia().toEntity(comision, false));
			}
		}

		return new EmpleadoEntity(business.getId(), business.getNombre(), business.getApellido(), business.getPorcentajeComision(), RolDAO.getInstancia().toEntity(business.getRol()), comisiones, SectoresSalonDAO.getInstancia().toEntity(business.getSectorSalon()));
	}

	public EmpleadoEntity toEntityWithoutSectoresSalon(Empleado business) {
		List<ComisionEntity> comisiones = new ArrayList<>();

		for (Comision comision : business.getComisiones()) {
			comisiones.add(ComisionDAO.getInstancia().toEntity(comision, false));
		}

		return new EmpleadoEntity(business.getId(), business.getNombre(), business.getApellido(), business.getPorcentajeComision(), RolDAO.getInstancia().toEntity(business.getRol()), comisiones, null);
	}


	public Empleado getById(Long id) throws BaseDeDatosException, EmpleadoNoExisteException {
		EmpleadoEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (EmpleadoEntity) session.createQuery("from EmpleadoEntity s where s.id = :id")
					.setParameter("id", id).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new EmpleadoNoExisteException();
		}

		return this.toBusiness(entity);
	}

	public Empleado getBySectorSalon(SectorSalon sectorSalon) throws BaseDeDatosException, EmpleadoNoExisteException {
		SectorSalonEntity sectorSalonEntity = new SectorSalonEntity();
		sectorSalonEntity.setId(sectorSalon.getId());
		EmpleadoEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (EmpleadoEntity) session.createQuery("from EmpleadoEntity s where s.sectorSalon = :sectorSalon")
					.setParameter("sectorSalon", sectorSalonEntity).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new EmpleadoNoExisteException();
		}

		return this.toBusiness(entity);
	}

	public Long save(Empleado empleado) throws BaseDeDatosException {
		EmpleadoEntity entity = this.toEntity(empleado);
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

	@SuppressWarnings("unchecked")
	public List<Empleado> getAll() throws BaseDeDatosException {
		List<EmpleadoEntity> all = new ArrayList<EmpleadoEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from EmpleadoEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		List<Empleado> empleados = new ArrayList<>();
		for (EmpleadoEntity empleado : all) empleados.add(toBusiness(empleado));
		return empleados;
	}

}
