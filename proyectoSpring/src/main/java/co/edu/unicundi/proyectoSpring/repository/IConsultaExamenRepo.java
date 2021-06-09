package co.edu.unicundi.proyectoSpring.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import co.edu.unicundi.proyectoSpring.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpring.view.InfoMedicoView;

@Repository
public interface IConsultaExamenRepo extends JpaRepository<ConsultaExamen, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen, info_adicional) values(:idConsulta, :idExamen, :infoAdicional)", nativeQuery = true)
	public void guardar(@Param("idConsulta") Integer idConsulta,@Param("idExamen") Integer idExamen, @Param("infoAdicional") String infoAdicional);
	
	
	@Query(value = "SELECT COUNT(*) FROM consulta_examen WHERE id_consulta = :idConsulta AND id_examen = :idExamen", nativeQuery = true)
	public Integer buscaConsultaExamen(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);

	
	@Query(value = "from ConsultaExamen ce where ce.consulta.id = :idConsulta")
	List<ConsultaExamen> listarPorIdConsulta(@Param("idConsulta")Integer idConsulta);
	
	Page<ConsultaExamen> findByConsulta_Id(Integer id, Pageable page);
	
	
	@Query(value="SELECT * FROM info_medico_view", nativeQuery = true)
	public Page<InfoMedicoView> listarView(Pageable pageable);
	
	@Query(value="SELECT * FROM info_medico_view", nativeQuery = true)
	public List<InfoMedicoView> view(Pageable pageable);

}
