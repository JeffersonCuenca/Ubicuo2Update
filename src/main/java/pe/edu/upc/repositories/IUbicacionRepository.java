package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Ubicacion;

@Repository
public interface IUbicacionRepository extends JpaRepository<Ubicacion, Integer>{
	
	@Query("select count(u.nlongitud) from Ubicacion u where u.nlongitud=:name")
	public int ubicacionesExistentes(@Param("name") String nombre);
}
