package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "depositos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class DepositoEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3149269139672201799L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDeposito")
	private List<PedidoReposicionEntity> pedidosReposicion;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDeposito")
	private List<InsumoEntity> insumos;
	@OneToOne
	private EmpleadoEntity empleado;
	
	public DepositoEntity(String nombre, List<PedidoReposicionEntity> pedidosReposicion, List<InsumoEntity> insumos, EmpleadoEntity empleado) {
		this.nombre = nombre;
		this.pedidosReposicion = pedidosReposicion;
		this.insumos = insumos;
		this.empleado = empleado;
	}
	
	public DepositoEntity() {}

	public String getNombre() {
		return nombre;
	}

	public List<PedidoReposicionEntity> getPedidosReposicion() {
		return pedidosReposicion;
	}

	public List<InsumoEntity> getInsumos() {
		return insumos;
	}

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}
	
}
