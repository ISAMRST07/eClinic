package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {

}
