package co.edu.unicundi.proyectoSpring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unicundi.proyectoSpring.entity.Medico;

@Repository
public interface IMedicoRepo extends JpaRepository<Medico, Integer>{
	
	@Query(value = "SELECT * FROM medico WHERE nombre_medico = ?1", nativeQuery = true)
	public Page<Medico> findByNombre(Pageable pageable,String nombre);
	
	@Query(value = "SELECT * FROM medico WHERE apellido_medico = ?1", nativeQuery = true)
	public Page<Medico> findByApellido(Pageable pageable, String apellido);

	@Query(value = "SELECT m FROM Medico m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre,'%'))", nativeQuery = true)
	public Page<Medico> retornarMayus (Pageable pageable,String nombre);
	
	@Query(value = "SELECT * FROM direccion as d INNER JOIN medico as m ON d.medico_id =m.id WHERE d.barrio =?1", nativeQuery = true)
	public Page<Medico> findByBarrio(Pageable pageable, String barrio);
	
	@Query(value = "SELECT * FROM direccion as d INNER JOIN medico as m ON d.medico_id =m.id WHERE d.ciudad =?1", nativeQuery = true)
	public Page<Medico> findByCiudad(Pageable pageable, String ciudad);

	@Query(value = "SELECT * FROM direccion as d INNER JOIN medico as m ON d.medico_id =m.id WHERE d.pais =?1", nativeQuery = true)
	public Page<Medico> findByPais(Pageable pageable, String pais);
	
	@Query(value = "SELECT * FROM direccion as d INNER JOIN medico as m ON d.medico_id =m.id WHERE d.detalle =?1", nativeQuery = true)
	public Page<Medico> findByDetalle(Pageable pageable, String detalle);
	
}
