package co.edu.unicundi.proyectoSpring.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unicundi.proyectoSpring.entity.Consulta;

@Repository
public interface IConsultaRepo extends JpaRepository<Consulta, Integer>{

	@Query(value = "SELECT * FROM consulta WHERE id = ?1", nativeQuery = true)
	public Consulta buscarPorId(Integer id);
	
	@Query(value = "SELECT COUNT (*) FROM consulta WHERE id = ?1", nativeQuery = true)
	public Consulta buscarCount(Integer id);
	
	@Query(value = "SELECT * FROM consulta INNER JOIN detalle_consulta ON detalle_consulta.id_consulta =consulta.id "
			+ "WHERE detalle_consulta.id = ?1", nativeQuery = true)
	public List<Consulta> findByDetalleConsulta(Integer detalleConsulta); 
	
	
	//public Page<Consulta> findByNombreDoctorStartingWith (Pageable pageable, String nombreDoctor);
		
	
}
