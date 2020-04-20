package mrs.eclinicapi.model; /***********************************************************************
 * Module:  User.java
 * Author:  Manojlović
 * Purpose: Defines the Class User
 ***********************************************************************/


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.enums.UserType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    private String username;
    private String password;
    private String name;
    private UserType type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public User(String username, String password, String name) {
    	this.username = username;
    	this.password = password;
    	this.name = name;
    }

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", type=" + type + ", id="
				+ id + "]";
	}
    
    
}
