package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.AppointmentRequest;
import mrs.eclinicapi.model.Codebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, String> {
    List<AppointmentRequest> findAppointmentRequestsByClinic_Id(String clinicID);

    Page<AppointmentRequest> findAppointmentRequestsByClinic_Id(String clinicID, Pageable p);

    List<AppointmentRequest> findAppointmentRequestsByPatient_Id(String clinicID);

    Page<AppointmentRequest> findAppointmentRequestsByPatient_Id(String clinicID, Pageable p);

    List<AppointmentRequest> findAppointmentRequestsByDoctor_Id(String doctorID);
}
