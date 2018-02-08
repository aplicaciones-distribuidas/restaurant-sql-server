package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.SucursalEntity;
import entities.TareaEntity;
import entities.AreaEntity;
import entities.CajaEntity;
import entities.CartaEntity;
import entities.DepositoEntity;
import entities.PedidoEntity;
import entities.PedidoReposicionEntity;
import entities.ReservaEntity;
import entities.SectorSalonEntity;
import excepciones.BaseDeDatosException;
import excepciones.SucursalNoExisteException;
import hibernate.HibernateUtil;
import negocio.Sucursal;
import negocio.Tarea;
import negocio.Area;
import negocio.Caja;
import negocio.Carta;
import negocio.Deposito;
import negocio.Pedido;
import negocio.PedidoReposicion;
import negocio.Reserva;
import negocio.SectorSalon;

public class SucursalDAO {
	private static SucursalDAO instancia;

	private SucursalDAO() {
	}

	public static SucursalDAO getInstancia() {
		if (instancia == null)
			instancia = new SucursalDAO();
		return instancia;
	}

	public Sucursal toBusiness(SucursalEntity entity) {
		List<Carta> cartas = new ArrayList<Carta>(); // TODO: translate entity.getCartas() to business
		Caja caja = null; // TODO: translate entity.getCaja() to business
		List<Area> areas = new ArrayList<Area>(); // TODO: translate entity.getArea() to business
		List<Pedido> pedidos = new ArrayList<Pedido>(); // TODO: translate entity.getPedidos() to business
		List<Reserva> reservas = new ArrayList<Reserva>(); // TODO: translate entity.getReservas() to business
		List<Tarea> tareas = new ArrayList<Tarea>(); // TODO: translate entity.getTareas() to business
		List<SectorSalon> sectores = SectoresSalonDAO.getInstancia().toBusiness(entity.getSectores(), false);
		List<PedidoReposicion> pedidosReposicion = new ArrayList<PedidoReposicion>(); // TODO: translate entity.getPedidosReposicion() to business
		Deposito deposito = null; // TODO: translate entity.getDeposito() to business
		return new Sucursal(entity.getId(), entity.getNombre(), entity.getUbicacion(), entity.getCapacidad(), cartas,
				caja, areas, pedidos, reservas, tareas, sectores, pedidosReposicion, deposito);
	}

	public List<Sucursal> toBusiness(List<SucursalEntity> entities) {
		List<Sucursal> business = new ArrayList<Sucursal>();
		for (SucursalEntity entity : entities) {
			business.add(this.toBusiness(entity));
		}
		return business;
	}

	public SucursalEntity toEntity(Sucursal business) {
		List<CartaEntity> cartas = new ArrayList<CartaEntity>(); // TODO: translate business.getCartas() to entity
		CajaEntity caja = null; // TODO: translate business.getCaja() to entity
		List<AreaEntity> areas = new ArrayList<AreaEntity>(); // TODO: translate business.getArea() to entity
		List<PedidoEntity> pedidos = new ArrayList<PedidoEntity>(); // TODO: translate business.getPedidos() to entity
		List<ReservaEntity> reservas = new ArrayList<ReservaEntity>(); // TODO: translate business.getReservas() to entity
		List<TareaEntity> tareas = new ArrayList<TareaEntity>(); // TODO: translate business.getTareas() to entity
		List<SectorSalonEntity> sectores = business.getSectores() != null || business.getSectores().isEmpty() ? new ArrayList<>() : SectoresSalonDAO.getInstancia().toEntity(business.getSectores());
		List<PedidoReposicionEntity> pedidosReposicion = new ArrayList<PedidoReposicionEntity>(); // TODO: translate business.getPedidosReposicion() to entity
		DepositoEntity deposito = null;// TODO: translate business.getDeposito() to entity

		return new SucursalEntity(business.getId(), business.getNombre(), business.getUbicacion(), business
				.getCapacidad(), cartas, caja, areas, pedidos, reservas, tareas, sectores, pedidosReposicion, deposito);
	}

	public List<SucursalEntity> toEntity(List<Sucursal> businesses) {
		List<SucursalEntity> entities = new ArrayList<SucursalEntity>();
		for (Sucursal business : businesses) {
			entities.add(this.toEntity(business));
		}
		return entities;
	}

	@SuppressWarnings("unchecked")
	public List<Sucursal> getAll() throws BaseDeDatosException {
		List<SucursalEntity> all = new ArrayList<SucursalEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from SucursalEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}

	public Sucursal getByNombre(String nombre) throws BaseDeDatosException, SucursalNoExisteException {
		SucursalEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (SucursalEntity) session.createQuery("from SucursalEntity s where s.nombre = :nombre")
					.setParameter("nombre", nombre).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new SucursalNoExisteException(nombre);
		}

		return this.toBusiness(entity);
	}

	public Long save(Sucursal sucursal) throws BaseDeDatosException {
		SucursalEntity entity = this.toEntity(sucursal);
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
