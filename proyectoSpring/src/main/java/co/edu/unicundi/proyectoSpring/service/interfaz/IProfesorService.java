package co.edu.unicundi.proyectoSpring.service.interfaz;

import java.util.ArrayList;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.Profesor;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;

public interface IProfesorService {
	
	public ArrayList<Profesor> retornar() throws NoExisteException;
	
	public Profesor retornarCedula(int cedula) throws NoExisteException;

	public void  agregar(Profesor profe) throws UniqueException;
	
	public void editar(Profesor profe) throws NoExisteException;
	
	public void eliminar(int cedula) throws NoExisteException;


}
