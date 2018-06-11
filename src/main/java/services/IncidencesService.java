package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Incidence;
import repositories.IncidencesRepository;

/**
 * Servicio de las incidencias
 * 
 * @author Tania Álvarez Díaz
 *
 */
@Service
public class IncidencesService {

    @Autowired
    IncidencesRepository incidencesRepository;

    public List<Incidence> getIncidences() {
	List<Incidence> incidencias = new ArrayList<Incidence>();
	incidencesRepository.findAll().forEach(incidencias::add);
	return incidencias;
    }

    public Incidence getIncidence(Long id) {
	return incidencesRepository.findOne(id);
    }

    public void modifyState(Incidence inci) {
	incidencesRepository.save(inci);
    }
}
