package co.edu.unicundi.proyectoSpring.service.imp;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.Medico;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.repository.IMedicoRepo;
import co.edu.unicundi.proyectoSpring.service.interfaz.IMedicoService;

@Transactional
@Service
public class MedicoServiceImp implements IMedicoService {

	List<Medico> lista = new ArrayList<Medico>();

	@Autowired
	private IMedicoRepo medicoRepo;

	@Override
	public Medico retornarById(Integer id) throws NoExisteException {
		Medico med = medicoRepo.findById(id).orElseThrow(() -> new NoExisteException("El medico no existe"));
		return med;
	}

	@Override
	public Page<Medico> paginar(Integer pag, Integer size) throws NoExisteException {
		return medicoRepo.findAll(PageRequest.of(pag, size));
	}

	@Override
	public Page<Medico> retornarPorNombre(Integer pag, Integer size, String nombre) throws NoExisteException {
		return medicoRepo.findByNombre(PageRequest.of(pag, size),nombre);

	}

	@Override
	public void agregar(Medico medico) throws UniqueException {
		if (medico.getDireccion() != null) {
			medico.getDireccion().setMedico(medico);
		}
		medicoRepo.save(medico);
	}

	@Override
	public void editar(Medico medico) throws NoExisteException {

		Medico med = this.retornarById(medico.getId());

		if (med == null) {

			throw new NoExisteException("No existe el medico");

		} else {
			med.setNombre(medico.getNombre());
			med.setApellido(medico.getApellido());
			med.setCorreo(medico.getCorreo());
			med.getDireccion().setBarrio(medico.getDireccion().getBarrio());
			med.getDireccion().setCiudad(medico.getDireccion().getCiudad());
			med.getDireccion().setPais(medico.getDireccion().getPais());
			med.getDireccion().setDetalle(medico.getDireccion().getDetalle());

			this.medicoRepo.save(med);
		}

	}

	@Override
	public void eliminar(Integer id) throws NoExisteException {
		Medico med = this.retornarById(id);

		if (med == null) {
			throw new NoExisteException("No existe el medico");
		} else {
			medicoRepo.deleteById(id);
		}
	}

	@Override
	public List<Medico> retornar() throws NoExisteException {
		List<Medico> lista = medicoRepo.findAll();
		return lista;
	}

	@Override
	public Page<Medico> retornarPorApellido(Integer pag, Integer size, String apellido) throws NoExisteException {
		return medicoRepo.findByApellido(PageRequest.of(pag, size), apellido);
	}

	@Override
	public Page<Medico> retornarMayus(Integer pag, Integer size,String nombre) {
		return medicoRepo.retornarMayus(PageRequest.of(pag, size), nombre);
	}

	@Override
	public Page<Medico> retornarBarrio(Integer pag, Integer size,String barrio) {
		return medicoRepo.findByBarrio(PageRequest.of(pag, size), barrio);
	}

	@Override
	public Page<Medico> retornarCiudad(Integer pag, Integer size, String ciudad) {
		return medicoRepo.findByCiudad(PageRequest.of(pag, size) ,ciudad);
	}

	@Override
	public Page<Medico> retornarPais(Integer pag, Integer size, String pais) {
		return medicoRepo.findByPais(PageRequest.of(pag, size), pais);
	}

	@Override
	public Page<Medico> retornarDetalle(Integer pag, Integer size, String detalle) {
		return medicoRepo.findByDetalle(PageRequest.of(pag, size), detalle);
	}
	
	

}
