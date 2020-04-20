package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{

}
