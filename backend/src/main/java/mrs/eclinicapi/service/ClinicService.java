package mrs.eclinicapi.service;


import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.repository.ClinicRepository;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;

    public Clinic addClinic(Clinic newClinic){
        if(this.findOne(newClinic.getId()) != null) return null;
        return clinicRepository.save(newClinic);
    }

    public Clinic findOne(Long id) {
		return clinicRepository.findById(id).orElseGet(null);
	}

	public Clinic modifyClinic(Long id, Clinic clinic) {
        Clinic found = this.findOne(id);
        if (found == null) {
            return null;
        }
        found.setName(clinic.getName());
        found.setAddress(clinic.getAddress());
        found.setDescription(clinic.getDescription());
        found.setCoordinates(clinic.getCoordinates());
        clinicRepository.save(found);
        return found;
    }

    public List<Clinic> findAll() {
		return clinicRepository.findAll();
	}

    public void deleteById(Long id) {
    	clinicRepository.deleteById(id);
    }

    public void deleteAll() {
    	clinicRepository.deleteAll();
    }

    public void deleteOne(Clinic clinicToDelete) {
    	clinicRepository.delete(clinicToDelete);
    }
}
