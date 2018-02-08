package entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("depositoArea")
public class DepositoAreaEntity extends DepositoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7199274069511757183L;

	public DepositoAreaEntity(String nombre, List<PedidoReposicionEntity> pedidosReposicion, List<InsumoEntity> insumos, EmpleadoEntity empleado) {
		super(nombre, pedidosReposicion, insumos, empleado);
	}

	public DepositoAreaEntity() {}

}
