package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface  ClinicAdministratorRepository extends JpaRepository<ClinicAdministrator, Long> {

}
