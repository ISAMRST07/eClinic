package mrs.eclinicapi.controller;


import mrs.eclinicapi.DTO.AuthenticationRequest;
import mrs.eclinicapi.DTO.TokenResponse;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.security.TokenUtils;
import mrs.eclinicapi.service.UserDetailsService;
import mrs.eclinicapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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

}
