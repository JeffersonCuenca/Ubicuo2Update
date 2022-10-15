package pe.edu.upc.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "alertas")
public class Alerta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlerta;
	
	@ManyToOne
	@NotNull(message = "Seleccione un Estado")
	@JoinColumn(name = "cEstados", nullable = false)
	private Estados cEstados;
	
	@ManyToOne
	@NotNull(message = "Seleccione un Usuario")
	@JoinColumn(name = "idUsuarioAux", nullable = false)
	private Usuario idUsuarioAux;
	
	@ManyToOne
	@NotNull(message = "Seleccione un Usuario")
	@JoinColumn(name = "idUsuarioRes", nullable = false)
	private Usuario idUsuarioRes;

	@NotNull(message = "Ingrese una fecha")
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date fecha;

	public Alerta() {
		super();
		// TODO Auto-generated constructor stub
		Calendar today = Calendar.getInstance();
		fecha = today.getTime();
		today.add(Calendar.DATE, 3);
		today.set(Calendar.HOUR_OF_DAY, 19);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
	}

	public Alerta(int idAlerta, Estados cEstados, Usuario idUsuarioAux, Usuario idUsuarioRes, Date fecha) {
		super();
		this.idAlerta = idAlerta;
		this.cEstados = cEstados;
		this.idUsuarioAux = idUsuarioAux;
		this.idUsuarioRes = idUsuarioRes;
		this.fecha = fecha;
	}

	public int getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(int idAlerta) {
		this.idAlerta = idAlerta;
	}

	public Estados getcEstados() {
		return cEstados;
	}

	public void setcEstados(Estados cEstados) {
		this.cEstados = cEstados;
	}

	public Usuario getIdUsuarioAux() {
		return idUsuarioAux;
	}

	public void setIdUsuarioAux(Usuario idUsuarioAux) {
		this.idUsuarioAux = idUsuarioAux;
	}

	public Usuario getIdUsuarioRes() {
		return idUsuarioRes;
	}

	public void setIdUsuarioRes(Usuario idUsuarioRes) {
		this.idUsuarioRes = idUsuarioRes;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

		
}
