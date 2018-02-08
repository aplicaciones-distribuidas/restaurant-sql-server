package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.DirectoEntity;
import entities.InsumoProductoEntity;
import entities.ProductoEntity;
import entities.SemiElaboradoEntity;
import excepciones.BaseDeDatosException;
import excepciones.InsumoNoExisteException;
import excepciones.ProductoNoExisteException;
import hibernate.HibernateUtil;
import negocio.Directo;
import negocio.InsumoProducto;
import negocio.Producto;
import negocio.SemiElaborado;

public class ProductoDAO {
	private static ProductoDAO instancia;

	private ProductoDAO() {
	}

	public static ProductoDAO getInstancia() {
		if (instancia == null)
			instancia = new ProductoDAO();
		return instancia;
	}

	public Directo toBusiness(DirectoEntity entity) {
		return new Directo(
				entity.getId(),
				entity.getRubro(),
				entity.getCaducidad(),
				entity.getComisionMozo(),
				entity.getFecha(),
				entity.getPrecio(),
				AreaDAO.getInstancia().toBusiness(entity.getArea()),
				new InsumoProducto(entity.getInsumoProducto().getCantidad(), InsumoDAO.getInstancia().toBusiness(entity.getInsumoProducto().getInsumo()))
		);
	}

	public DirectoEntity toEntity(Directo business) {
		return new DirectoEntity(
				business.getId(),
				business.getRubro(),
				business.getCaducidad(),
				business.getComisionMozo(),
				business.getFecha(),
				business.getPrecio(),
				AreaDAO.getInstancia().toEntity(business.getArea()),
				new InsumoProductoEntity(business.getInsumoProducto().getId(), business.getInsumoProducto().getCantidad(), InsumoDAO.getInstancia().toEntity(business.getInsumoProducto().getInsumo()))
		);
	}

	public SemiElaborado toBusiness(SemiElaboradoEntity entity) {
		List<InsumoProducto> insumos = new ArrayList<>();

		for (InsumoProductoEntity insumo : entity.getInsumosProducto()) {
			insumos.add(
					new InsumoProducto(insumo.getCantidad(), InsumoDAO.getInstancia().toBusiness(insumo.getInsumo()))
			);
		}

		return new SemiElaborado(
				entity.getId(),
				entity.getRubro(),
				entity.getCaducidad(),
				entity.getComisionMozo(),
				entity.getFecha(),
				entity.getPrecio(),
				insumos,
				AreaDAO.getInstancia().toBusiness(entity.getArea())
		);
	}

	public SemiElaboradoEntity toEntity(SemiElaborado business) {

		List<InsumoProductoEntity> insumos = new ArrayList<>();

		for (InsumoProducto insumo : business.getInsumosProducto()) {
			insumos.add(
					new InsumoProductoEntity(insumo.getId(), insumo.getCantidad(), InsumoDAO.getInstancia().toEntity(insumo.getInsumo()))
			);
		}

		return new SemiElaboradoEntity(
				business.getId(),
				business.getRubro(),
				business.getCaducidad(),
				business.getComisionMozo(),
				business.getFecha(),
				business.getPrecio(),
				insumos,
				AreaDAO.getInstancia().toEntity(business.getArea())
		);
	}


	public Long save(Directo directo) throws BaseDeDatosException {
		DirectoEntity entity = this.toEntity(directo);
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

	public Long save(SemiElaborado semiElaborado) throws BaseDeDatosException {
		SemiElaboradoEntity entity = this.toEntity(semiElaborado);
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

	public Producto getById(Long id) throws BaseDeDatosException, ProductoNoExisteException, InsumoNoExisteException {
		ProductoEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (ProductoEntity) session.createQuery("from ProductoEntity p where p.id = :id")
					.setParameter("id", id).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new ProductoNoExisteException();
		}

		try {
			DirectoEntity directoEntity = (DirectoEntity) entity;
			return this.toBusiness(directoEntity);
		} catch (Exception e) {
			SemiElaboradoEntity semiElaboradoEntity = (SemiElaboradoEntity) entity;
			return this.toBusiness(semiElaboradoEntity);
		}
	}

}
