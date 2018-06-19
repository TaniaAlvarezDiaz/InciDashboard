package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.Incidence;

@Entity
@Table(name = "IncidentManagementStaff")
public class IncidentManagementStaff implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	@Column(unique = true)
	private String identificador; // Es unico y es el nombre de usuario
	private String password;
	@Transient
	private String passwordConfirm;

	@OneToMany(mappedBy = "incidentManagementStaff")
	private Set<Incidence> incidences = new HashSet<Incidence>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Incidence> getIncidences() {
		return incidences;
	}

	public void setIncidences(Set<Incidence> incidences) {
		this.incidences = incidences;
	}

	public IncidentManagementStaff() {

	}

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param email
	 * @param identificador
	 */
	public IncidentManagementStaff(String nombre, String email, String identificador, String password) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.identificador = identificador;
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncidentManagementStaff other = (IncidentManagementStaff) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IncidentManagementStaff [nombre=" + nombre + ", email=" + email + ", identificador=" + identificador
				+ "]";
	}

}
