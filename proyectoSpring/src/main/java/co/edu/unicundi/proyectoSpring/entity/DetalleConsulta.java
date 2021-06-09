package co.edu.unicundi.proyectoSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_consulta")
public class DetalleConsulta {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id 
	private Integer id;
	
	@NotNull(message = "El diagnostico no puede ser nulo")
	@Size(min = 10, max = 100 ,message = "El diagnostico debe contener entre 10 y 100 carateres")
	@Column(name="diagnostico", nullable = false, length = 100)
	private String diagnostico;
	
	@NotNull(message = "El tratamiento no puede ser nulo")
	@Size(min = 10, max = 100 ,message = "El tratamiento debe contener entre 10 y 100 carateres")
	@Column(name="tratamiento", nullable = false, length = 100)
	private String tratamiento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_consulta", nullable = false, foreignKey = @ForeignKey(name="Fk_Consulta"))
	private Consulta consulta;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDiagnostico() {
		return diagnostico;
	}


	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}


	public String getTratamiento() {
		return tratamiento;
	}


	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}


	public Consulta getConsulta() {
		return consulta;
	}


	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

}
