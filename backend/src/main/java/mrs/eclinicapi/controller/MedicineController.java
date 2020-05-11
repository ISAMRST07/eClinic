package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Medicine;
import mrs.eclinicapi.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Medicine>> getAllMedicine() {
        List<Medicine> medicines = service.findAll();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine) {
        Medicine added = service.add(medicine);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medicine> updateMedicine(@RequestBody Medicine medicine) {
        Medicine modified = service.modify(medicine);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable("id") String id) {

        Medicine medicine = service.findOne(id);
        if (medicine == null) {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }

        service.deleteById(id);
        return new ResponseEntity<>("room deleted", HttpStatus.OK);
    }

}
