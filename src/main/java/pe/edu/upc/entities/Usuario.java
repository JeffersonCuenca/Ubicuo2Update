package pe.edu.upc.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idUsuario;
	
	@Column(name = "foto", nullable = true)
	private String photoUsuario;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Usuario no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Usuario no puede contener un número")
	@Column(name = "fistnameUsuario", length = 35, nullable = false)
	private String fistnameUsuario;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Usuario no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Usuario no puede contener un número")
	@Column(name = "lastnameUsuario", length = 35, nullable = false)
	private String lastnameUsuario;
	
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	@Column(name = "correo", length = 35, nullable = false)
	private String correo;
	
	@Pattern(regexp = "[^0]\\d{7}", message = "Ingrese DNI correctamente.")
	@Column(name = "dni", nullable = false)
	private String dni;
	
	private Boolean enabled;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<TipoUsuario> tipousuarios;
	
	@ManyToOne
	@JoinColumn(name = "idUbicacion", nullable = true)
	private Ubicacion ubicacion;
	
	@ManyToOne
	@JoinColumn(name = "idCargo", nullable = true)
	private Cargo cargo;
	
	@ManyToOne
	@JoinColumn(name = "idServicio", nullable = true)
	private Servicio servicio;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPhotoUsuario() {
		return photoUsuario;
	}

	public void setPhotoUsuario(String photoUsuario) {
		this.photoUsuario = photoUsuario;
	}

	public String getFistnameUsuario() {
		return fistnameUsuario;
	}

	public void setFistnameUsuario(String fistnameUsuario) {
		this.fistnameUsuario = fistnameUsuario;
	}

	public String getLastnameUsuario() {
		return lastnameUsuario;
	}

	public void setLastnameUsuario(String lastnameUsuario) {
		this.lastnameUsuario = lastnameUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<TipoUsuario> getTipousuarios() {
		return tipousuarios;
	}

	public void setTipousuarios(List<TipoUsuario> tipousuarios) {
		this.tipousuarios = tipousuarios;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}