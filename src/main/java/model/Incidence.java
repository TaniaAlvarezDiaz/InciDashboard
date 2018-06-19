package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa una incidencia
 * 
 * @author Tania Álvarez Díaz
 *
 */
@Entity
@Table(name = "Incidence")
public class Incidence implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Estado {
		ABIERTA, ENPROCESO, CERRADA, ANULADA
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre; // nombre de la incidencia
	private String descripcion; // descripicion de la incidencia
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent; // agente que envio la incidencia
	@Embedded
	private Location localizacion; // se obtiene, si es posible, del agente

	@ElementCollection
	@CollectionTable(name = "incidence_tags", joinColumns = @JoinColumn(name = "incidence_ID"))
	private Set<String> tags = new HashSet<String>();

	@ElementCollection
	@CollectionTable(name = "incidence_properties", joinColumns = @JoinColumn(name = "incidence_ID"))
	private Set<Property> properties = new HashSet<Property>();

	@ManyToOne
	@JoinColumn(name = "incidentManagementStaff_id")
	private IncidentManagementStaff incidentManagementStaff; // personal de gestion de incidencias al que se le asigno
																// dicha incidencia

	private String comments; // comentarios del personal de gestion de incidencias sobre dicha incidencia
	private String fechaCaducidad; // fecha en la que expira la incidencia
	private boolean peligrosa; // indica si la incidencia tiene propiedades peligrosas

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Location getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Location localizacion) {
		this.localizacion = localizacion;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	public IncidentManagementStaff getIncidentManagementStaff() {
		return incidentManagementStaff;
	}

	public void setIncidentManagementStaff(IncidentManagementStaff incidentManagementStaff) {
		this.incidentManagementStaff = incidentManagementStaff;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public boolean isPeligrosa() {
		return peligrosa;
	}

	public void setPeligrosa(boolean peligrosa) {
		this.peligrosa = peligrosa;
	}

	public Incidence() {
	}

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param estado
	 * @param agent
	 * @param tags
	 * @param properties
	 * @param incidentManagementStaff
	 * @param comments
	 * @param fechaCaducidad
	 * @param peligrosa
	 */
	public Incidence(String nombre, String descripcion, Estado estado, Agent agent, Set<String> tags,
			Set<model.Property> properties, IncidentManagementStaff incidentManagementStaff, String comments,
			String fechaCaducidad, boolean peligrosa) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.agent = agent;
		this.localizacion = agent.getLocation(); // Se obtiene del agente
		this.tags = tags;
		this.properties = properties;
		this.incidentManagementStaff = incidentManagementStaff;
		this.comments = comments;
		this.fechaCaducidad = fechaCaducidad;
		this.peligrosa = peligrosa;
	}

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param estado
	 * @param agent
	 * @param tags
	 * @param properties
	 * @param fechaCaducidad
	 */
	public Incidence(String nombre, String descripcion, Estado estado, Agent agent, Set<String> tags,
			Set<model.Property> properties, String fechaCaducidad) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.agent = agent;
		this.localizacion = agent.getLocation(); // Se obtiene del agente
		this.tags = tags;
		this.properties = properties;
		this.fechaCaducidad = fechaCaducidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Incidence other = (Incidence) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Incidence [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", agent=" + agent.toString() + ", localizacion=" + localizacion.toString() + ", tags=" + tags
				+ ", properties=" + properties + ", incidentManagementStaff=" + incidentManagementStaff.toString()
				+ ", comments=" + comments + ", fechaCaducidad=" + fechaCaducidad + ", peligrosa=" + peligrosa + "]";
	}

}
