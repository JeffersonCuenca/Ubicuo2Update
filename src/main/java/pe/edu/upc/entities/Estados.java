package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "estados")
public class Estados {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cEstados;
	@NotNull
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del estado no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del estado no puede contener un número")
	@Column(name="nameEstados", length=35, nullable=false)
	private String nameEstados;

	public Estados() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estados(int cEstados, String nameEstados) {
		super();
		this.cEstados = cEstados;
		this.nameEstados = nameEstados;
	}

	public int getcEstados() {
		return cEstados;
	}

	public void setcEstados(int cEstados) {
		this.cEstados = cEstados;
	}

	public String getNameEstados() {
		return nameEstados;
	}

	public void setNameEstados(String nameEstados) {
		this.nameEstados = nameEstados;
	}

}
