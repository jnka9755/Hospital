package co.edu.unicundi.proyectoSpring.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.Consulta;
import co.edu.unicundi.proyectoSpring.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.service.interfaz.IConsultaExamenService;
import co.edu.unicundi.proyectoSpring.service.interfaz.IConsultaService;
import co.edu.unicundi.proyectoSpring.view.InfoMedicoView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/consultas")
@Api(tags = "Consulta Controller")
public class ConsultaController {
	
	@Autowired
	private IConsultaService consultaService;
	
	@Autowired
	private IConsultaExamenService consultaExamenService;

	@ApiOperation(value = "Método GET", notes = "Método de petición que retorna todas las consultas existentes de su lista correspondiente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK. El objeto se ha encontrado", response = List.class),
			@ApiResponse(code = 204, message = "No Content. La lista del objeto consulta está vacía"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })
	
	@GetMapping("/retornar")
	public ResponseEntity<List<Consulta>> retornar() throws NoExisteException {
		List<Consulta> listaConsulta = consultaService.retornar();
		return new ResponseEntity<List<Consulta>>(listaConsulta, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Método GET por id", notes = "Método de petición que retorna a todos los profesores existentes en la lista filtrado por el parametro 'id'")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El objeto se ha encontrado", response = Consulta.class),
			@ApiResponse(code = 404, message = "Not Found. El objeto profesor no existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@GetMapping("/retornarConsulta/{id}")
	public ResponseEntity<Object> retornarConsulta(@PathVariable int id) throws NoExisteException {
		Consulta cons;
		cons = consultaService.retornarConsulta(id);
		return new ResponseEntity<Object>(cons, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Método POST", notes = "Método de petición que agrega una consulta nueva")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created. El objeto se ha agregado correctamente", response = Consulta.class),
			@ApiResponse(code = 400, message = "Bad Request. El cuerpo del objeto ingresado es incorrecto"),
			@ApiResponse(code = 400, message = "Bad Request. El objeto profesor ya existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 415, message = "Unsupported Media Type. El formato del objeto enviado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@PostMapping("agregar")
	public ResponseEntity<Object> agregar(@Valid @RequestBody Consulta cons) throws UniqueException, NoExisteException {
		consultaService.agregar(cons);
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}
	
	@ApiOperation(value = "Método PUT", notes = "Método de petición que edita un objeto consulta")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El objeto consulta se ha editado correctamente", response = Consulta.class),
			@ApiResponse(code = 400, message = "Bad Request. El cuerpo del objeto ingresado es incorrecto"),
			@ApiResponse(code = 404, message = "Not Found. El objeto profesor no existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 415, message = "Unsupported Media Type. El formato del objeto enviado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@PutMapping("editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Consulta cons) throws NoExisteException, NotFound {
		consultaService.editar(cons);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	@ApiOperation(value = "Método DELETE", notes = "Método de petición que elimita el objeto consulta por el parámetrp'id'")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content. El objeto consulta se ha eliminado correctamente", response = Consulta.class),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable(required = true) int id) throws NoExisteException {
		consultaService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/retornarPagina/{pag}/{size}")
	public ResponseEntity<Page<Consulta>> retornarPagina(@PathVariable int pag, @PathVariable int size) throws NoExisteException {
		Page<Consulta> listaCons = consultaService.paginar(pag, size);
		return new ResponseEntity<Page<Consulta>>(listaCons, HttpStatus.OK);
	}
	
	@GetMapping("/retornarDoctor/{pag}/{size}/{nombreDoctor}")
	public ResponseEntity<Page<Consulta>> retornarPorNombre(@PathVariable int pag, @PathVariable int size, @PathVariable String nombreDoctor) throws NoExisteException {
		Page<Consulta> listaCons = consultaService.retornarPorNombre(pag, size, nombreDoctor);
		return new ResponseEntity<Page<Consulta>>(listaCons, HttpStatus.OK);
	}
	
	@GetMapping("/retornarPorDetalleConsulta/{pag}/{size}/{detalle}")
	public ResponseEntity<List<Consulta>> retornarConsultaPorDetalleConsulta(@PathVariable int detalle) throws NoExisteException {
		List<Consulta> listaCons = consultaService.retornarConsultaPorDetalleConsulta(detalle);
		return new ResponseEntity<List<Consulta>>(listaCons, HttpStatus.OK);
	}

		
	@PostMapping("/guardarConsultaExamenNativo")
	public ResponseEntity<?> guardarConsultaExamenNativo(@Valid @RequestBody ConsultaExamen consultaExamen) throws NoExisteException {	
		consultaExamenService.guardarNativo(consultaExamen);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);				
	}	
	
	@GetMapping("/listarCEPorIdConsulta/{id}")
	public ResponseEntity<?> listarCEPorIdConsulta(@PathVariable int id) throws NoExisteException  {
			List<ConsultaExamen> lista = consultaExamenService.listarPorIdConsulta(id);
			return new ResponseEntity<List<ConsultaExamen>>(lista, HttpStatus.OK);			
	}	
	
	@GetMapping("/listarCEPorIdConsultaPaginado/{id}/{page}/{size}")
	public ResponseEntity<?> listarCEPorIdConsultaPaginado(@PathVariable int id,
			@PathVariable int page,
			@PathVariable int size ) throws NoExisteException  {
			Page<ConsultaExamen> lista = consultaExamenService.listarPorIdConsultaPaginado(id, page, size);
			return new ResponseEntity<Page<ConsultaExamen>>(lista, HttpStatus.OK);			
	}		
	
	//// VISTA /////
	
	@GetMapping("/view/{page}/{size}")
	public ResponseEntity<List<InfoMedicoView>> vista (@PathVariable int page, @PathVariable int size){
		
		List<InfoMedicoView> lista = consultaExamenService.vista(page, size);
		return new ResponseEntity<List<InfoMedicoView>>(lista, HttpStatus.OK);
	}
	
	

}
