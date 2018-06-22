package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para gestionar la pagina de inicio
 * 
 * @author Tania Alvarez Diaz
 *
 */
@Controller
public class HomeController {

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
		//model.addAttribute("agent", new Agent());
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String setUser(@Validated Operator operator, BindingResult result, Model model) {
		signUpFormValidator.validate(operator, result);
		if (result.hasErrors()) {
			return "signup";
		}
		
		operator.setRole(rolesService.getRoles()[0]);
		operatorsService.addOperator(operator);
		securityService.autoLogin(operator.getIdentifier(), operator.getPasswordConfirm());
		return "redirect:home";
	}
}