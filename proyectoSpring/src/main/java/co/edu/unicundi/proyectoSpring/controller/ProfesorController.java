package co.edu.unicundi.proyectoSpring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;
import co.edu.unicundi.proyectoSpring.entity.Profesor;
import co.edu.unicundi.proyectoSpring.exception.NoExisteException;
import co.edu.unicundi.proyectoSpring.service.interfaz.IProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/profesores")
@Api(tags = "Profesor Controller")
public class ProfesorController {

	@Autowired
	private IProfesorService profesorService;

	@ApiOperation(value = "Método GET", notes = "Método de petición que retorna a todos los profesores existentes de su lista correspondiente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK. El objeto se ha encontrado", response = List.class),
			@ApiResponse(code = 204, message = "No Content. La lista del objeto profesor está vacía"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })
	@GetMapping("/retornar")

	public ResponseEntity<List<Profesor>> retornar() throws NoExisteException {
		List<Profesor> listaProfesor = profesorService.retornar();
		return new ResponseEntity<List<Profesor>>(listaProfesor, HttpStatus.OK);
	}

	@ApiOperation(value = "Método GET por id", notes = "Método de petición que retorna a todos los profesores existentes en la lista filtrado por el parametro 'id'")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El objeto se ha encontrado", response = Profesor.class),
			@ApiResponse(code = 404, message = "Not Found. El objeto profesor no existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@GetMapping("/retornarCedula/{cedula}")
	public ResponseEntity<Object> retornarCedula(@PathVariable int cedula) throws NoExisteException {
		Profesor profe;
		profe = profesorService.retornarCedula(cedula);
		return new ResponseEntity<Object>(profe, HttpStatus.OK);
	}

	@ApiOperation(value = "Método POST", notes = "Método de petición que agrega un profesor nuevo")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created. El objeto se ha agregado correctamente", response = Profesor.class),
			@ApiResponse(code = 400, message = "Bad Request. El cuerpo del objeto ingresado es incorrecto"),
			@ApiResponse(code = 400, message = "Bad Request. El objeto profesor ya existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 415, message = "Unsupported Media Type. El formato del objeto enviado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@PostMapping("agregar")
	public ResponseEntity<Object> agregar(@Valid @RequestBody Profesor profe) throws UniqueException {
		profesorService.agregar(profe);
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	@ApiOperation(value = "Método PUT", notes = "Método de petición que edita un objeto profesor")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El objeto profesor se ha editado correctamente", response = Profesor.class),
			@ApiResponse(code = 400, message = "Bad Request. El cuerpo del objeto ingresado es incorrecto"),
			@ApiResponse(code = 404, message = "Not Found. El objeto profesor no existe"),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 415, message = "Unsupported Media Type. El formato del objeto enviado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@PutMapping("editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Profesor profe) throws NoExisteException, NotFound {
		profesorService.editar(profe);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	@ApiOperation(value = "Método DELETE", notes = "Método de petición que elimita el objeto profesor por el parámetrp'id'")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content. El objeto profesor se ha eliminado correctamente", response = Profesor.class),
			@ApiResponse(code = 405, message = "Method Not Allowed. El método solicitado es incorrecto"),
			@ApiResponse(code = 500, message = "Internal Server Error. Ha ocurrido un error inesperado en el servidor") })

	@DeleteMapping("/eliminar/{cedula}")
	public ResponseEntity<Object> eliminar(@PathVariable(required = true) int cedula) throws NoExisteException {
		profesorService.eliminar(cedula);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	///////////////////////////////// Ejemplo /////////////////////////////

//	@GetMapping("/retornarCedula/{cedula}")
//	public ResponseEntity<Object> retornarId (@PathVariable int cedula) throws NoExisteException {
//		Profesor profe;
//		try {
//			profe = profesorService.retornarId(cedula);
//			return new ResponseEntity<Object>(profe, HttpStatus.OK);
//		} catch (NoExisteException e) {
//			
//			ExceptionResponse exc = new ExceptionResponse("404", "Not Found", e.getMessage(), "/profesores/retornarCedula/{cedula}");
//			
//			e.printStackTrace();
//			return new ResponseEntity<Object>(exc, HttpStatus.NOT_FOUND);
//			}
//		
//	}

}
