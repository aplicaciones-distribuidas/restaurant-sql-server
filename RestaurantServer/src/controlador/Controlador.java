package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AreaDAO;
import dao.FormaPagoDAO;
import dao.InsumoDAO;
import dao.MesasDAO;
import dao.MesasOcupacionDAO;
import dao.ProductoDAO;
import dao.SectoresSalonDAO;
import dao.SucursalDAO;
import dto.*;
import excepciones.AreaNoExisteException;
import excepciones.BaseDeDatosException;
import excepciones.EmpleadoNoExisteException;
import excepciones.FormaDePagoNoExisteException;
import excepciones.InsumoNoExisteException;
import excepciones.MesaNoExisteException;
import excepciones.MesaOcupacionNoExisteException;
import excepciones.NoHayMesasDisponiblesException;
import excepciones.ProductoNoExisteException;
import excepciones.ProductoSinStockException;
import excepciones.RubroNoExisteException;
import excepciones.SucursalNoExisteException;
import negocio.Area;
import negocio.Comision;
import negocio.Directo;
import negocio.Empleado;
import negocio.FormaPago;
import negocio.Insumo;
import negocio.InsumoProducto;
import negocio.Mesa;
import negocio.MesaOcupacion;
import negocio.Producto;
import negocio.Proveedor;
import negocio.Rol;
import negocio.SectorSalon;
import negocio.SemiElaborado;
import negocio.Sucursal;

public class Controlador {
	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public List<MesaView> getMesasDisponibles(String nombreSucursal, int cantPersonas) throws SucursalNoExisteException,
			BaseDeDatosException {
		List<MesaView> mesas = new ArrayList<MesaView>();
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		for (Mesa mesa : sucursal.getMesasDisponibles(cantPersonas)) {
			mesas.add(mesa.toView());
		}
		return mesas;
	}

	public List<MesaOcupacionView> getMesasOcupadas(String nombreSucursal) throws SucursalNoExisteException,
			BaseDeDatosException {
		List<MesaOcupacionView> mesasOcupacion = new ArrayList<MesaOcupacionView>();
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		for (MesaOcupacion mesa : sucursal.getMesasOcupadas()) {
			mesasOcupacion.add(mesa.toView());
		}
		return mesasOcupacion;
	}

	public List<SucursalView> getSucursales() throws BaseDeDatosException {
		List<Sucursal> sucursales = SucursalDAO.getInstancia().getAll();
		List<SucursalView> sucursalesView = new ArrayList<>();

		for (Sucursal sucursal : sucursales) {
			sucursalesView.add(new SucursalView(sucursal.getId(), sucursal.getNombre(), sucursal.getUbicacion(), sucursal.getCapacidad()));
		}

		return sucursalesView;
	}

	public List<SectorSalon> getSectoresSalon() throws BaseDeDatosException {
		return SectoresSalonDAO.getInstancia().getAll();
	}

	public List<EmpleadoView> getEmpleadosBySucursal(String nombreSucursal) throws BaseDeDatosException, SucursalNoExisteException {
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		List<EmpleadoView> empleados = new ArrayList<>();
		for (Empleado empleado : sucursal.getEmpleados()) {
			empleados.add(empleado.toView());
		}
		return empleados;
	}

	public List<FormaPagoView> getFormasDePago() throws BaseDeDatosException {
		List<FormaPago> formasDePago = FormaPagoDAO.getInstancia().getAll();
		List<FormaPagoView> formasDePagoView = new ArrayList<>();
		for (FormaPago formaPago : formasDePago) {
			formasDePagoView.add(formaPago.toView());
		}
		return formasDePagoView;
	}

	public List<MesaView> getMesas() throws BaseDeDatosException {
		List<MesaView> mesas = new ArrayList<>();
		for (Mesa m : MesasDAO.getInstancia().getAll()) mesas.add(m.toView());
		return mesas;
	}

	public Mesa buscarMesa(int numero) throws BaseDeDatosException, MesaNoExisteException {
		return MesasDAO.getInstancia().getByNumero(numero);
	}

	public MesaOcupacionView abrirMesa(String nombreSucursal, int cantPersonas, Long idEmpleado) throws NoHayMesasDisponiblesException, BaseDeDatosException, SucursalNoExisteException, EmpleadoNoExisteException {
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		return sucursal.abrirMesa(cantPersonas, idEmpleado).toView();
	}

	public void agregarProductoAMesa(Long idMesaOcupacion, Long idProducto, int cantidadProducto) throws BaseDeDatosException, ProductoNoExisteException, InsumoNoExisteException, MesaOcupacionNoExisteException, ProductoSinStockException {
		Producto producto = ProductoDAO.getInstancia().getById(idProducto);
		MesaOcupacion mesaOcupacion = MesasOcupacionDAO.getInstancia().getById(idMesaOcupacion);
		mesaOcupacion.agregarProducto(producto, cantidadProducto);
		producto.descontarStock();
	}

