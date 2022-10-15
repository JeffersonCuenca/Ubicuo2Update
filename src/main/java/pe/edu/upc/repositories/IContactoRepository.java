package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Contacto;

@Repository
public interface IContactoRepository extends JpaRepository<Contacto, Integer>{
	
	@Query("select count(c.nameContacto) from Contacto c where c.nameContacto=:name")
	public int contactosExistentes(@Param("name") String nombre);
	
	@Query(value="select u.fistname_usuario, count(c.id_contacto) "
			+"from contactos c join Usuario u on c.id_usuario=u.id_usuario "
			+"group by u.fistname_usuario "
			+"order by 2 asc ", nativeQuery = true)
	public List<String[]> contactByUser();

}
