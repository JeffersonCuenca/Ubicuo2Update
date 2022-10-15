package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tiposervicios")
public class TipoServicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTiposervicio;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Tipo de Servicio no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Tipo de Servicio no puede contener un número")
	@Column(name = "nameTiposervicio", length = 35, nullable = false)
	private String nameTiposervicio;

	public TipoServicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoServicio(int idTiposervicio, String nameTiposervicio) {
		super();
		this.idTiposervicio = idTiposervicio;
		this.nameTiposervicio = nameTiposervicio;
	}

	public int getIdTiposervicio() {
		return idTiposervicio;
	}


	public void setIdTiposervicio(int idTiposervicio) {
		this.idTiposervicio = idTiposervicio;
	}


	public String getNameTiposervicio() {
		return nameTiposervicio;
	}


	public void setNameTiposervicio(String nameTiposervicio) {
		this.nameTiposervicio = nameTiposervicio;
	}


	
	
}
