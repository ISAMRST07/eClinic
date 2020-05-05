package mrs.eclinicapi.controller;


import mrs.eclinicapi.DTO.AuthenticationRequest;
import mrs.eclinicapi.DTO.TokenResponse;
import mrs.eclinicapi.model.UnregisteredUser;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.security.TokenUtils;
import mrs.eclinicapi.service.UnregisteredUserService;
import mrs.eclinicapi.service.UserDetailsService;
import mrs.eclinicapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UnregisteredUserService unregisteredUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                                                   HttpServletResponse response) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        long expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new TokenResponse(jwt, expiresIn, user));
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<String> getUser(@PathVariable String email) {
        try {
            User u = (User) userDetailsService.loadUserByUsername(email);
            return new ResponseEntity<>(u.getName(), HttpStatus.OK);
        } catch(UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UnregisteredUser> addUser(@RequestBody UnregisteredUser userRequest) {

        User existUser = (User) userDetailsService.loadUserByUsername(userRequest.getUser().getEmail());
        if (existUser != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        UnregisteredUser user = this.unregisteredUserService.addUnregisteredUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
