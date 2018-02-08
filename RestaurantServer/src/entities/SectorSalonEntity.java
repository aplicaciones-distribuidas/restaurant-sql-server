package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "sectores_salon")
public class SectorSalonEntity implements Serializable {
	private static final long serialVersionUID = 3194137122676407785L;

	public SectorSalonEntity() {
	}

	public SectorSalonEntity(Long id) {
		this.id = id;
	}

	public SectorSalonEntity(Long id, String nombre, SucursalEntity sucursal, List<MesaEntity> mesas,
							 List<EmpleadoEntity> empleados) {
		this.id = id;
		this.nombre = nombre;
		this.sucursal = sucursal;
		this.mesas = mesas;
		this.empleados = empleados;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@ManyToOne(cascade = CascadeType.ALL)
	private SucursalEntity sucursal;

	@OneToMany(mappedBy = "sectorSalon", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MesaEntity> mesas;

	@OneToMany(mappedBy = "sectorSalon", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<EmpleadoEntity> empleados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public SucursalEntity getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalEntity sucursal) {
		this.sucursal = sucursal;
	}

	public List<MesaEntity> getMesas() {
		return mesas;
	}

	public void setMesas(List<MesaEntity> mesas) {
		this.mesas = mesas;
	}

	public List<EmpleadoEntity> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<EmpleadoEntity> empleados) {
		this.empleados = empleados;
	}

}
