package co.edu.unicundi.proyectoSpring.service.interfaz;

import java.util.List;
import org.springframework.data.domain.Page;
import co.edu.unicundi.proyectoSpring.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;

public interface IDetalleConsulta extends ICrud<DetalleConsulta, Integer> {
	
	public List<DetalleConsulta> retornar() throws NoExisteException;
	
	public DetalleConsulta retornarDetalleConsulta(int id) throws NoExisteException;
		
	public Page<DetalleConsulta> retornarPorTratamiento(Integer pag, Integer size,String tratamiento) throws NoExisteException;

	public void agregar(DetalleConsulta det, int idConsulta) throws NoExisteException;
	
	public List<DetalleConsulta> retornarPorDiagnostico(String diagnostico) throws NoExisteException;

	public List<DetalleConsulta> retornarPorDiagnosticoTratamiento(String diagnostico, String tratamiento) throws NoExisteException;
	
	public List<DetalleConsulta> retornarOrdenarPorDiagnostico(String diagnostico) throws NoExisteException;
	
	public List<DetalleConsulta> retornarPorNombreDoctor(String nombreDoctor) throws NoExisteException;

}
