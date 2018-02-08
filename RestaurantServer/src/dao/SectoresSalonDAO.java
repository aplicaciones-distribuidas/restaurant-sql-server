package dao;

import java.util.ArrayList;
import java.util.List;

import excepciones.MesaNoExisteException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.EmpleadoEntity;
import entities.MesaEntity;
import entities.SectorSalonEntity;
import entities.SucursalEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Empleado;
import negocio.Mesa;
import negocio.SectorSalon;
import negocio.Sucursal;

public class SectoresSalonDAO {
	private static SectoresSalonDAO instancia;

	private SectoresSalonDAO() {
	}

	public static SectoresSalonDAO getInstancia() {
		if (instancia == null)
			instancia = new SectoresSalonDAO();
		return instancia;
	}

	public SectorSalon toBusiness(SectorSalonEntity entity) {
		return this.toBusiness(entity, true);
	}

	public SectorSalon toBusiness(SectorSalonEntity entity, boolean includeSucursal) {
		boolean includeSectorSalon = false;

		if (entity == null) return null;

		Sucursal sucursal = null;
		if (includeSucursal) {
			sucursal = SucursalDAO.getInstancia().toBusiness(entity.getSucursal());
		}

		List<Mesa> mesas = MesasDAO.getInstancia().toBusiness(entity.getMesas(), includeSectorSalon);
		List<Empleado> empleados = new ArrayList<Empleado>();

		for (EmpleadoEntity ee : entity.getEmpleados()) {
			empleados.add(EmpleadoDAO.getInstancia().toBusinessWithoutSectoresSalon(ee));
		}

		return new SectorSalon(entity.getId(), entity.getNombre(), sucursal, mesas, empleados);
	}

	public List<SectorSalon> toBusiness(List<SectorSalonEntity> entities) {
		return this.toBusiness(entities, true);
	}

	public List<SectorSalon> toBusiness(List<SectorSalonEntity> entities, boolean includeSucursal) {
		List<SectorSalon> business = new ArrayList<SectorSalon>();
		for (SectorSalonEntity entity : entities) {
			business.add(this.toBusiness(entity, includeSucursal));
		}
		return business;
	}

	public SectorSalonEntity toEntity(SectorSalon business) {
		if (business == null) return null;
		SucursalEntity sucursal = SucursalDAO.getInstancia().toEntity(business.getSucursal());
		List<MesaEntity> mesas = MesasDAO.getInstancia().toEntity(business.getMesas());
		List<EmpleadoEntity> empleados = new ArrayList<EmpleadoEntity>();

		for (Empleado e : business.getEmpleados()) {
			empleados.add(EmpleadoDAO.getInstancia().toEntity(e));
		}

		return new SectorSalonEntity(business.getId(), business.getNombre(), sucursal, mesas, empleados);
	}

	public List<SectorSalonEntity> toEntity(List<SectorSalon> businesses) {
		List<SectorSalonEntity> entities = new ArrayList<SectorSalonEntity>();
		for (SectorSalon business : businesses) {
			entities.add(this.toEntity(business));
		}
		return entities;
	}

	public Long save(SectorSalon sectorSalon) throws BaseDeDatosException {
		SectorSalonEntity entity = this.toEntity(sectorSalon);
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
	public List<SectorSalon> getAll() throws BaseDeDatosException {
		List<SectorSalonEntity> all = new ArrayList<SectorSalonEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from SectorSalonEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}
}
