package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long>{

}
