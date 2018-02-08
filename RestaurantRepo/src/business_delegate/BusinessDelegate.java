package business_delegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import dto.*;
import excepciones.*;
import interfaces.NegocioTDA;

public class BusinessDelegate {
	private static BusinessDelegate instancia;

	NegocioTDA remoteObject;

	private BusinessDelegate() throws ConexionException {
		try {
			remoteObject = (NegocioTDA) Naming.lookup("//localhost:1098/restaurant");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public static BusinessDelegate getInstancia() throws ConexionException {
		if (instancia == null)
			instancia = new BusinessDelegate();
		return instancia;
	}

	public List<SucursalView> getSucursales() throws ConexionException, BaseDeDatosException {
		try {
			return remoteObject.getSucursales();
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public List<MesaView> mesasDisponibles(String sucursal, int cantPersonas) throws ConexionException,
			SucursalNoExisteException, BaseDeDatosException {
		try {
			return remoteObject.mesasDisponibles(sucursal, cantPersonas);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public List<MesaOcupacionView> mesasOcupadas(String sucursal) throws ConexionException, SucursalNoExisteException,
			BaseDeDatosException {
		try {
			return remoteObject.mesasOcupadas(sucursal);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public MesaOcupacionView abrirMesa(String sucursal, int cantPersonas, Long idEmpleado) throws ConexionException, BaseDeDatosException, NoHayMesasDisponiblesException, SucursalNoExisteException, EmpleadoNoExisteException {
		try {
			return remoteObject.abrirMesa(sucursal, cantPersonas, idEmpleado);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public void reservarMesa(String sucursal, int cantPersonas, Date fecha) throws ConexionException,
			NoHayMesasDisponiblesException, BaseDeDatosException {
		try {
			remoteObject.reservar(sucursal, cantPersonas, fecha);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public void agregarProductoAMesa(Long idMesaOcupacion, Long idProducto, int cantidadProducto) throws ConexionException, BaseDeDatosException, ProductoNoExisteException, InsumoNoExisteException, MesaOcupacionNoExisteException, ProductoSinStockException {
		try {
			remoteObject.agregarProductoAMesa(idMesaOcupacion, idProducto, cantidadProducto);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public void cerrarMesa(Long idMesaOcupacion, Long idFormaDePago) throws ConexionException, BaseDeDatosException, MesaOcupacionNoExisteException, FormaDePagoNoExisteException {
		try {
			remoteObject.cerrarMesa(idMesaOcupacion, idFormaDePago);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public List<ComisionView> getComisionesMozos(String sucursal) throws ConexionException, BaseDeDatosException, SucursalNoExisteException {
		try {
			return remoteObject.getComisionesMozos(sucursal);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public void crearPlatoDirecto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, Long idInsumo, float cantInsumo) throws ConexionException, AreaNoExisteException, InsumoNoExisteException, RubroNoExisteException, BaseDeDatosException {
		try {
			remoteObject.crearPlatoDirecto(rubro, caducidad, comisionMozo, fecha, precio, nombreArea, idInsumo, cantInsumo);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public List<EmpleadoView> getEmpleadosBySucursal(String nombreSucursal) throws ConexionException, BaseDeDatosException, SucursalNoExisteException {
		try {
			return remoteObject.getEmpleadosBySucursal(nombreSucursal);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public List<FormaPagoView> getFormasDePago() throws ConexionException, BaseDeDatosException {
		try {
			return remoteObject.getFormasDePago();
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

}
