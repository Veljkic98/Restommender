package pro.restommender.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.restommender.model.AuthenticatedUser;

@Repository
public interface AuthenticatedUserRepository extends JpaRepository<AuthenticatedUser, Long> {
    
    @Transactional
    AuthenticatedUser findByEmail(String email);
    
}
