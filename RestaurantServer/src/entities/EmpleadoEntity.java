package entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class EmpleadoEntity implements Serializable {
	private static final long serialVersionUID = -7273500272703721840L;

	@Id
	@GeneratedValue
	private Long id;

	private String nombre;
	private String apellido;
	private int porcentajeComision;

	@OneToOne
	private RolEntity rol;

	@ManyToOne
	private SectorSalonEntity sectorSalon;

	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ComisionEntity> comisiones;

	public EmpleadoEntity(Long id, String nombre, String apellido, int porcentajeComision, RolEntity rol,
						  List<ComisionEntity> comisiones, SectorSalonEntity sectorSalon) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.porcentajeComision = porcentajeComision;
		this.rol = rol;
		this.comisiones = comisiones;
		this.sectorSalon = sectorSalon;
	}

	public EmpleadoEntity() {
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getPorcentajeComision() {
		return porcentajeComision;
	}

	public RolEntity getRol() {
		return rol;
	}

	public List<ComisionEntity> getComisiones() {
		return comisiones;
	}

	public SectorSalonEntity getSectorSalon() {
		return sectorSalon;
	}

	public void setSectorSalon(SectorSalonEntity sectorSalon) {
		this.sectorSalon = sectorSalon;
	}
}
