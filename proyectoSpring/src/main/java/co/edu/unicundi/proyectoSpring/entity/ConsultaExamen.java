package co.edu.unicundi.proyectoSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "consulta_examen")
@IdClass(ConsultaExamenPk.class)
public class ConsultaExamen {
	
	@Id
	private Consulta consulta;
	
	@Id
	private Examen examen;
	
	@Column(name = "infoAdicional")
	private String infoAdicional;
	
	

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	
	

}
