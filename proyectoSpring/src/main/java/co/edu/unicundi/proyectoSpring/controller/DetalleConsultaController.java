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
import co.edu.unicundi.proyectoSpring.entity.Consulta;
import co.edu.unicundi.proyectoSpring.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.service.interfaz.IDetalleConsulta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/detalleConsultas")
@Api(tags = "Detalle Consulta Controller")
public class DetalleConsultaController {
	
	@Autowired
	private IDetalleConsulta detalleService;
	
	@ApiOperation(value = "Método GET", notes = "Método de petición que retorna todas las consultas existentes de su lista correspondiente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK. El objeto se ha encontrado", response = List.class),
			@ApiResponse(code = 204, message = "No Content. La lista del objeto consulta está vacía"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })
	
	@GetMapping("/retornar")
	public ResponseEntity<List<DetalleConsulta>> retornar() throws NoExisteException {
		List<DetalleConsulta> listaConsulta = detalleService.retornar();
		return new ResponseEntity<List<DetalleConsulta>>(listaConsulta, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Método GET por id", notes = "Método de petición que retorna a todos los detalles existentes en la lista filtrado por el parametro 'id'")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El objeto se ha encontrado", response = Consulta.class),
			@ApiResponse(code = 404, message = "Not Found. El objeto profesor no existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@GetMapping("/retornarConsulta/{id}")
	public ResponseEntity<Object> retornarDetalleConsulta(@PathVariable int id) throws NoExisteException {
		DetalleConsulta cons;
		cons = detalleService.retornarDetalleConsulta(id);
		return new ResponseEntity<Object>(cons, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Método POST", notes = "Método de petición que agrega un detalle nueva")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created. El objeto se ha agregado correctamente", response = Consulta.class),
			@ApiResponse(code = 400, message = "Bad Request. El cuerpo del objeto ingresado es incorrecto"),
			@ApiResponse(code = 400, message = "Bad Request. El objeto profesor ya existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 415, message = "Unsupported Media Type. El formato del objeto enviado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@PostMapping("/agregar")
	public ResponseEntity<Object> agregar(@Valid @RequestBody DetalleConsulta det, int idConsulta) throws NoExisteException {
		detalleService.agregar(det, idConsulta);
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
	public ResponseEntity<Object> editar(@Valid @RequestBody DetalleConsulta cons) throws NoExisteException, NotFound {
		detalleService.editar(cons);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}
	
	@ApiOperation(value = "Método DELETE", notes = "Método de petición que elimita el objeto consulta por el parámetrp'id'")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content. El objeto consulta se ha eliminado correctamente", response = Consulta.class),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable(required = true) int id) throws NoExisteException {
		detalleService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/retornarDiagnostico/{diagnostico}")
	public ResponseEntity<List<DetalleConsulta>> retornarPorDiagnostico(@PathVariable String diagnostico) throws NoExisteException {
		List<DetalleConsulta> listaCons = detalleService.retornarPorDiagnostico(diagnostico);
		return new ResponseEntity<List<DetalleConsulta>>(listaCons, HttpStatus.OK);
	}
	
	@GetMapping("/retornarDiagnosticoAndTratatamiento/{diagnostico}/{tratamiento}")
	public ResponseEntity<List<DetalleConsulta>> retornarPorDiagnosticoTratamiento(@PathVariable String diagnostico, @PathVariable String tratamiento) throws NoExisteException {
		List<DetalleConsulta> listaCons = detalleService.retornarPorDiagnosticoTratamiento(diagnostico, tratamiento);
		return new ResponseEntity<List<DetalleConsulta>>(listaCons, HttpStatus.OK);
	}
	
	@GetMapping("/retornarOrdenadoPorDiagnostico/{diagnostico}")
	public ResponseEntity<List<DetalleConsulta>> retornarOrdenadoPorDiagnostico(@PathVariable String diagnostico) throws NoExisteException {
		List<DetalleConsulta> listaCons = detalleService.retornarOrdenarPorDiagnostico(diagnostico);
		return new ResponseEntity<List<DetalleConsulta>>(listaCons, HttpStatus.OK);
	}
	
	@GetMapping("/retornarPorNombreDoctor/{nombreDoctor}")
	public ResponseEntity <List<DetalleConsulta>> retornarPorNombreDoctor(@PathVariable String nombreDoctor) throws NoExisteException {
		List<DetalleConsulta> Cons = detalleService.retornarPorNombreDoctor(nombreDoctor);
		return new ResponseEntity<List<DetalleConsulta>>(Cons, HttpStatus.OK);
	}
	
	@GetMapping("/retornarPorTratamiento/{pag}/{size}/{tratamiento}")
	public ResponseEntity<Page<DetalleConsulta>> retornarPorTratamiento(@PathVariable int pag, @PathVariable int size, @PathVariable String tratamiento) throws NoExisteException {
		Page<DetalleConsulta> listaCons = detalleService.retornarPorTratamiento(pag, size, tratamiento);
		return new ResponseEntity<Page<DetalleConsulta>>(listaCons, HttpStatus.OK);
	}

}
