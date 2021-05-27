package pro.restommender.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.dto.requestDTO.UserLoginRequestDTO;
import pro.restommender.dto.responseDTO.UserLoginResponseDTO;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.User;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.security.TokenUtils;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticatedUserRepository repo;

    @PostMapping(path = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginRequestDTO authenticationRequest,
            HttpServletResponse response) {

        System.out.println("---------------------------1-------------------------");
        System.out.println(authenticationRequest.getUsername() + authenticationRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        System.out.println("---------------------------2-------------------------");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String jwt = tokenUtils.generateToken(user.getEmail()); // prijavljujemo se na sistem sa email adresom
        int expiresIn = tokenUtils.getExpiredIn();

        return new ResponseEntity<>(new UserLoginResponseDTO(jwt, expiresIn), HttpStatus.OK);
    }

    @GetMapping(path = "/login2")
    public ResponseEntity<?> createAuthenticationToken2(@RequestBody UserLoginRequestDTO authenticationRequest,
            HttpServletResponse response) {

        System.out.println("---------------------------1-------------------------");

        List<AuthenticatedUser> users = repo.findAll();
        System.out.println(users.size());
        for (AuthenticatedUser authenticatedUser : users) {
            System.out.println(authenticatedUser);
        }

        return new ResponseEntity<>("Idmeooooo", HttpStatus.OK);

    }

}
