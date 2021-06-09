package co.edu.unicundi.proyectoSpring.service.imp;

import java.util.List;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.repository.IConsultaExamenRepo;
import co.edu.unicundi.proyectoSpring.service.interfaz.IConsultaExamenService;
import co.edu.unicundi.proyectoSpring.view.InfoMedicoView;

@Service
public class IConsultaExamenServiceImp implements IConsultaExamenService {

	@Autowired
	private IConsultaExamenRepo consultaExamenRepo;
	
	@Override
	public ConsultaExamen retornarById(Integer id) throws NoExisteException {
		ConsultaExamen cons = consultaExamenRepo.findById(id).orElseThrow(() -> new NoExisteException("La consulta no existe"));
		return cons;
	}

	@Override
	public Page<ConsultaExamen> paginar(Integer pag, Integer size) throws NoExisteException {
		return consultaExamenRepo.findAll(PageRequest.of(pag, size));
	}

	@Override
	public Page<ConsultaExamen> retornarPorNombre(Integer pag, Integer size, String nombre) throws NoExisteException {
		
		return null;
	}

	@Override
	public void agregar(ConsultaExamen CE) throws NoExisteException {
	
	}

	@Override
	public void editar(ConsultaExamen entity) throws NoExisteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) throws NoExisteException {
		
		List<?> lista = consultaExamenRepo.listarPorIdConsulta(id);
		if (lista != null) {

			consultaExamenRepo.deleteById(id);

		} else {

			throw new NoExisteException("La consulta no existe");
		}
		
	}

	@Override
	public void guardarNativo(ConsultaExamen consultaExamen) throws NoExisteException {
		
		if(consultaExamen.getConsulta() == null)
			throw new NoExisteException("id Consulta es requerido");
		if(consultaExamen.getExamen() == null)
			throw new NoExisteException("id Examen es requerido");
		if(consultaExamenRepo.buscaConsultaExamen(consultaExamen.getConsulta().getId(), consultaExamen.getExamen().getId()) > 0)
			throw new NoExisteException("Esta consulta ya tiene asociado este examen");
		else {
			consultaExamenRepo.guardar(consultaExamen.getConsulta().getId(), 
					consultaExamen.getExamen().getId(), consultaExamen.getInfoAdicional());
		}
	
	}

	@Override
	public List<ConsultaExamen> listarPorIdConsulta(Integer idConsulta) {
		return consultaExamenRepo.listarPorIdConsulta(idConsulta);
	}

	@Override
	public Page<ConsultaExamen> listarPorIdConsultaPaginado(Integer id, Integer page, Integer size) {
		return consultaExamenRepo.findByConsulta_Id(id, PageRequest.of(page, size));
	}

	@Override
	public List<InfoMedicoView> vista(Integer page, Integer size) {
		return consultaExamenRepo.view(PageRequest.of(page, size));
	}
	

}
