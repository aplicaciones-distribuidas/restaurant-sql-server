package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.MesaEntity;
import entities.ProveedorEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Mesa;
import negocio.Proveedor;

public class ProveedorDAO {
	private static ProveedorDAO instancia;

	private ProveedorDAO() {
	}

	public static ProveedorDAO getInstancia() {
		if (instancia == null)
			instancia = new ProveedorDAO();
		return instancia;
	}

	public Proveedor toBusiness(ProveedorEntity entity) {
		return new Proveedor(entity.getId(), entity.getNombre(), entity.getTelefono(), entity.getDireccion());
	}

	public ProveedorEntity toEntity(Proveedor business) {
		return new ProveedorEntity(business.getId(), business.getNombre(), business.getTelefono(), business.getDireccion());
	}
	
	public Long save(Proveedor proveedor) throws BaseDeDatosException {
		ProveedorEntity entity = this.toEntity(proveedor);
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
	public List<Proveedor> getAll() throws BaseDeDatosException {
		List<ProveedorEntity> all = new ArrayList<ProveedorEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from ProveedorEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		List<Proveedor> proveedores = new ArrayList<>();
		for (ProveedorEntity pe : all) proveedores.add(toBusiness(pe));
		return proveedores;
	}

}
