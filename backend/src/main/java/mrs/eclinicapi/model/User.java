package mrs.eclinicapi.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.DTO.ClinicAdministratorDto;
import mrs.eclinicapi.generator.IdGenerator;
import mrs.eclinicapi.model.enums.UserType;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User implements UserDetails {

    @Id
    @Column(length=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "u_seq")
    @GenericGenerator(name = "u_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "U")})
    private String id;

    @NotBlank
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;
    private String name;
    private String surname;
    private UserType type;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String personalID;
    private Timestamp lastPasswordResetDate;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password, String name, String surname, UserType type) {
        this.email = email;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.type = type;
    }

    public User(ClinicAdministratorDto clinicAdminDto){
        this.password = clinicAdminDto.getPassword();
        this.name = clinicAdminDto.getName();
        this.surname = clinicAdminDto.getSurname();
        this.type = UserType.clinicAdministrator;
        this.email = clinicAdminDto.getEmail();
        this.city = clinicAdminDto.getCity();
        this.country = clinicAdminDto.getCountry();
        this.personalID = clinicAdminDto.getPersonalID();
        this.address = clinicAdminDto.getAddress();
    }

    public User(String email, String password, String name, UserType type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }
    public User(String email, String password, String name, String surname,
                UserType type, String phoneNumber, String address,
                String city, String country, String personalID) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.personalID = personalID;
    }

    public void setPassword(String password) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        this.setLastPasswordResetDate(now);
        this.password = password;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public String toString() {
        return "User [username=" + email + ", password=" + password + ", name=" + name + ", type=" + type + ", id="
                + id + "]";
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.type.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.type != UserType.unregisteredUser;
    }
}
