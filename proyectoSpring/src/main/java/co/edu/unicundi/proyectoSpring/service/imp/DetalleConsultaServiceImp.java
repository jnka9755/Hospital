package co.edu.unicundi.proyectoSpring.service.imp;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.Consulta;
import co.edu.unicundi.proyectoSpring.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.repository.IConsultaRepo;
import co.edu.unicundi.proyectoSpring.repository.IDetalleConsultaRepo;
import co.edu.unicundi.proyectoSpring.service.interfaz.IDetalleConsulta;

@Service
public class DetalleConsultaServiceImp implements IDetalleConsulta{

	@Autowired
	public IDetalleConsultaRepo detallerepo;
	
	@Autowired
	public IConsultaRepo consultaRepo;
	
	@Override
	public List<DetalleConsulta> retornar() throws NoExisteException {
		List<DetalleConsulta> detalle =detallerepo.findAll();
		
		return detalle;
	}

	@Override
	public DetalleConsulta retornarDetalleConsulta(int id) throws NoExisteException {
		DetalleConsulta cons = detallerepo.findById(id).orElseThrow(() -> new NoExisteException("La consulta no existe"));
		cons.getId();
		return cons;
	}

	@Override
	public void agregar(DetalleConsulta det, int idConsulta) throws NoExisteException {
		
		Consulta detalle = this.consultaRepo.buscarPorId(idConsulta);

		if (detalle !=null) {
			det.setConsulta(detalle);
			this.detallerepo.save(det);
			
		}else {
			throw new NoExisteException("No existe la consulta");
		}
		
	}	
	

	@Override
	public void editar(DetalleConsulta consulta) throws NoExisteException {

		DetalleConsulta detalle = this.retornarDetalleConsulta(consulta.getId());
		
		if (detalle == null) {
			
			throw new NoExisteException("La consulta no existe");

		} else {
			detalle.setTratamiento(consulta.getTratamiento());
			detalle.setDiagnostico(consulta.getDiagnostico());
			this.detallerepo.save(detalle);
		}
		
	}
   

	@Override
	public List<DetalleConsulta> retornarPorDiagnostico(String diagnostico) throws NoExisteException {
		return detallerepo.findByDiagnostico(diagnostico);
	}

	@Override
	public List<DetalleConsulta> retornarPorDiagnosticoTratamiento(String diagnostico, String tratamiento)throws NoExisteException {
		return detallerepo.findByDiagnosticoAndTratamiento(diagnostico, tratamiento);
	}

	@Override
	public List<DetalleConsulta> retornarOrdenarPorDiagnostico(String diagnostico) {
		return detallerepo.findByDiagnosticoOrderByIdDesc(diagnostico);
	}

	@Override
	public List<DetalleConsulta> retornarPorNombreDoctor(String nombreDoctor) throws NoExisteException {
		List<DetalleConsulta> detalle = detallerepo.findByNombreDoctor(nombreDoctor);
		return detalle;
			
	}

	@Override
	public Page<DetalleConsulta> retornarPorTratamiento(Integer pag, Integer size, String tratamiento)
			throws NoExisteException {
		return detallerepo.findByTratamiento(PageRequest.of(pag, size), tratamiento);
	}

	@Override
	public DetalleConsulta retornarById(Integer id) throws NoExisteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DetalleConsulta> paginar(Integer pag, Integer size) throws NoExisteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DetalleConsulta> retornarPorNombre(Integer pag, Integer size, String nombreMedico)
			throws NoExisteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregar(DetalleConsulta entity) throws UniqueException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) throws NoExisteException {
		
		DetalleConsulta cons = detallerepo.buscarPorId(id);
		if (cons != null) {

			detallerepo.deleteById(id);

		} else {

			throw new NoExisteException("La consulta no existe");
		}
		
	}

	

  

}
