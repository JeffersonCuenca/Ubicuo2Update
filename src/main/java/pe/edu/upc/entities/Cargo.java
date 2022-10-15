package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="cargo")
public class Cargo 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idCargo;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Distrito no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Distrito no puede contener un número")
	@Column(name = "nameCargo", length = 35, nullable = false)
	private String nameCargo;

	public Cargo() 
	{
		super();
	}

	public Cargo(int idCargo, String nameCargo) 
	{
		super();
		this.idCargo = idCargo;
		this.nameCargo = nameCargo;
	}

	public int getIdCargo() 
	{
		return idCargo;
	}
	public void setIdCargo(int idCargo) 
	{
		this.idCargo = idCargo;
	}

	public String getNameCargo() 
	{
		return nameCargo;
	}
	public void setNameCargo(String nameCargo) 
	{
		this.nameCargo = nameCargo;
	}
	
}
