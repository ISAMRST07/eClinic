package mrs.eclinicapi.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import mrs.eclinicapi.model.enums.UserType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id
    @Column(length=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "u_seq")
    @GenericGenerator(name = "u_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "U")})
    private String id;

    private String username;
    private String password;
    private String name;
    private String surname;
    private UserType type;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public User(String username, String password, String name, String surname, UserType type) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.type = type;
    }

    public User(String username, String password, String name, UserType type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", name=" + name + ", type=" + type + ", id="
                + id + "]";
    }


}
