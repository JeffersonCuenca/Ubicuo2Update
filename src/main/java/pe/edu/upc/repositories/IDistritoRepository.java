package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Distrito;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Integer>{
	
	@Query("select count(d.nameDistrito) from Distrito d where d.nameDistrito=:name")
	public int distritosExistentes(@Param("name") String nombre);
}
