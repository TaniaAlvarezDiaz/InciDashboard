package controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Incidence;
import model.Incidence.Estado;
import services.IncidencesService;

@Controller
public class IncidencesController {

	@Autowired
	IncidencesService incidenceService;

	@RequestMapping("/currentIncidents")
	public String listCurrentIncidences(Model model) {
		List<Incidence> incidencias = incidenceService.findIncidents();
		model.addAttribute("incidencesList", incidencias);
		return "/incidences/listIncidences";
	}

	@RequestMapping("/assignedIncidents")
	public String listAssignedIncidences(Model model, Principal principal) {
		List<Incidence> incidencias = incidenceService.findIncidentsOfIMS(principal.getName());
		model.addAttribute("incidencesList", incidencias);
		return "/incidences/listAssignedIncidences";
	}

	@RequestMapping("/incidence/detailsIncidence/{id}")
	public String getIncidenceInfo(Model model, @PathVariable Long id) {

		model.addAttribute("incidence", incidenceService.findIncidence(id));

		return "/incidences/detailsIncidence";
	}

	@RequestMapping("/incidence/modify/{id}")
	public String modifyInfo(Model model, @PathVariable Long id) {

		model.addAttribute("incidence", incidenceService.findIncidence(id));
		model.addAttribute("listStates", Estado.values());

		return "/incidences/modifyIncidence";
	}

	@RequestMapping(value = "/incidence/modify/{id}", method = RequestMethod.POST)
	public String setModifyInfo(Model model, @PathVariable Long id, @ModelAttribute Incidence incidence) {
		//Si queremos hacer todo en un paso
		//incidenceService.modificarIncidencia(incidence);
		
		// Modificar estado
		incidenceService.modificarEstado(id, incidence.getEstado());
		// Modificar comentarios
		incidenceService.modificarComentarios(id, incidence.getComments());

		return "redirect:/incidence/detailsIncidence/" + id;
	}

	@RequestMapping("/verMapa/{latitud}/{longitud}")
	public String getMap(Model model, @PathVariable double latitud, @PathVariable double longitud) {
		model.addAttribute("latitud", latitud);
		model.addAttribute("longitud", longitud);
		return "incidences/map";
	}

}