	public void cerrarMesa(Long idMesaOcupacion, Long idFormaDePago) throws BaseDeDatosException, MesaOcupacionNoExisteException, FormaDePagoNoExisteException {
		FormaPago formaDePago = FormaPagoDAO.getInstancia().getById(idFormaDePago);
		MesaOcupacion mesaOcupacion = MesasOcupacionDAO.getInstancia().getById(idMesaOcupacion);
		mesaOcupacion.cerrar(formaDePago);
	}

	public List<ComisionView> getComisionesMozos(String nombreSucursal) throws BaseDeDatosException, SucursalNoExisteException {
		List<ComisionView> comisionView = new ArrayList<>();
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);

		for (Comision c : sucursal.obtenerComisionesMozos()) {
			comisionView.add(c.toView());
		}

		return comisionView;
	}

	public void crearPlatoDirecto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, Long idInsumo, float cantInsumo) throws AreaNoExisteException, InsumoNoExisteException, RubroNoExisteException, BaseDeDatosException {
		Area area = AreaDAO.getInstancia().getByNombre(nombreArea);
		Insumo insumo = InsumoDAO.getInstancia().getById(idInsumo);

		Directo productoDirecto = new Directo(null, rubro, caducidad, comisionMozo, fecha, precio, area, new InsumoProducto(cantInsumo, insumo));
		productoDirecto.save();
	}

	public void crearPlatoSemielaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, List<InsumoProductoView> insumosView) throws AreaNoExisteException, BaseDeDatosException, InsumoNoExisteException {
		Area area = AreaDAO.getInstancia().getByNombre(nombreArea);
		List<InsumoProducto> insumos = new ArrayList<>();

		for (InsumoProductoView insumo : insumosView) {
			insumos.add(new InsumoProducto(insumo.getCantidad(), InsumoDAO.getInstancia().getById(insumo.getInsumoId())));
		}

		SemiElaborado semiElaborado = new SemiElaborado(null, rubro, caducidad, comisionMozo, fecha, precio, insumos, area);
		semiElaborado.save();
	}

	public void cargarDatos() throws BaseDeDatosException, SucursalNoExisteException, NoHayMesasDisponiblesException, EmpleadoNoExisteException {
		Sucursal sucursal = new Sucursal("Belgrano", "Av. Juramento 1234", 100);
		sucursal.save();

		Sucursal sucursal2 = new Sucursal("Caballito", "Av. Pedro Goyena 432", 85);
		sucursal2.save();

		SectorSalon sA = new SectorSalon("A");
		sA.setSucursal(sucursal);
		sA.save();

		SectorSalon sB = new SectorSalon("B");
		sB.setSucursal(sucursal);
		sB.save();

		SectorSalon sZ = new SectorSalon("Z");
		sZ.setSucursal(sucursal2);
		sZ.save();

		Rol mozo = new Rol(null, "mozo");
		mozo.save();

		List<Comision> comisiones = new ArrayList<>();

		Empleado empleado = new Empleado(null, "José", "Pérez", 10, mozo, comisiones, sA);
		empleado.save();

		Empleado empleado2 = new Empleado(null, "Pepe", "González", 12, mozo, comisiones, sZ);
		empleado2.save();

		Mesa m1 = new Mesa(null, 1, false, 8, sA);
		m1.save();

		Mesa m2 = new Mesa(null, 2, false, 6, sA);
		m2.save();

		Mesa m3 = new Mesa(null, 3, false, 4, sB);
		m3.save();

		Area areaCocina = new Area(null, "cocina");
		areaCocina.save();
		Area areaBarra = new Area(null, "barra");
		areaBarra.save();
		Area areaCafeteria = new Area(null, "cafeteria");
		areaCafeteria.save();

		Proveedor proveedor1 = new Proveedor("proveedor 1", "43028730", "calle falsa 123");

		Insumo insumo1 = new Insumo("insumo 1", "arroz", 10, new Date(), new Date(), proveedor1, 15);
		Insumo insumo2 = new Insumo("insumo 2", "pure", 10, new Date(), new Date(), proveedor1, 15);
		Insumo insumo3 = new Insumo("insumo 3", "papas", 10, new Date(), new Date(), proveedor1, 15);

		InsumoProducto insumoProducto1 = new InsumoProducto(10, insumo1);
		InsumoProducto insumoProducto2 = new InsumoProducto(10, insumo2);
		InsumoProducto insumoProducto3 = new InsumoProducto(10, insumo3);

		Directo directo1 = new Directo(null, "plato", 10, 10, new Date(), 100, areaCocina, insumoProducto1);
		directo1.save();
		Directo directo2 = new Directo(null, "plato", 10, 10, new Date(), 100, areaBarra, insumoProducto2);
		directo2.save();
		Directo directo3 = new Directo(null, "plato", 10, 10, new Date(), 100, areaCafeteria, insumoProducto3);
		directo3.save();

		FormaPago fp = new FormaPago(null, "debito", 1, "santander", 10);
		fp.save();

		FormaPago fp2 = new FormaPago(null, "credito", 1, "itau", 10);
		fp2.save();

		FormaPago fp3 = new FormaPago(null, "credito", 1, "frances", 10);
		fp3.save();

		this.abrirMesa(sucursal.getNombre(), 3, empleado.getId());
	}
}
