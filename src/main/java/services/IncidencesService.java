package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Incidence;
import model.Incidence.Estado;
import repositories.IncidencesRepository;

/**
 * Servicio de las incidencias
 * 
 * @author Tania Álvarez Díaz
 *
 */
@Service
public class IncidencesService implements GetIncidences, UpdateIncidence{

	@Autowired
	IncidencesRepository incidencesRepository;

	/**
	 * Método para encontrar las incidencias que tiene asignadas un operador
	 * 
	 * @param identificador
	 * @return
	 */
	@Override
	public List<Incidence> findIncidentsOfIMS(String identificador) {
		return incidencesRepository.findIncidences(identificador);
	}

	/**
	 * Método para modificar el estado de la incidencia que se pasa por parametro
	 * 
	 * @param id
	 * @param state
	 */
	@Override
	public void modificarEstado(Long id, Estado state) {
		incidencesRepository.updateStateIncidence(id, state);
	}

	/**
	 * Método para encontrar todas las incidencias
	 * 
	 * @return
	 */
	@Override
	public List<Incidence> findIncidents() {
		return (List<Incidence>) incidencesRepository.findAll();
	}

	/**
	 * Método para encontrar una incidencia por su id
	 */
	@Override
	public Incidence findIncidence(Long id) {
		return incidencesRepository.findById(id);
	}

	/**
	 * Método para modificar los comentarios de la incidencia
	 * 
	 */
	@Override
	public void modificarComentarios(Long id, String comments) {
		incidencesRepository.updateCommentsIncidence(id, comments);
	}

	@Override
	public void modificarIncidencia(Incidence i) {
		incidencesRepository.save(i);
	}
	
}
