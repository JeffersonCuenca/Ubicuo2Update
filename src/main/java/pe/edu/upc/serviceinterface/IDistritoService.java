package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Distrito;

public interface IDistritoService {

	public Integer insert(Distrito distrito);
	
	List<Distrito> list();
	
	public void delete(int idDistrito);
	
	Optional<Distrito> listId(int idDistrito);
}
