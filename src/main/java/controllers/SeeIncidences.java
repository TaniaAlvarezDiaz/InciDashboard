package controllers;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface SeeIncidences {

	public String listCurrentIncidences(Model model);
	public String listAssignedIncidences(Model model, Principal principal);
	public String getIncidenceInfo(Model model, @PathVariable Long id);
	public String getMap(Model model, @PathVariable double latitud, @PathVariable double longitud);
}
