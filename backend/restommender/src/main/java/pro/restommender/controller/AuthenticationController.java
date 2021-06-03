package pro.restommender.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
import pro.restommender.event.LoginEvent;
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

    @Autowired
    private KieSession kieSession;

    @PostMapping(path = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginRequestDTO authenticationRequest,
            HttpServletResponse response) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();

            String jwt = tokenUtils.generateToken(user.getEmail()); // prijavljujemo se na sistem sa email adresom
            int expiresIn = tokenUtils.getExpiredIn();

            return new ResponseEntity<>(new UserLoginResponseDTO(jwt, expiresIn), HttpStatus.OK);

        } catch (BadCredentialsException e) {
            AuthenticatedUser user = repo.findByEmail(authenticationRequest.getUsername());

            kieSession.insert(user);
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            FactHandle handle = kieSession.insert(user);
            kieSession.fireAllRules();

            kieSession.delete(handle);

            if (user.getBlocked()) {
                repo.save(user);
            }

            return new ResponseEntity<>("user password incorect", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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
