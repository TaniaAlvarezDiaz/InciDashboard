package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import model.Incidence;

public interface ModifyIncidence {

	public String modifyInfo(Model model, @PathVariable Long id);
	public String setModifyInfo(Model model, @PathVariable Long id, @ModelAttribute Incidence incidence);
}
