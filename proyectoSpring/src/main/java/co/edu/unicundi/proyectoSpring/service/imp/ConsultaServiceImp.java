package co.edu.unicundi.proyectoSpring.service.imp;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.Consulta;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.repository.IConsultaRepo;
import co.edu.unicundi.proyectoSpring.service.interfaz.IConsultaService;

@Transactional
@Service
public class ConsultaServiceImp implements IConsultaService {

	List<Consulta> listaConsulta = new ArrayList<Consulta>();

	@Autowired
	private IConsultaRepo consultaRepo;

	@Override
	public List<Consulta> retornar() throws NoExisteException {
		List<Consulta> listaConsulta = consultaRepo.findAll();
		return listaConsulta;
	}

	@Override
	public Consulta retornarConsulta(Integer id) throws NoExisteException {

		Consulta cons = consultaRepo.findById(id).orElseThrow(() -> new NoExisteException("La consulta no existe"));
		// cons.setDetalleConsulta(null);
		return cons;
	}

	@Override
	public void agregar(Consulta consulta) throws UniqueException {

		consulta.setId(null);
		if (consulta.getDetalleConsulta() != null) {
			consulta.getDetalleConsulta().forEach(det -> {
				det.setConsulta(consulta);
			});
		}
		consultaRepo.save(consulta);
	}

	@Override
	public void editar(Consulta consulta) throws NoExisteException {
		Consulta cons = this.retornarConsulta(consulta.getId());
		
		if (cons == null) {

			throw new NoExisteException("No existe la consulta");

		} else {
			cons.setMedico(consulta.getMedico());
			cons.setFecha(consulta.getFecha());
			this.consultaRepo.save(cons);
		}

	}

	@Override
	public void eliminar(Integer id) throws NoExisteException {

		Consulta cons = consultaRepo.buscarPorId(id);
		if (cons != null) {

			consultaRepo.deleteById(id);

		} else {

			throw new NoExisteException("La consulta no existe");
		}

	}

	@Override
	public Page<Consulta> paginar(Integer pag, Integer size) throws NoExisteException {
		
		return consultaRepo.findAll(PageRequest.of(pag, size, Sort.by(Sort.Direction.ASC, "nombre").ascending()));
	}

	@Override
	public Page<Consulta> retornarPorNombre(Integer pag, Integer size, String ordenar) throws NoExisteException {
		//return consultaRepo.findByNombreDoctorStartingWith(PageRequest.of(pag, size), ordenar); 
		return null;
	}

	@Override
	public List<Consulta> retornarConsultaPorDetalleConsulta(Integer detalle) {
		return consultaRepo.findByDetalleConsulta(detalle);
	}

	@Override
	public Consulta retornarById(Integer id) throws NoExisteException {
		return consultaRepo.buscarPorId(id);
	}
	
	
	
	
}
