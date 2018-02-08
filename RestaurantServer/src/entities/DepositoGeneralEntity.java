package entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("depositoGeneral")
public class DepositoGeneralEntity extends DepositoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1909121784679595087L;

	public DepositoGeneralEntity(String nombre, List<PedidoReposicionEntity> pedidosReposicion, List<InsumoEntity> insumos, EmpleadoEntity empleado) {
		super(nombre, pedidosReposicion, insumos, empleado);
	}

	public DepositoGeneralEntity() {}

}
