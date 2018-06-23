package services;

import java.util.List;

import model.Incidence;

public interface GetIncidences {

	public List<Incidence> findIncidentsOfIMS(String identificador);
	public List<Incidence> findIncidents();
	public Incidence findIncidence(Long id);
}
