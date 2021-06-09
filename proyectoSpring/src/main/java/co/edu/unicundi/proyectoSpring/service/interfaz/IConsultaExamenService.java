package co.edu.unicundi.proyectoSpring.service.interfaz;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpring.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.view.InfoMedicoView;

@Service
public interface IConsultaExamenService extends ICrud<ConsultaExamen, Integer> {

	public void guardarNativo(ConsultaExamen consultaExamen) throws NoExisteException;
	
	List<ConsultaExamen> listarPorIdConsulta(Integer idConsulta);
	
	Page<ConsultaExamen> listarPorIdConsultaPaginado(Integer id, Integer page, Integer size);	
	
	public List<InfoMedicoView> vista (Integer page, Integer size);

	
}
