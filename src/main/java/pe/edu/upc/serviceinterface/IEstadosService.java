package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Estados;

public interface IEstadosService {
	public Integer insert(Estados estados);
	List<Estados> list();
	public void delete(int cEstados);	
	Optional<Estados> listId(int cEstados);

}
