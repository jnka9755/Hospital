package co.edu.unicundi.proyectoSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "profesor")
public class Profesor {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id 
	private Integer id;    
	
	@NotNull(message = "Nombre no puede ser nulo")
	@Size(min = 3 , max = 20, message ="El nombre no puede ser menor de 3 caracteres ni mayor que 20")
	@Column (name = "nombre", length = 20, nullable = false)
	private String nombre;

	@NotNull(message = "Apellido no puede ser nulo")
	@Size(min = 3 , max = 20, message ="El apellido no puede ser menor de 3 caracteres ni mayor que 20")
	@Column (name = "apellido", length = 20, nullable = false)
	private String apellido;
	
	@NotNull(message = "Cedula no puede ser nulo")
	@Size(min = 7 , max = 10, message ="La cédula no puede ser menor de 7 números ni mayor que 10")
	@Column (name = "cedula",nullable = false)
	private Integer cedula;
	
	@NotNull(message = "Edad no puede ser nulo")
	@Size(min = 18 , max = 60, message ="La edad no puede ser menor de 18 años")
	@Column (name = "edad", nullable = false)
	private Integer edad;
	
	@NotNull(message = "Edad no puede ser nulo")
	@Email(message="Escriba un correo valido")
	@Size(min = 7 , max = 35, message ="El correo no puede ser menor de 7 caracteres ni mayor que 35")
	@Column(name = "correo", nullable = false)
	private String correo;
	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
	
}
