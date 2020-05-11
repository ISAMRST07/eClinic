package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.InterventionType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterventionTypeRepository extends JpaRepository<InterventionType, String> {

	@Query("select i from InterventionType i where i.clinic.id = ?1")
	List<InterventionType> getClinicInterventionType(String id);
}
