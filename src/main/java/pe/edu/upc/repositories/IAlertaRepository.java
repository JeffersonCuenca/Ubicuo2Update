package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Alerta;
import pe.edu.upc.entities.Usuario;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta, Integer> {
	@Query("select count(a.idUsuarioRes) from Alerta a where a.idUsuarioRes=:name and a.cEstados in (1)")
	public int buscarAlerta(@Param("name") Usuario nombre);
	
	@Query(value = "SELECT u.fistname_usuario, u.lastname_usuario, count(al.id_usuario_res) "
			+ "from Usuario u join alertas al on u.id_usuario = al.id_usuario_res "
			+ "group by u.fistname_usuario, u.lastname_usuario "
			+ "ORDER BY count(al.id_usuario_res) desc limit 1 ", nativeQuery = true)
	public List<String[]> RescatistaAlertas();
	
	@Query(value = "SELECT t.name_tiposervicio, count(al.id_usuario_res) "
			+ "	from tiposervicios t join servicios s on t.id_tiposervicio = s.id_tipo_servicio "
			+ "	join Usuario u on s.id_servicio = u.id_servicio "
			+ "	join alertas al on u.id_usuario =  al.id_usuario_res "
			+ "	group by t.name_tiposervicio "
			+ "	ORDER BY count(al.id_usuario_res) desc", nativeQuery = true)
	public List<String[]> TipoServicioAlerta();
}
