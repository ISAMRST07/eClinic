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
//    @Query(value = "select distinct c from Clinic c join c.doctors d join d.workingCalendar w left join w.vacations v join d.specialties as s where :specialty = s and (v.start is null or :date not between v.start and v.end)", nativeQuery = true)
//    Page<Clinic> search(@Param("date") LocalDate date, @Param("specialty") InterventionType type, Pageable pageable);
//
////    and " +
////            "(:date not between vacation.start and vacation.end)")
//@Query(value = "select distinct c from Clinic c join c.doctors d join d.workingCalendar w left join w.vacations v join d.specialties as s where :specialty = s and (v.start is null or :date not between v.start and v.end)", nativeQuery = true)
//    List<Clinic> search(@Param("date") LocalDate date, @Param("specialty") InterventionType type);
}

