package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Alerta;

public interface IAlertaService {

	public Integer insert(Alerta alerta);
	
	public void insertUpdate(Alerta alerta);
	
	List<Alerta> list();
	
	public void delete(int idAlerta);
	
	Optional<Alerta> listId(int idAlerta);
	
	public List<String[]> RescatistaAlertas();
	
	public List<String[]> TipoServicioAlerta();
}
