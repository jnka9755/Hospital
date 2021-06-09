package co.edu.unicundi.proyectoSpring.controller;

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
import co.edu.unicundi.proyectoSpring.entity.Medico;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.service.interfaz.IMedicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/medicos")
@Api(tags = "Medico Controller")
public class MedicoController {

	@Autowired
	private IMedicoService medicoService;
	
	@ApiOperation(value = "Método GET por id", notes = "Método de petición que retorna a todos los profesores existentes en la lista filtrado por el parametro 'id'")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El objeto se ha encontrado", response = Medico.class),
			@ApiResponse(code = 404, message = "Not Found. El objeto profesor no existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@GetMapping("/retornarPorId/{id}")
	public ResponseEntity<Object> retornarById(@PathVariable int id) throws NoExisteException {
		Medico med;
		med = medicoService.retornarById(id);
		return new ResponseEntity<Object>(med, HttpStatus.OK);
	}
	
	@GetMapping("/retornarPagina/{pag}/{size}")
	public ResponseEntity<Page<Medico>> retornarPagina(@PathVariable int pag, @PathVariable int size) throws NoExisteException {
		Page<Medico> listaCons = medicoService.paginar(pag, size);
		return new ResponseEntity<Page<Medico>>(listaCons, HttpStatus.OK);
	}
	
	@GetMapping("/retornarDoctor/{pag}/{size}/{nombre}")
	public ResponseEntity<Page<Medico>> retornarPorNombre(@PathVariable int pag, @PathVariable int size, @PathVariable String nombre) throws NoExisteException {
		Page<Medico> listaMed = medicoService.retornarPorNombre(pag, size, nombre);
		return new ResponseEntity<Page<Medico>>(listaMed, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Método POST", notes = "Método de petición que agrega una consulta nueva")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created. El objeto se ha agregado correctamente", response = Medico.class),
			@ApiResponse(code = 400, message = "Bad Request. El cuerpo del objeto ingresado es incorrecto"),
			@ApiResponse(code = 400, message = "Bad Request. El objeto profesor ya existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 415, message = "Unsupported Media Type. El formato del objeto enviado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@PostMapping("agregar")
	public ResponseEntity<Object> agregar(@Valid @RequestBody Medico medico) throws UniqueException, NoExisteException {
		medicoService.agregar(medico);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Método PUT", notes = "Método de petición que edita un objeto consulta")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El objeto consulta se ha editado correctamente", response = Medico.class),
			@ApiResponse(code = 400, message = "Bad Request. El cuerpo del objeto ingresado es incorrecto"),
			@ApiResponse(code = 404, message = "Not Found. El objeto profesor no existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 415, message = "Unsupported Media Type. El formato del objeto enviado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@PutMapping("editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody  Medico medico) throws NoExisteException, NotFound {
		medicoService.editar(medico);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Método DELETE", notes = "Método de petición que elimita el objeto consulta por el parámetrp'id'")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content. El objeto consulta se ha eliminado correctamente", response = Medico.class),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable(required = true) int id) throws NoExisteException {
		medicoService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/retornarApellido/{pag}/{size}/{apellido}")
	public ResponseEntity<Page<Medico>> retornarPorApellido(@PathVariable int pag, @PathVariable int size, @PathVariable String apellido) throws NoExisteException {
		Page<Medico> listaMed = medicoService.retornarPorApellido(pag, size, apellido);
		return new ResponseEntity<Page<Medico>>(listaMed, HttpStatus.OK);
	}
	
	@GetMapping("/retornarBarrio/{pag}/{size}/{barrio}")
	public ResponseEntity<Page<Medico>> retornarPorBarrio(@PathVariable int pag, @PathVariable int size, @PathVariable String barrio) throws NoExisteException {
		Page<Medico> listaMed = medicoService.retornarBarrio(pag, size, barrio);
		return new ResponseEntity<Page<Medico>>(listaMed, HttpStatus.OK);
	}
	
	@GetMapping("/retornarCiudad/{pag}/{size}/{ciudad}")
	public ResponseEntity<Page<Medico>> retornarPorCiudad(@PathVariable int pag, @PathVariable int size, @PathVariable String ciudad) throws NoExisteException {
		Page<Medico> listaMed = medicoService.retornarCiudad(pag, size, ciudad);
		return new ResponseEntity<Page<Medico>>(listaMed, HttpStatus.OK);
	}
	
	@GetMapping("/retornarPais/{pag}/{size}/{pais}")
	public ResponseEntity<Page<Medico>> retornarPorPais(@PathVariable int pag, @PathVariable int size, @PathVariable String pais) throws NoExisteException {
		Page<Medico> listaMed = medicoService.retornarPais(pag, size, pais);
		return new ResponseEntity<Page<Medico>>(listaMed, HttpStatus.OK);
	}
	
	@GetMapping("/retornarDetalle/{pag}/{size}/{detalle}")
	public ResponseEntity<Page<Medico>> retornarPorDetalle(@PathVariable int pag, @PathVariable int size, @PathVariable String detalle) throws NoExisteException {
		Page<Medico> listaMed = medicoService.retornarDetalle(pag, size, detalle);
		return new ResponseEntity<Page<Medico>>(listaMed, HttpStatus.OK);
	}
	
}
