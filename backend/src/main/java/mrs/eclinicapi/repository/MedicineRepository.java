package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long>{

}
