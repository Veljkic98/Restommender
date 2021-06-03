package pro.restommender.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.model.AuthenticatedUser;
import pro.restommender.repository.AuthenticatedUserRepository;

@Service
public class UserService {

    @Autowired
    private AuthenticatedUserRepository userRepository;

    public List<AuthenticatedUser> getAll(Boolean blocked) {

        List<AuthenticatedUser> users = userRepository.findAllByBlocked(blocked);

        return users;
    }
    
}
