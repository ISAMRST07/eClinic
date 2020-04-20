package mrs.eclinicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping()
    public User addUser(@RequestParam String username, 
			    		@RequestParam String password,
			    		@RequestParam String name) {
		User newUser = new User(username, password, name);
	    service.addUser(newUser);
	    System.out.println("newUser = " + newUser);
	    return newUser;
	}
	
	@GetMapping(value ="/{id}")
	public User getClinic(@PathVariable("id") Long id) {

		User user = service.findOne(id);

		if (user == null) {
			return null;
		}
		System.out.println("found user with id = " + id + " " + user);
		return user;
	}
	
	@GetMapping(value = "/getAll")
   	public ResponseEntity<List<User>> getAllUsers() {

   		List<User> users = service.findAll();

   		if (users == null) {
   			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		}
   		for(User u : users) {
   	   		System.out.println("getall user = " + u);
   		}
   		return new ResponseEntity<>(users, HttpStatus.OK);
   	}
}
