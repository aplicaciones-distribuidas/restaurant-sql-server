package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class ProductoEntity implements Serializable {
	private static final long serialVersionUID = 8088811606516162589L;

	@Id
	@GeneratedValue
	private Long id;

	private String rubro;
	private int caducidad;
	private float comisionMozo;
	private Date fecha;
	private float precio;
	@OneToOne(cascade = CascadeType.ALL)
	private AreaEntity area;

	public ProductoEntity(Long id, String rubro, int caducidad, float comisionMozo, Date fecha, float precio, AreaEntity area) {
		this.id = id;
		this.rubro = rubro;
		this.caducidad = caducidad;
		this.comisionMozo = comisionMozo;
		this.fecha = fecha;
		this.precio = precio;
		this.area = area;
	}

	public ProductoEntity() {
	}

	public Long getId() {
		return id;
	}

	public String getRubro() {
		return rubro;
	}


	public int getCaducidad() {
		return caducidad;
	}


	public float getComisionMozo() {
		return comisionMozo;
	}


	public Date getFecha() {
		return fecha;
	}


	public float getPrecio() {
		return precio;
	}


	public AreaEntity getArea() {
		return area;
	}

}
