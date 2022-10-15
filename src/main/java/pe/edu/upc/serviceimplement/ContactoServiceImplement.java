package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Contacto;
import pe.edu.upc.repositories.IContactoRepository;
import pe.edu.upc.serviceinterface.IContactoService;

@Service
public class ContactoServiceImplement implements IContactoService{

	@Autowired
	private IContactoRepository cR;
	
	@Override
	public Integer insert(Contacto contacto) {
		// TODO Auto-generated method stub
		int rpta= cR.contactosExistentes(contacto.getNameContacto());
		if (rpta==0) {
			cR.save(contacto);
		}
		return rpta;
	}

	@Override
	public List<Contacto> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public void delete(int idContacto) {
		// TODO Auto-generated method stub
		cR.deleteById(idContacto);
	}

	@Override
	@Transactional
	public Contacto listId(int idContacto) {
		// TODO Auto-generated method stub
		Optional<Contacto> op= cR.findById(idContacto);	
		return op.isPresent() ? op.get() : new Contacto();
	}

	@Override
	public List<String[]> contactByUser() {
		// TODO Auto-generated method stub
		return cR.contactByUser();
	}




}
