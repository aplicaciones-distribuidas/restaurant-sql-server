package negocio;

import java.util.List;

public class DepositoGeneral extends Deposito {

	public DepositoGeneral(String nombre, List<PedidoReposicion> pedidosReposicion, List<Insumo> insumos, Empleado empleado) {
		super(nombre, pedidosReposicion, insumos, empleado);
	}

	

}
