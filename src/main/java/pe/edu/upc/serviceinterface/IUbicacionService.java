package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Ubicacion;

public interface IUbicacionService {
	
	public Integer insert(Ubicacion ubicacion);
	
	List<Ubicacion> list();
	
	public void delete(int idUbicacion);
	
	Optional<Ubicacion> listId(int idUbicacion);
}
