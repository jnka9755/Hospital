package co.edu.unicundi.proyectoSpring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Etiqueta para lanzar el codigo de excepcion
@ResponseStatus(HttpStatus.NOT_FOUND)

public class NoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoExisteException(String message) {
		super(message);
	}
}
