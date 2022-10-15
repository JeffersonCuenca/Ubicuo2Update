package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Servicio;


public interface IServicioService {
	public Integer insert(Servicio servicio);
	
	List<Servicio> list();
	
	Servicio listarId(int idServicio);
	public void delete(int idServicio);
	
	Optional<Servicio> listId(int idServicio);
}
