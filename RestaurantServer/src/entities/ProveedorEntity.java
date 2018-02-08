package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
public class ProveedorEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4107759938918532615L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private String telefono;
	private String direccion;

	public ProveedorEntity(String nombre, String telefono, String direccion) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public ProveedorEntity(Long id, String nombre, String telefono, String direccion) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public ProveedorEntity() {}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public Long getId() {
		return id;
	}
	
}
