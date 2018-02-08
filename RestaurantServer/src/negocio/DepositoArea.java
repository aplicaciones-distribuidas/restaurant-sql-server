package negocio;

import java.util.List;

public class DepositoArea extends Deposito {

	public DepositoArea(String nombre, List<PedidoReposicion> pedidosReposicion, List<Insumo> insumos, Empleado empleado) {
		super(nombre, pedidosReposicion, insumos, empleado);
	}

	

}
