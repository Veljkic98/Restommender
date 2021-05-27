package pro.restommender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pro.restommender.model.AuthenticatedUser;
import pro.restommender.repository.AuthenticatedUserRepository;

@Service
public class AuthenticatedUserService implements UserDetailsService {

    @Autowired
    private AuthenticatedUserRepository authenticatedUserRepository;

    public AuthenticatedUser add(AuthenticatedUser entity) {

        try {
            return authenticatedUserRepository.save(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AuthenticatedUser authenticatedUser = authenticatedUserRepository.findByEmail(email);

        if (authenticatedUser != null) 
            return authenticatedUser;

        throw new DataIntegrityViolationException("Auth user not found");
    }
    
}
