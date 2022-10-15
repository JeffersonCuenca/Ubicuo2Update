package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Cargo;

@Repository
public interface ICargoRepository extends JpaRepository<Cargo, Integer>
{
	@Query("select count(c.nameCargo) from Cargo c where c.nameCargo=:name")
	public int cargosExistentes(@Param("name") String nombre);
	
	List<Cargo> findByNameCargo(String name);
	
	@Query(value ="SELECT ca.name_cargo,COUNT(us.id_cargo) FROM usuario us JOIN cargo ca ON us.id_cargo=ca.id_cargo GROUP BY ca.name_cargo", nativeQuery = true)
	public List<String[]> cargoTopQuantityUsser(); 
}
