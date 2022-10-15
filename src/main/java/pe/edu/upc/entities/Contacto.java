package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "contactos")
public class Contacto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idContacto;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del contacto no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del contacto no puede contener un número")
	@Column(name="nameContacto", length=60, nullable=false)
	private String nameContacto;
	
	@NotNull
	@Column(name = "numeroCelular", length=9, nullable = false)
	private String numeroCelular;

	public Contacto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contacto(int idContacto, Usuario usuario, String nameContacto, String numeroCelular) {
		super();
		this.idContacto = idContacto;
		this.usuario = usuario;
		this.nameContacto = nameContacto;
		this.numeroCelular = numeroCelular;
	}

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNameContacto() {
		return nameContacto;
	}

	public void setNameContacto(String nameContacto) {
		this.nameContacto = nameContacto;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	
}
