package validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.IncidentManagementStaff;
import services.IncidentManagementStaffService;

@Component
public class SignUpFormValidator implements Validator {
	
	@Autowired
	private IncidentManagementStaffService imsService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return IncidentManagementStaff.class.equals(aClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		IncidentManagementStaff ims= (IncidentManagementStaff) target;
		
		IncidentManagementStaff imsBD = imsService.getIncidentManagementStaffByIdentificador(ims.getIdentificador());
		
		if (imsBD != null) {
			errors.rejectValue("identificador", "Error.signup.identifier.duplicate");
		}
		if (ims.getIdentificador().length() < 5 || ims.getIdentificador().length() > 24) {
			errors.rejectValue("identificador", "Error.signup.identifier.length");
		}
		if (ims.getNombre().length()< 2 || ims.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.signup.name.length");
		}
		if (ims.getPassword().length() < 5 || ims.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}
		if (!ims.getPasswordConfirm().equals(ims.getPassword())) {
			errors.rejectValue("passwordConfirm","Error.signup.passwordConfirm.coincidence");
		}
	}
}

