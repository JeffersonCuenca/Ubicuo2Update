package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Usuario;

public interface IUsuarioService 
{
	public Integer insert(Usuario Usuario);
	
	public void delete(int idUsuario);
	
	List<Usuario> list();
	
	Usuario listarId(int idUsuario);
}