package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoServicio;
import pe.edu.upc.repositories.ITipoServicioRepository;
import pe.edu.upc.serviceinterface.ITipoServicioService;

@Service
public class TipoServicioServiceImplement implements ITipoServicioService{

	@Autowired
	private ITipoServicioRepository tR;
	
	@Override
	public Integer insert(TipoServicio tiposervicio) {
		// TODO Auto-generated method stub
		int rpta = tR.TipoServicioExistentes(tiposervicio.getNameTiposervicio());
		if (rpta == 0) {
			tR.save(tiposervicio);
		}
		return rpta;
	}

	@Override
	public List<TipoServicio> list() {
		// TODO Auto-generated method stub
		return tR.findAll();
	}

	@Override
	public void delete(int idTiposervicio) {
		// TODO Auto-generated method stub
		tR.deleteById(idTiposervicio);
		
	}

	@Override
	public Optional<TipoServicio> listId(int idTiposervicio) {
		// TODO Auto-generated method stub
		return tR.findById(idTiposervicio);
	}

	@Override
	public List<TipoServicio> findByNameTiposervicio(String name) {
		// TODO Auto-generated method stub
		return tR.findByNameTiposervicio(name);
	}

}
