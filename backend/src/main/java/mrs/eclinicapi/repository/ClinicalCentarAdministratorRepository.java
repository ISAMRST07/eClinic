package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.ClinicalCentarAdministrator;


public interface ClinicalCentarAdministratorRepository 
					extends JpaRepository<ClinicalCentarAdministrator, Long> {
}
