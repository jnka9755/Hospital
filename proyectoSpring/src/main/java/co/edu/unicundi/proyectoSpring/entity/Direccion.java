package co.edu.unicundi.proyectoSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "direccion")
public class Direccion {

	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Id 
	private Integer id;
	
	@NotNull(message = "El detalle de direccion no puede ser nulo")
	@Size(min = 2 , max = 20, message ="La direccion detalle no puede ser menor de 3 caracteres ni mayor que 20")
	@Column(name="detalle", nullable = false, length = 20)
	private String detalle;
	
	@NotNull(message = "El barrio no puede ser nulo")
	@Size(min = 2 , max = 20, message ="El barrio no puede ser menor de 3 caracteres ni mayor que 20")
	@Column(name="barrio", nullable = false, length = 20)
	private String barrio;
	
	@NotNull(message = "La ciudad no puede ser nulo")
	@Size(min = 7 , max = 35, message ="La ciudad no puede ser menor de 7 caracteres ni mayor que 35")
	@Column(name = "ciudad", nullable = false)
	private String ciudad;
	
	@NotNull(message = "El pais no puede ser nulo")
	@Size(min = 7 , max = 35, message ="El pais no puede ser menor de 7 caracteres ni mayor que 35")
	@Column(name = "pais", nullable = false)
	private String pais;
	
	@OneToOne
	@MapsId
	@JsonIgnore
	private Medico medico;
	
	/////////////////////////
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}
