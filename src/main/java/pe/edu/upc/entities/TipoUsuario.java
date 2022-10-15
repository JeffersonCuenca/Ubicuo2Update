package pe.edu.upc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TipoUsuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "rol" }) })
public class TipoUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idTipoUsuario;
	
	private String rol;

	@ManyToOne
	@NotNull(message = "El usuario ya tiene rol")
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}
