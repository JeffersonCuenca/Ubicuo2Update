package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Distrito;
import pe.edu.upc.repositories.IDistritoRepository;
import pe.edu.upc.serviceinterface.IDistritoService;

@Service
public class DistritoServiceImplement  implements IDistritoService{

	@Autowired
	private IDistritoRepository dR;

	@Override
	public Integer insert(Distrito distrito) {
		// TODO Auto-generated method stub
		int rpta= dR.distritosExistentes(distrito.getNameDistrito());
		if(rpta==0) {
			dR.save(distrito);
			
		}
		return rpta;
	}

	@Override
	public List<Distrito> list() {
		// TODO Auto-generated method stub
		return dR.findAll();
	}

	@Override
	public void delete(int idDistrito) {
		// TODO Auto-generated method stub
		dR.deleteById(idDistrito);
	}

	@Override
	public Optional<Distrito> listId(int idDistrito) {
		// TODO Auto-generated method stub
		return dR.findById(idDistrito);
	}
}
