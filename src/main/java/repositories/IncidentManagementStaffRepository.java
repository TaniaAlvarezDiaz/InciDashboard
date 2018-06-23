package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.IncidentManagementStaff;

@Repository
public interface IncidentManagementStaffRepository extends CrudRepository<IncidentManagementStaff, Long> {

	IncidentManagementStaff findByIdentificador(String identificador);

}
