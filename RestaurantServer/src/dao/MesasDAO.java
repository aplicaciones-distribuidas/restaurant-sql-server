package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.MesaEntity;
import entities.SectorSalonEntity;
import excepciones.MesaNoExisteException;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Mesa;
import negocio.SectorSalon;

public class MesasDAO {
	private static MesasDAO instancia;

	private MesasDAO() {
	}

	public static MesasDAO getInstancia() {
		if (instancia == null)
			instancia = new MesasDAO();
		return instancia;
	}

	public Mesa toBusiness(MesaEntity entity) {
		return toBusiness(entity, true);
	}

	public Mesa toBusiness(MesaEntity entity, boolean includeSectorSalon) {
		SectorSalon sectorSalon = null;
		if (includeSectorSalon)
			sectorSalon = SectoresSalonDAO.getInstancia().toBusiness(entity.getSectorSalon());
		return new Mesa(entity.getId(), entity.getNumero(), entity.isOcupada(), entity.getCapacidad(), sectorSalon);
	}

	public List<Mesa> toBusiness(List<MesaEntity> entities) {
		return this.toBusiness(entities, true);
	}

	public List<Mesa> toBusiness(List<MesaEntity> entities, boolean includeSectorSalon) {
		List<Mesa> business = new ArrayList<Mesa>();
		for (MesaEntity entity : entities) {
			business.add(this.toBusiness(entity, includeSectorSalon));
		}
		return business;
	}

	public MesaEntity toEntity(Mesa business) {
		SectorSalonEntity sectorSalon = SectoresSalonDAO.getInstancia().toEntity(business.getSectorSalon());
		return new MesaEntity(business.getId(), business.getNumero(), business.isOcupada(), business.getCapacidad(),
				sectorSalon);
	}

	public MesaEntity toEntityWithoutMesaSector(Mesa business) {
		SectorSalonEntity sectorSalon = null;
		return new MesaEntity(business.getId(), business.getNumero(), business.isOcupada(), business.getCapacidad(),
				sectorSalon);
	}

	public List<MesaEntity> toEntity(List<Mesa> businesses) {
		List<MesaEntity> entities = new ArrayList<MesaEntity>();
		for (Mesa business : businesses) {
			entities.add(this.toEntity(business));
		}
		return entities;
	}

	public List<MesaEntity> toEntityWithoutMesaSector(List<Mesa> businesses) {
		List<MesaEntity> entities = new ArrayList<MesaEntity>();
		for (Mesa business : businesses) {
			entities.add(this.toEntityWithoutMesaSector(business));
		}
		return entities;
	}

	@SuppressWarnings("unchecked")
	public List<Mesa> getAll() throws BaseDeDatosException {
		List<MesaEntity> all = new ArrayList<MesaEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from MesaEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}

	public Mesa getByNumero(int numero) throws BaseDeDatosException, MesaNoExisteException {
		MesaEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (MesaEntity) session.createQuery("from MesaEntity m where m.numero = :numero").setParameter(
					"numero", numero).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new MesaNoExisteException(numero);
		}

		return this.toBusiness(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Mesa> getDisponiblesBySectorSalon(SectorSalon sectorSalon) throws BaseDeDatosException {
		SectorSalonEntity sectorSalonEntity = new SectorSalonEntity(sectorSalon.getId());

		List<MesaEntity> all = new ArrayList<MesaEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from MesaEntity m where m.sectorSalon = :sectorSalon and m.ocupada = false")
					.setParameter("sectorSalon", sectorSalonEntity).list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}

	public Long save(Mesa mesa) throws BaseDeDatosException {
		MesaEntity entity = this.toEntity(mesa);
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

	public Long update(Mesa mesa) throws BaseDeDatosException {
		MesaEntity entity = this.toEntity(mesa);
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return entity.getId();
	}

}
