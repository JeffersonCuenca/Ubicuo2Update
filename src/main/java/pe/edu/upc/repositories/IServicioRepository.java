package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Servicio;

@Repository
public interface IServicioRepository extends JpaRepository<Servicio, Integer>{

	@Query("select count(s.nameServicio) from Servicio s where s.nameServicio=:name")
	public int buscarServicio(@Param("name") String nombre);
}
