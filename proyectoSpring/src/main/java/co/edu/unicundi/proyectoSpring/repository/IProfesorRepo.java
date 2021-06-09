package co.edu.unicundi.proyectoSpring.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unicundi.proyectoSpring.entity.Profesor;

@Repository
public interface IProfesorRepo extends JpaRepository<Profesor, Integer> {
	
	//SQL
	
	@Query(value = "SELECT * FROM profesor WHERE cedula = ?1", nativeQuery = true)
	public Profesor buscarPorCedulaSql(Integer cedula);
   
	//JPQL 

	@Query(value = "SELECT p FROM Profesor p WHERE p.cedula = :cedula", nativeQuery = false)
	public Profesor buscarPorCedulaJPQL(Integer cedula);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Profesor WHERE cedula = :cedula", nativeQuery = false)
	public void eliminarPorCedulaJPQL(Integer cedula);
	
	
	public Profesor findByCedula(Integer cedula);
	
	
}
