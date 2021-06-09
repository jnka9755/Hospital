package co.edu.unicundi.proyectoSpring.dto;

import java.time.LocalDateTime;

public class ExceptionResponse {

	// fechas en base de datos private LocalDateTime timestamp;
	
	private String timestamp;
	private Integer status; //código del error
	private String error; // error del significado del status
	private String message; //mensaje relacionado al error
	private String path; // url donde se genera el error
	
	
	public ExceptionResponse(Integer status, String error, String message, String path) {
		super();
		this.timestamp = LocalDateTime.now().toString();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}
