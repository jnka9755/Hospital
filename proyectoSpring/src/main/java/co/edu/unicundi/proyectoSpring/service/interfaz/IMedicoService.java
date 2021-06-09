package co.edu.unicundi.proyectoSpring.service.interfaz;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import co.edu.unicundi.proyectoSpring.entity.Medico;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;

@Service
public interface IMedicoService extends ICrud<Medico, Integer>{

	public List<Medico> retornar() throws NoExisteException;
	
	public Page<Medico> retornarPorApellido(Integer pag, Integer size, String apellido) throws NoExisteException;
	
	public Page<Medico> retornarMayus(Integer pag, Integer size, String nombre);
	
	public Page<Medico> retornarBarrio(Integer pag, Integer size,String barrio);
	
	public Page<Medico> retornarCiudad(Integer pag, Integer size,String ciudad);
	
	public Page<Medico> retornarPais(Integer pag, Integer size,String pais);
	
	public Page<Medico> retornarDetalle(Integer pag, Integer size,String detalle);
	
}
