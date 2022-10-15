package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Estados;
import pe.edu.upc.repositories.IEstadosRepository;
import pe.edu.upc.serviceinterface.IEstadosService;

@Service
public class EstadosServiceImplement implements IEstadosService{
	
	@Autowired
	private IEstadosRepository cR;
	
	@Override
	public Integer insert(Estados estados) {
		// TODO Auto-generated method stub
		int rpta=cR.estadosExistentes(estados.getNameEstados());
		if(rpta==0) {
			cR.save(estados);
		}
		return rpta;
	}

	@Override
	public List<Estados> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public void delete(int cEstados) {
		// TODO Auto-generated method stub
		cR.deleteById(cEstados);
	}

	@Override
	public Optional<Estados> listId(int cEstados) {
		// TODO Auto-generated method stub
		return cR.findById(cEstados);
	}

}
