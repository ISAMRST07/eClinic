package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {

}
