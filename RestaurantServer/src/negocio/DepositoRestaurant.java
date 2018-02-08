package negocio;

import java.util.List;

public class DepositoRestaurant extends Deposito {

	public DepositoRestaurant(String nombre, List<PedidoReposicion> pedidosReposicion, List<Insumo> insumos, Empleado empleado) {
		super(nombre, pedidosReposicion, insumos, empleado);
	}

}
