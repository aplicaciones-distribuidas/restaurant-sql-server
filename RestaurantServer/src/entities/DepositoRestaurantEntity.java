package entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("depositoRestaurant")
public class DepositoRestaurantEntity extends DepositoEntity {
	private static final long serialVersionUID = 6860668370272985461L;

	public DepositoRestaurantEntity(String nombre, List<PedidoReposicionEntity> pedidosReposicion,
			List<InsumoEntity> insumos, EmpleadoEntity empleado) {
		super(nombre, pedidosReposicion, insumos, empleado);
	}

	public DepositoRestaurantEntity() {
	}

}
