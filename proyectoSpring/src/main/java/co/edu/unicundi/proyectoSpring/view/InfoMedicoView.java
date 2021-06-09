package co.edu.unicundi.proyectoSpring.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info_medico_view")
public class InfoMedicoView {
	
	@Id
	private Integer id;
	private String nombre_medico;
	private String apellido_medico;
	private String ciudad;
	private String barrio;
	private String detalle;
	

	public String getNombre_medico() {
		return nombre_medico;
	}
	public void setNombre_medico(String nombre_medico) {
		this.nombre_medico = nombre_medico;
	}
	public String getApellido_medico() {
		return apellido_medico;
	}
	public void setApellido_medico(String apellido_medico) {
		this.apellido_medico = apellido_medico;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
}
