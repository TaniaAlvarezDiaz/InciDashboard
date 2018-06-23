package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import model.IncidentManagementStaff;
import repositories.IncidentManagementStaffRepository;

@Service
public class IncidentManagementStaffService implements GetIncidencesOfIMS, SaveIMS {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IncidentManagementStaffRepository imsRepository;

	@Override
	public IncidentManagementStaff getIncidentManagementStaffByIdentificador(String identificador) {
		return imsRepository.findByIdentificador(identificador);
	}

	@Override
	public void saveIncidentManagementStaff(IncidentManagementStaff ims) {
		// Encriptamos contraseña
		ims.setPassword(bCryptPasswordEncoder.encode(ims.getPassword()));
		imsRepository.save(ims);
	}

}
