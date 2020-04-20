package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Codebook;

public interface CodebookRepository extends JpaRepository<Codebook, Long>{

}
