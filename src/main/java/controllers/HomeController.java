package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.IncidentManagementStaff;
import services.IncidentManagementStaffService;
import validators.SignUpFormValidator;

/**
 * Controlador para gestionar la pagina de inicio
 * 
 * @author Tania Alvarez Diaz
 *
 */
@Controller
public class HomeController {

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@Autowired
	private IncidentManagementStaffService imsService;

	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("ims", new IncidentManagementStaff());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String setUser(@ModelAttribute ("ims") @Validated IncidentManagementStaff ims, BindingResult result, Model model) {
		signUpFormValidator.validate(ims, result);
		if (result.hasErrors()) {
			return "signup";
		}

		imsService.saveIncidentManagementStaff(ims);
		return "redirect:home";
	}
}