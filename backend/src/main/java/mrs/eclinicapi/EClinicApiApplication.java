package mrs.eclinicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EClinicApiApplication {

    public static final String APP_URL = "https://e-clinic-isamrs.herokuapp.com/"; // za development


    public static void main(String[] args) {
        SpringApplication.run(EClinicApiApplication.class);
    }

}
