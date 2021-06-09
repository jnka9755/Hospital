package co.edu.unicundi.proyectoSpring.service.imp;

import java.util.ArrayList;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.Profesor;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.repository.IProfesorRepo;
import co.edu.unicundi.proyectoSpring.service.interfaz.IProfesorService;

@Transactional
@Service
public class ProfesorServiceImp implements IProfesorService {

	ArrayList<Profesor> listaProfe = new ArrayList<Profesor>();

	@Autowired
	private IProfesorRepo profesorRepo;

	@Override
	public ArrayList<Profesor> retornar() {
		return (ArrayList<Profesor>) profesorRepo.findAll();
		// return listaProfe;
	}

	@Override
	public Profesor retornarCedula(int cedula) throws NoExisteException {

		Profesor profe = profesorRepo.buscarPorCedulaJPQL(cedula);

		if (profe == null) {
			throw new NoExisteException("La cedula no existe");
		}

		return profe;

		// Profesor profesor = profesorRepo.findById(cedula).orElseThrow(
		// () -> new NoExisteException("Profesor no encontrado"));
		// return profesor;

		/*
		*/
	}

	@Override
	public void agregar(Profesor profe) throws UniqueException {

		Profesor profesor = profesorRepo.buscarPorCedulaJPQL(profe.getCedula());

		if (profesor != null) {
			throw new UniqueException("La cédula ya está resgistrada");
		}
		profesorRepo.save(profe);

		/*
		for (Profesor profesor : listaProfe) {
			if (profesor.getCedula() == profe.getCedula()) {
				throw new UniqueException("La cédula ya está resgistrada");
			}
		}
		// listaProfe.add(profe);*/
	}

	@Override
	public void editar(Profesor profe) throws NoExisteException, BadRequest {

		Profesor proff = this.retornarCedula(profe.getCedula());
		
		if (proff != null) {
			
			proff.setNombre(profe.getNombre());
			proff.setApellido(profe.getApellido());
			proff.setEdad(profe.getEdad());
			proff.setCorreo(profe.getCorreo());

			this.profesorRepo.save(proff);
		}else {
			throw new NoExisteException("No existe el profesor");
		}
		
		
		

		/*
		 * boolean opc = false;
		 * 
		 * for (Profesor profesor : listaProfe) { if (profesor.getId() == profe.getId())
		 * { profesor.setCedula(profe.getCedula());
		 * profesor.setNombre(profe.getNombre());
		 * profesor.setApellido(profe.getApellido()); opc = true; } }
		 * 
		 * if (!opc) { throw new NoExisteException("No existe el profesor"); }
		 */

	}

	@Override
	public void eliminar(int cedula) throws NoExisteException {

		
		Profesor proff = this.profesorRepo.buscarPorCedulaJPQL(cedula);
		
		if (proff !=null) {
			profesorRepo.eliminarPorCedulaJPQL(cedula);
		}else {
			throw new NoExisteException("No existe el profesor");
		}  
		
 
		/*
		 * boolean opc = false;
		 * 
		 * for (Profesor profesor : listaProfe) { if (profesor.getCedula() == cedula) {
		 * listaProfe.remove(profesor); opc = true; break; } }
		 * 
		 * if (!opc) { throw new NoExisteException("No existe el profesor"); }
		 */
	}

}
