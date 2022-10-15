package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Cargo;
import pe.edu.upc.repositories.ICargoRepository;
import pe.edu.upc.serviceinterface.ICargoService;

@Service
public class CargoServiceImplement implements ICargoService
{
	@Autowired
	private ICargoRepository cR;
	
	@Override
	public Integer insert(Cargo cargo) 
	{
		int rpta= cR.cargosExistentes(cargo.getNameCargo());
		if(rpta==0) 
		{
			cR.save(cargo);
		}
		return rpta;
	}
	
	@Override
	public void delete(int idCargo) 
	{
		cR.deleteById(idCargo);
	}

	@Override
	public Optional<Cargo> listId(int idCargo) 
	{
		return cR.findById(idCargo);
	}
	
	@Override
	public List<Cargo> findByNameCargo(String name) 
	{
		return cR.findByNameCargo(name);
	}
	
	@Override
	public List<Cargo> list() 
	{
		return cR.findAll();
	}

	@Override
	public List<String[]> cargoTopQuantityUsser() 
	{
		
		return cR.cargoTopQuantityUsser();
	}
	
}
