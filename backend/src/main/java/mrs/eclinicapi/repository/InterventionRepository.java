package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long>{

}
