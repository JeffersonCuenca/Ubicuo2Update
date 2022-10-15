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
@Table(name="servicios")
public class Servicio {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idServicio;
	

	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del servicio no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del servicio no puede contener un número")
	@Column(name = "nameServicio", nullable = false)
	private String nameServicio;
	
	@Column(name="direccionServicio",nullable=false, length=60)
	private String direccionServicio;
	
	
	@Column(name="numtelef",nullable=false, length=60)
	private String numtelef;
	
	@ManyToOne
	@NotNull(message = "Seleccione un Distrito")
	@JoinColumn(name = "idDistrito", nullable = false)
	private Distrito distrito;

	@ManyToOne
	@NotNull(message = "Seleccione un Tipo de Servicio")
	@JoinColumn(name = "idTipoServicio", nullable = false)
	private TipoServicio tipoServicio;

	public Servicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Servicio(int idServicio,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del servicio no puede contener un número") @Pattern(regexp = "[^0-9]+", message = "El nombre del servicio no puede contener un número") String nameServicio,
			String direccionServicio,
			@Pattern(regexp = "[^0]\\d{2}", message = "Ingrese DNI correctamente.") String numtelef,
			@NotNull(message = "Seleccione un Distrito") Distrito distrito,
			@NotNull(message = "Seleccione un Tipo de Servicio") TipoServicio tipoServicio) {
		super();
		this.idServicio = idServicio;
		this.nameServicio = nameServicio;
		this.direccionServicio = direccionServicio;
		this.numtelef = numtelef;
		this.distrito = distrito;
		this.tipoServicio = tipoServicio;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNameServicio() {
		return nameServicio;
	}

	public void setNameServicio(String nameServicio) {
		this.nameServicio = nameServicio;
	}

	public String getDireccionServicio() {
		return direccionServicio;
	}

	public void setDireccionServicio(String direccionServicio) {
		this.direccionServicio = direccionServicio;
	}

	public String getNumtelef() {
		return numtelef;
	}

	public void setNumtelef(String numtelef) {
		this.numtelef = numtelef;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	
}
