package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.FormaPagoEntity;
import excepciones.BaseDeDatosException;
import excepciones.FormaDePagoNoExisteException;
import hibernate.HibernateUtil;
import negocio.FormaPago;

import java.util.ArrayList;
import java.util.List;

public class FormaPagoDAO {
	private static FormaPagoDAO instancia;

	private FormaPagoDAO() {
	}

	public static FormaPagoDAO getInstancia() {
		if (instancia == null)
			instancia = new FormaPagoDAO();
		return instancia;
	}

	public FormaPago toBusiness(FormaPagoEntity entity) {
		return new FormaPago(entity.getId(), entity.getTipo(), entity.getNumeroCupon(), entity.getBanco(), entity.getMonto());
	}

	public List<FormaPago> toBusiness(List<FormaPagoEntity> entities) {
		List<FormaPago> formasPago = new ArrayList<>();
		for (FormaPagoEntity entity : entities) {
			formasPago.add(this.toBusiness(entity));
		}
		return formasPago;
	}

	public FormaPagoEntity toEntity(FormaPago business) {
		if (business == null) {
			return null;
		}

		return new FormaPagoEntity(business.getId(), business.getTipo(), business.getNumeroCupon(), business.getBanco(), business.getMonto());
	}

	public FormaPago getById(Long id) throws BaseDeDatosException, FormaDePagoNoExisteException {
		FormaPagoEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (FormaPagoEntity) session.createQuery("from FormaPagoEntity f where f.id = :id")
					.setParameter("id", id).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new FormaDePagoNoExisteException();
		}

		return this.toBusiness(entity);
	}

	@SuppressWarnings("unchecked")
	public List<FormaPago> getAll() throws BaseDeDatosException {
		List<FormaPagoEntity> all = new ArrayList<>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from FormaPagoEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}

	public Long save(FormaPago formaDePago) throws BaseDeDatosException {
		FormaPagoEntity entity = this.toEntity(formaDePago);
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
