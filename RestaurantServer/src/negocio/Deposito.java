package negocio;

import java.util.List;

public abstract class Deposito {

	private String nombre;
	private List<PedidoReposicion> pedidosReposicion;
	private List<Insumo> insumos;
	private Empleado empleado;

	public Deposito(String nombre, List<PedidoReposicion> pedidosReposicion, List<Insumo> insumos, Empleado empleado) {
		this.nombre = nombre;
		this.pedidosReposicion = pedidosReposicion;
		this.insumos = insumos;
		this.empleado = empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public List<PedidoReposicion> getPedidosReposicion() {
		return pedidosReposicion;
	}

	public List<Insumo> getInsumos() {
		return insumos;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

}
