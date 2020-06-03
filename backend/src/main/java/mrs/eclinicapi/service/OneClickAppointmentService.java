package mrs.eclinicapi.service;

import mrs.eclinicapi.model.OneClickAppointment;
import mrs.eclinicapi.repository.OneClickAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneClickAppointmentService {

    private OneClickAppointmentRepository repository;

    @Autowired
    public OneClickAppointmentService(OneClickAppointmentRepository repository) {
        this.repository = repository;
    }

    public OneClickAppointment save(OneClickAppointment oneClickAppointment) {
        return repository.save(oneClickAppointment);
    }

    public OneClickAppointment delete(String id) {
        OneClickAppointment appointment = repository.findById(id).orElse(null);
        if (appointment == null) return null;
        repository.delete(appointment);
        return appointment;
    }

    public OneClickAppointment modify(String id, OneClickAppointment appointment) {
        OneClickAppointment oneClickAppointment = repository.findById(id).orElse(null);
        if (oneClickAppointment == null) return null;
        if (!oneClickAppointment.getId().equals(appointment.getId())) return null;
        oneClickAppointment.setClinic(appointment.getClinic());
        oneClickAppointment.setClinicRoom(appointment.getClinicRoom());
        oneClickAppointment.setDoctor(appointment.getDoctor());
        oneClickAppointment.setDateTime(appointment.getDateTime());
        oneClickAppointment.setInterventionType(appointment.getInterventionType());
        return repository.save(oneClickAppointment);
    }

    public OneClickAppointment findByID(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<OneClickAppointment> findByClinicID(String clinicID) {
        return repository.findAllByClinic_Id(clinicID);
    }

    public Page<OneClickAppointment> findByClinicIDPaged(String clinicID, int pageNumber, int pageSize) {
        return this.findByClinicIDPaged(clinicID, pageNumber, pageSize, null, false);
    }

    public Page<OneClickAppointment> findByClinicIDPaged(String clinicID, int pageNumber, int pageSize, String sort, boolean desc) {
        Pageable p;
        sort = modifySort(sort);
        if (sort != null) {
            String[] sorts = sort.split(";");
            Sort s;
            if (desc) s = Sort.by(Sort.Direction.DESC, sorts);
            else s = Sort.by(Sort.Direction.ASC, sorts);
            p = PageRequest.of(--pageNumber, pageSize, s);
        } else p = PageRequest.of(--pageNumber, pageSize);
        return repository.findAllByClinic_Id(clinicID, p);
    }

    private String modifySort(String sort) {
        if (sort == null) return null;
        String modified;
        switch (sort) {
            case "typeName":
                modified = "interventionType.name";
                break;
            case "dateTime.start":
                modified = "dateTime.start";
                break;
            case "dateTime.end":
                modified = "dateTime.end";
                break;
            case "doctorName":
                modified = "doctor.user.surname;doctor.user.name";
                break;
            case "clinicRoomName":
                modified = "clinicRoom.name";
                break;
            case "price":
                modified = "interventionType.price";
                break;
            default:
                modified = "id";
        }
        return modified;
    }
}
