package mrs.eclinicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EClinicApiApplication {

//    public static String appUrl = "https://e-clinic-isamrs.herokuapp.com"; // za deployment
    public static String appUrl = "http://localhost"; // za development


    public static void main(String[] args) {
        SpringApplication.run(EClinicApiApplication.class);
    }

}
