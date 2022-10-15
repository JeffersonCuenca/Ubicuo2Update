package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Servicio;
import pe.edu.upc.repositories.IServicioRepository;
import pe.edu.upc.serviceinterface.IServicioService;

@Service
public class ServiciosServiceImplement implements IServicioService{
	
	@Autowired
	private IServicioRepository sR;

	@Override
	public Integer insert(Servicio servicio) {
		// TODO Auto-generated method stub

		int rpta=sR.buscarServicio(servicio.getNameServicio());
		if (rpta==0) {
			sR.save(servicio);
		}
		return rpta;
		
	}

	@Override
	public List<Servicio> list() {
		// TODO Auto-generated method stub
		return sR.findAll();
	}

	@Override
	@Transactional
	public Servicio listarId(int idServicio) {
		// TODO Auto-generated method stub
		Optional<Servicio> op= sR.findById(idServicio);	
		return op.isPresent() ? op.get() : new Servicio();
	}

	@Override
	public void delete(int idServicio) {
		// TODO Auto-generated method stub
		sR.deleteById(idServicio);
	}

	@Override
	public Optional<Servicio> listId(int idServicio) {
		// TODO Auto-generated method stub
		return sR.findById(idServicio);
	}
	
	
}
