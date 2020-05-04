package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.User;
import mrs.eclinicapi.model.enums.UserType;
import mrs.eclinicapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping()
    public User addUser(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String name,
                        @RequestParam int type) {
        User newUser = new User(username, password, name, UserType.fromInteger(type));
        service.addUser(newUser);
        System.out.println("newUser = " + newUser);
        return newUser;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {

        User user = service.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("found user with id = " + id + " " + user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = service.findAll();

        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (User u : users) {
            System.out.println("getall user = " + u);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        User user = service.findOne(id);
        if (user == null) {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
        System.out.println("foudn user with id = " + id + " " + id);
        service.deleteById(id);
        return new ResponseEntity<>("deleted user", HttpStatus.OK);
    }


    @RequestMapping(path = "/updateUsername")
    public ResponseEntity<String> updateUsername(@RequestParam String id,
                                                 @RequestParam String newUsername) {
        User user = service.findOne(id);
        if (user == null) {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
        List<User> allUsers = service.findAll();
        for (User u : allUsers) {
            System.out.println("User u = " + u);
            if (u.getUsername().toUpperCase().equals(newUsername.toUpperCase())) {
                return new ResponseEntity<>("username already exists", HttpStatus.BAD_REQUEST);
            }
        }
        System.out.println("foudn user with id = " + id);
        service.updateUsername(id, newUsername);
        return new ResponseEntity<>("updated user username", HttpStatus.OK);
    }

    @RequestMapping(path = "/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam String id,
                                                 @RequestParam String newPassword) {
        User user = service.findOne(id);
        if (user == null) {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }

        System.out.println("foudn user with id = " + id);
        service.updatePassword(id, newPassword);
        return new ResponseEntity<>("updated user password", HttpStatus.OK);
    }

    @RequestMapping(path = "/updateName")
    public ResponseEntity<String> updateName(@RequestParam String id,
                                             @RequestParam String newName) {
        User user = service.findOne(id);
        if (user == null) {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }

        System.out.println("foudn user with id = " + id);
        service.updateName(id, newName);
        return new ResponseEntity<>("updated user password", HttpStatus.OK);
    }
}
