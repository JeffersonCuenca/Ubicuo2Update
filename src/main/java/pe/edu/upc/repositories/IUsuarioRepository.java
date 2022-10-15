package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>
{
	public Usuario findByDni(String dni);
	
	@Query("select count(c.dni) from Usuario c where c.dni=:dni")
	public int usuariosExistentes(@Param("dni") String dni);
	
	@Transactional
	@Modifying
	@Query(value = "insert into tipousuario (nameTipoUsuario, usuario_id) VALUES (:nameTipoUsuario, :usuario_id)", nativeQuery = true)
	public void insRol(@Param("nameTipoUsuario") String nameTipoUsuario, @Param("usuario_id") Long usuario_id);
}