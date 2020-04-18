package mrs.eclinicapi.service;


import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.repository.ClinicRepository;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClinicService {

	private ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Transactional
    public Clinic addClinic(Clinic newClinic){
        return clinicRepository.save(newClinic);
    }

}