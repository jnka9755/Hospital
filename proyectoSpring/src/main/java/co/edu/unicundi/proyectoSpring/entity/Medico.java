package co.edu.unicundi.proyectoSpring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "medico")

public class Medico {
	
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Id 
	private Integer id;
	
	@NotNull(message = "El nombre del médico no puede ser nulo")
	@Size(min = 2 , max = 20, message ="El nombre no puede ser menor de 3 caracteres ni mayor que 20")
	@Column(name="nombre_medico", nullable = false, length = 20)
	private String nombre;
	
	@NotNull(message = "El apellido del médico no puede ser nulo")
	@Size(min = 2 , max = 20, message ="El apellido no puede ser menor de 3 caracteres ni mayor que 20")
	@Column(name="apellido_medico", nullable = false, length = 20)
	private String apellido;
	
	@NotNull(message = "Correo no puede ser nulo")
	@Email(message="Escriba un correo valido")
	@Size(min = 7 , max = 35, message ="El correo no puede ser menor de 7 caracteres ni mayor que 35")
	@Column(name = "correo_medico", nullable = false)
	private String correo;
	
	@NotNull(message="La direccion no puede ser nula")
	@OneToOne(mappedBy= "medico", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
	private Direccion direccion;
	
	@OneToMany(mappedBy= "medico",cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Consulta> consulta;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

}
