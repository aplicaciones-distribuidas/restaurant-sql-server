package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "sucursales")
public class SucursalEntity implements Serializable {
	private static final long serialVersionUID = -4659270814259782962L;

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return this.id;
	}

	private String nombre;
	private String ubicacion;
	private int capacidad;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSucursal")
	private List<CartaEntity> cartas;

	@OneToOne
	private CajaEntity caja;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSucursal")
	private List<AreaEntity> areas;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSucursal")
	private List<PedidoEntity> pedidos;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSucursal")
	private List<ReservaEntity> reservas;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSucursal")
	private List<TareaEntity> tareas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SectorSalonEntity> sectores;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSucursal")
	private List<PedidoReposicionEntity> pedidosReposicion;

	@OneToOne
	private DepositoEntity deposito;

	public SucursalEntity(Long id, String nombre, String ubicacion, int capacidad, List<CartaEntity> cartas,
			CajaEntity caja, List<AreaEntity> areas, List<PedidoEntity> pedidos, List<ReservaEntity> reservas,
			List<TareaEntity> tareas, List<SectorSalonEntity> sectores, List<PedidoReposicionEntity> pedidosReposicion,
			DepositoEntity deposito) {
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
		this.cartas = cartas;
		this.caja = caja;
		this.areas = areas;
		this.pedidos = pedidos;
		this.reservas = reservas;
		this.tareas = tareas;
		this.sectores = sectores;
		this.pedidosReposicion = pedidosReposicion;
		this.deposito = deposito;
	}

	public SucursalEntity() {
	}

	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public List<CartaEntity> getCartas() {
		return cartas;
	}

	public CajaEntity getCaja() {
		return caja;
	}

	public List<AreaEntity> getAreas() {
		return areas;
	}

	public List<PedidoEntity> getPedidos() {
		return pedidos;
	}

	public List<ReservaEntity> getReservas() {
		return reservas;
	}

	public List<TareaEntity> getTareas() {
		return tareas;
	}

	public List<SectorSalonEntity> getSectores() {
		return sectores;
	}

	public List<PedidoReposicionEntity> getPedidosReposicion() {
		return pedidosReposicion;
	}

	public DepositoEntity getDeposito() {
		return deposito;
	}

}
