package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoServicio;

@Repository
public interface ITipoServicioRepository extends JpaRepository<TipoServicio, Integer>{

	@Query("select count(t.nameTiposervicio) from TipoServicio t where t.nameTiposervicio=:name")
	public int TipoServicioExistentes(@Param("name") String nombre);
	
	List<TipoServicio> findByNameTiposervicio(String name);
	
}
