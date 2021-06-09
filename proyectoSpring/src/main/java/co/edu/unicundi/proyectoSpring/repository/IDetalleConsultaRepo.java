package co.edu.unicundi.proyectoSpring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unicundi.proyectoSpring.entity.DetalleConsulta;

@Repository
public interface IDetalleConsultaRepo extends JpaRepository<DetalleConsulta, Integer> {
	
	@Query(value = "SELECT * FROM detalle_consulta WHERE id = ?1", nativeQuery = true)
	public DetalleConsulta buscarPorId(Integer id);
	
	@Query(value = "SELECT * FROM detalle_consulta as d INNER JOIN consulta as c ON d.id_consulta =c.id WHERE c.nombre_doctor = ?1", nativeQuery = true)
	public List<DetalleConsulta> findByNombreDoctor(String nombreDoctor);
	
	public List<DetalleConsulta> findByDiagnostico(String diagnostico);
	
	public List<DetalleConsulta> findByDiagnosticoAndTratamiento(String diagnostico, String tratamiento);
	
	public List<DetalleConsulta> findByDiagnosticoOrderByIdDesc(String diagnostico);
	
	public Page<DetalleConsulta> findByTratamiento(Pageable pageable, String tratamiento);	

}
