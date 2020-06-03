package mrs.eclinicapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mrs.eclinicapi.dto.ClinicSearchRequest;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.InterventionType;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.DoctorService;
import mrs.eclinicapi.service.InterventionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "api/clinic")
@Slf4j
public class ClinicController {

    @Autowired
    private ClinicService service;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private InterventionTypeService interventionTypeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clinic> addClinic(@RequestBody Clinic clinic) {
        Clinic newClinic = service.addClinic(clinic);
        if (newClinic == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        System.out.println("newClinic = " + clinic);
        return new ResponseEntity<>(newClinic, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clinic> modifyClinic(@RequestBody Clinic clinic) {
        Clinic modified = service.modifyClinic(clinic);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Clinic>> getAllClinic() {

        List<Clinic> clinics = service.findAll();

        if (clinics == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Clinic c : clinics) {
            System.out.println("getall clinics = " + c);
        }
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @GetMapping(path = "/{pageNumber}/{pageSize}/{sort}/{desc}")
    public ResponseEntity<PagedResponse> getPagedClinics(@PathVariable int pageNumber,
                                                         @PathVariable int pageSize,
                                                         @PathVariable String sort,
                                                         @PathVariable String desc,
                                                         HttpServletRequest request) {
        log.info("URL123");
        log.info(request.getLocalAddr());
        log.info(request.getLocalName());
        PagedResponse response;
        if (pageSize < 1) {
            List<Clinic> allClinics = service.findAll();
            response = new PagedResponse(allClinics, allClinics.size());

        } else {
            Page<Clinic> clinicPage;
            if (sort.equals("undefined"))
                clinicPage = service.findPaged(pageNumber, pageSize);
            else {
                clinicPage = service.findPaged(pageNumber, pageSize, sort, desc.equals("true"));
            }
            response = new PagedResponse(clinicPage.getContent(), clinicPage.getTotalElements());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Clinic> getClinicByUserID(@PathVariable("id") String userID) {
        System.out.println("SAD CU DA TE NADJEM");
        Clinic clinic = service.findByUser(userID);

        if (clinic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Clinic> getOneClinic(@PathVariable("id") String id) {
        System.out.println("getOneClinic id = " + id);

        Clinic clinic = service.findOne(id);
        System.out.println("returned to controller = " + clinic);
        if (clinic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("get one  clinics = " + clinic);
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClinic(@PathVariable("id") String id) {
        Clinic clinic = service.findOne(id);
        if (clinic == null) {
            return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted clinic", HttpStatus.OK);
    }

    @PostMapping(path = "/search/{pageNumber}/{pageSize}/{sort}/{desc}")
    public ResponseEntity<PagedResponse> searchClinics(@RequestBody ClinicSearchRequest searchRequest,
                                                       @PathVariable int pageNumber,
                                                       @PathVariable int pageSize,
                                                       @PathVariable String sort,
                                                       @PathVariable String desc) {
        LocalDate date = searchRequest.getDate();
        InterventionType type = interventionTypeService.findOne(searchRequest.getInterventionType());
        String searchQuery = searchRequest.getSearchQuery();
        if (searchQuery == null) searchQuery = "";
        if (type == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        PagedResponse response;
        Page<Clinic> clinicPage;
        if (sort.equals("undefined"))
            clinicPage = service.search(searchQuery, date, type, pageNumber, pageSize);
        else {
            clinicPage = service.search(searchQuery, date, type, pageNumber, pageSize, sort, desc.equals("true"));
        }
        response = new PagedResponse(clinicPage.getContent(), clinicPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @AllArgsConstructor
    static class PagedResponse {
        public List<Clinic> clinics;
        public long totalLength;
    }

}
