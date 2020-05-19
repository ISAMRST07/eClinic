package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.InterventionType;
import mrs.eclinicapi.model.enums.Weekday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, String> {
    @Query("select c from Clinic c " +
            "join c.doctors as doctor " +
            "join doctor.specialties as s join doctor.workingCalendar as wc " +
            "join wc.vacations as vacation " +
            "where :specialty = s and" +
            ":date not between vacation.start and vacation.end")
    Page<Clinic> search(@Param("date") LocalDate date, @Param("specialty") InterventionType type, Pageable pageable);

//    and " +
//            "(:date not between vacation.start and vacation.end)")
@Query("select c from Clinic c " +
        "join c.doctors as doctor " +
        "join doctor.specialties as s join doctor.workingCalendar as wc " +
        "join wc.vacations as vacation " +
        "where :specialty = s and" +
        ":date not between vacation.start and vacation.end")
    List<Clinic> search(@Param("date") LocalDate date, @Param("specialty") InterventionType type);
}

