package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Incidence;
import model.Incidence.Estado;

@Repository
public interface IncidencesRepository extends CrudRepository<Incidence, Long> {

	/**
	 * Método que encuentra las incidencias que tiene asignadas el personal cuyo
	 * identificador se pasa por parametro
	 * 
	 * @param identificador
	 * @return
	 */
	@Query("SELECT i from Incidence i WHERE i.incidentManagementStaff.identificador = ?1")
	public List<Incidence> findIncidences(String identificador);
	
	/**
	 * Método para actualizar el estado de la incidencia que se pasa por parametro
	 * @param id
	 * @param state
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Incidence i SET i.estado = ?2 WHERE i.id = ?1")
	public void updateStateIncidence(Long id, Estado state);
	
	/**
	 * Método para actualizar los comentarios de la incidencia que se pasa por parametro
	 * @param id
	 * @param state
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Incidence i SET i.comments = ?2 WHERE i.id = ?1")
	public void updateCommentsIncidence(Long id, String comments);
	
	/**
	 * Método para encontrar una incidencia por su id
	 * @param id
	 * @return
	 */
	public Incidence findById(Long id);
}
