package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.TipoUsuario;

public interface ITipoUsuarioService 
{
	public Integer insert(TipoUsuario TipoUsuario);
	
	public void insertUpdate(TipoUsuario TipoUsuario);
	
	List<TipoUsuario> list();
	
	public void delete(int idTipoUsuario);	
	
	Optional<TipoUsuario> listId(int idTipoUsuario);
}