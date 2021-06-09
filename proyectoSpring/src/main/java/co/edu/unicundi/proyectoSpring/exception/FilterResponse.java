package co.edu.unicundi.proyectoSpring.exception;

import java.util.Iterator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.edu.unicundi.proyectoSpring.dto.ExceptionResponse;
import co.edu.unicundi.proyectoSpring.dto.UniqueException;

@ControllerAdvice
@RestController
public class FilterResponse extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoExisteException.class)
	public ResponseEntity<ExceptionResponse> filtroNoExisteException(NoExisteException ex, WebRequest req) {
		HttpStatus hs = HttpStatus.NOT_FOUND;
		ExceptionResponse exc = new ExceptionResponse(hs.value(), hs.getReasonPhrase(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exc, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UniqueException.class)
	public ResponseEntity<ExceptionResponse> filtroUniqueException(UniqueException ex, WebRequest req) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExceptionResponse exc = new ExceptionResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exc, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ExceptionResponse> filtroNullPointer(UniqueException ex, WebRequest req) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ExceptionResponse exc = new ExceptionResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exc, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		exception.printStackTrace();
		ExceptionResponse exc = new ExceptionResponse(status.value(), status.name(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exc, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		exception.printStackTrace();
		ExceptionResponse exc = new ExceptionResponse(status.value(), status.name(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exc, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		exception.printStackTrace();
		ExceptionResponse exc = new ExceptionResponse(status.value(), status.name(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exc, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		exception.printStackTrace();
		ExceptionResponse exc = new ExceptionResponse(status.value(), status.name(), "El json esta incorrecto",
				request.getDescription(false));
		return new ResponseEntity<Object>(exc, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String e ="";
		
		for(FieldError error: exception.getBindingResult().getFieldErrors()) {
			
			e += error.getField().toUpperCase() + ":" + error.getDefaultMessage()+".";
			
		}
		
		String statuString = status.toString().split("")[0];
		String statusPhraseString = status.getReasonPhrase();
		String mensaje = e;
		String path = request.getDescription(false).replaceAll("uri=", "");

		exception.printStackTrace();
		ExceptionResponse exc = new ExceptionResponse(status.value(), status.name(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exc, HttpStatus.BAD_REQUEST);
	}

}
