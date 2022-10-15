package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entities.TipoUsuario;
import pe.edu.upc.entities.Usuario;

public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer>
{
	@Query("select count(c.usuario) from TipoUsuario c where c.usuario=:name")
	public int TipoUsuariosExistentes(@Param("name") Usuario nombre);
}