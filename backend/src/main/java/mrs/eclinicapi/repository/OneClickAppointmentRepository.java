package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.OneClickAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneClickAppointmentRepository extends JpaRepository<OneClickAppointment, String> {
    List<OneClickAppointment> findAllByClinic_Id(String clinicID);

    Page<OneClickAppointment> findAllByClinic_Id(String clinicID, Pageable p);
}
