package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.TipoServicio;

public interface ITipoServicioService {
	
	public Integer insert(TipoServicio tiposervicio);

	List<TipoServicio> list();
	
	public void delete(int idTiposervicio);
	
	Optional<TipoServicio> listId(int idTiposervicio);
	
	List<TipoServicio> findByNameTiposervicio(String name);
}
