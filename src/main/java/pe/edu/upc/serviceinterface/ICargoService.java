package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Cargo;

public interface ICargoService 
{
	public Integer insert(Cargo cargo);
	
	public void delete(int idCargo);
	
	Optional <Cargo> listId(int idCargo);
	
	List<Cargo> findByNameCargo(String name);
	
	List<Cargo> list();
	
	public List<String[]> cargoTopQuantityUsser();
}
