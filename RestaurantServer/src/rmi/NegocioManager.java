package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import controlador.Controlador;
import dto.*;
import excepciones.AreaNoExisteException;
import excepciones.BaseDeDatosException;
import excepciones.EmpleadoNoExisteException;
import excepciones.FormaDePagoNoExisteException;
import excepciones.InsumoNoExisteException;
import excepciones.MesaOcupacionNoExisteException;
import excepciones.NoHayMesasDisponiblesException;
import excepciones.ProductoNoExisteException;
import excepciones.ProductoSinStockException;
import excepciones.RubroNoExisteException;
import excepciones.SucursalNoExisteException;
import excepciones.TareaNoExisteException;
import interfaces.NegocioTDA;

public class NegocioManager extends UnicastRemoteObject implements NegocioTDA, Serializable {
	public NegocioManager() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 3548218673814294625L;

	@Override
	public int stockPlatoBebida() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void centralizacionCompras() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void centralizacionDistribucion() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void asignarHorasTrabajo(int horas, Long trabajoId) throws RemoteException, TareaNoExisteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reservar(String sucursal, int cantPersonas, Date fecha) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MesaView> mesasDisponibles(String sucursal, int cantPersonas) throws RemoteException,
			SucursalNoExisteException, BaseDeDatosException {
		return Controlador.getInstancia().getMesasDisponibles(sucursal, cantPersonas);
	}

	@Override
	public List<MesaOcupacionView> mesasOcupadas(String sucursal) throws RemoteException, SucursalNoExisteException,
			BaseDeDatosException {
		return Controlador.getInstancia().getMesasOcupadas(sucursal);
	}

	@Override
	public void asignarEmpleadoSectorSucursal(Long idEmpleado, String sucursal, int numero) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public EmpleadoView buscarEmpleado(String nombre, String apellido, String rol) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MesaOcupacionView abrirMesa(String nombreSucursal, int cantPersonas, Long idEmpleado) throws RemoteException, NoHayMesasDisponiblesException, BaseDeDatosException, SucursalNoExisteException, EmpleadoNoExisteException {
		return Controlador.getInstancia().abrirMesa(nombreSucursal, cantPersonas, idEmpleado);
	}

	@Override
	public void agregarProductoAMesa(Long idMesaOcupacion, Long idProducto, int cantidadProducto) throws RemoteException, BaseDeDatosException, ProductoNoExisteException, InsumoNoExisteException, MesaOcupacionNoExisteException, ProductoSinStockException {
		Controlador.getInstancia().agregarProductoAMesa(idMesaOcupacion, idProducto, cantidadProducto);
	}

	@Override
	public void lanzarPedido(Long idMesaOcupacion) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void realizarReclamo(Long idMesaOcupacion, String reclamo) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void marcarComandaRealizada(int nroPedido) throws RemoteException {
		// TODO Auto-generated method stub

	}


	@Override
	public PedidoReposicionView buscarPedidoReposicion() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoView buscarPedido() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReporteView generarReporteReposicionInsumos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verificarStock() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void generarOrdenCompra() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarStock(Long idProducto, int cantidad) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cerrarMesa(Long idMesaOcupacion, Long idFormaDePago) throws RemoteException, BaseDeDatosException, MesaOcupacionNoExisteException, FormaDePagoNoExisteException {
		Controlador.getInstancia().cerrarMesa(idMesaOcupacion, idFormaDePago);
	}

	@Override
	public List<ComisionView> getComisionesMozos(String nombreSucursal) throws RemoteException, BaseDeDatosException, SucursalNoExisteException {
		return Controlador.getInstancia().getComisionesMozos(nombreSucursal);
	}

	@Override
	public void crearPlatoDirecto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, Long idInsumo, float cantInsumo) throws RemoteException, AreaNoExisteException, InsumoNoExisteException, RubroNoExisteException, BaseDeDatosException {
		Controlador.getInstancia().crearPlatoDirecto(rubro, caducidad, comisionMozo, fecha, precio, nombreArea, idInsumo, cantInsumo);
	}

	@Override
	public void crearPlatoSemielaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, List<InsumoProductoView> insumosView) throws RemoteException, AreaNoExisteException, BaseDeDatosException, InsumoNoExisteException {
		Controlador.getInstancia().crearPlatoSemielaborado(rubro, caducidad, comisionMozo, fecha, precio, nombreArea, insumosView);
	}

	@Override
	public List<SucursalView> getSucursales() throws RemoteException, BaseDeDatosException {
		return Controlador.getInstancia().getSucursales();
	}

	@Override
	public List<EmpleadoView> getEmpleadosBySucursal(String nombreSucursal) throws RemoteException, BaseDeDatosException, SucursalNoExisteException {
		return Controlador.getInstancia().getEmpleadosBySucursal(nombreSucursal);
	}

	@Override
	public List<FormaPagoView> getFormasDePago() throws RemoteException, BaseDeDatosException {
		return Controlador.getInstancia().getFormasDePago();
	}
}
