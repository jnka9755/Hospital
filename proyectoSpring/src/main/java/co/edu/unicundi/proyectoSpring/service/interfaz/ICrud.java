package co.edu.unicundi.proyectoSpring.service.interfaz;

import org.springframework.data.domain.Page;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;

public interface ICrud <T, V> {
	
	public T retornarById(V id) throws NoExisteException;
	
	public Page<T> paginar(Integer pag, Integer size) throws NoExisteException;
	
	public Page<T> retornarPorNombre(Integer pag, Integer size,String nombre) throws NoExisteException;
	
	public void agregar(T entity) throws UniqueException, NoExisteException;

	public void editar(T entity) throws NoExisteException;

	public void eliminar(V id) throws NoExisteException;

}
