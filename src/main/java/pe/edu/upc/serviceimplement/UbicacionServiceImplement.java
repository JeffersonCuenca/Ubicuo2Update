package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Ubicacion;
import pe.edu.upc.repositories.IUbicacionRepository;
import pe.edu.upc.serviceinterface.IUbicacionService;

@Service
public class UbicacionServiceImplement implements IUbicacionService{

	@Autowired
	private IUbicacionRepository uR;

	@Override
	public Integer insert(Ubicacion ubicacion) {
		// TODO Auto-generated method stub
		int rpta= uR.ubicacionesExistentes(ubicacion.getNlongitud());
		if(rpta==0) {
			uR.save(ubicacion);
		}
		return rpta;
	}

	@Override
	public List<Ubicacion> list() {
		// TODO Auto-generated method stub
		return uR.findAll();
	}

	@Override
	public void delete(int idUbicacion) {
		// TODO Auto-generated method stub
		uR.deleteById(idUbicacion);
	}

	@Override
	public Optional<Ubicacion> listId(int idUbicacion) {
		// TODO Auto-generated method stub
		return uR.findById(idUbicacion);
	}
}
