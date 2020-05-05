package mrs.eclinicapi;

import mrs.eclinicapi.model.ClinicalCentarAdministrator;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.model.enums.UserType;
import mrs.eclinicapi.repository.ClinicalCentarAdministratorRepository;
import mrs.eclinicapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class EClinicApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EClinicApiApplication.class, args);
    }
}
