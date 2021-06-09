package co.edu.unicundi.proyectoSpring.service.interfaz;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.Consulta;
import co.edu.unicundi.proyectoSpring.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;

@Service
public interface IConsultaService extends ICrud<Consulta, Integer> {

	public List<Consulta> retornar() throws NoExisteException;
	
	public Consulta retornarConsulta(Integer id) throws NoExisteException;

	public Page<Consulta> retornarPorNombre(Integer pag, Integer size,String nombre) throws NoExisteException;

	public List<Consulta> retornarConsultaPorDetalleConsulta(Integer detalle);

}
