package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="distritos")
public class Distrito {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idDistrito;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Distrito no puede contener un cracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Distrito no puede contener un número")
	@Column(name = "nameDistrito", length = 35, nullable = false)
	private String nameDistrito;

	
	public Distrito() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Distrito(int idDistrito, String nameDistrito) {
		super();
		this.idDistrito = idDistrito;
		this.nameDistrito = nameDistrito;
	}
	public int getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getNameDistrito() {
		return nameDistrito;
	}

	public void setNameDistrito(String nameDistrito) {
		this.nameDistrito = nameDistrito;
	}
	
}
