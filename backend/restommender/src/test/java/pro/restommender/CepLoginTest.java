package pro.restommender;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;

import pro.restommender.event.LoginEvent;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.repository.AuthenticatedUserRepository;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class CepLoginTest {

    private KieContainer kContainer;

    @Autowired
    private AuthenticatedUserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void init() {
        KieServices ks = KieServices.Factory.get();
        this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    void login_userUnblocked6Times_blockUser() {

        String mail = "mail2@gmail.com";
        String badPassword = "321222";

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(mail, badPassword));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // User user = (User) authentication.getPrincipal();
        } catch (BadCredentialsException e) {
            AuthenticatedUser user = userRepository.findByEmail(mail);

            FactHandle userFc = kieSession.insert(user);

            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            int num = kieSession.fireAllRules();

            kieSession.delete(userFc);

            assertEquals(user.getBlocked(), true);
            assertEquals(1, num);
        }
    }

    @Test
    void login_userUnblocked5Times_noBlockUser() {

        String mail = "mail2@gmail.com";
        String badPassword = "321222";

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(mail, badPassword));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // User user = (User) authentication.getPrincipal();
        } catch (BadCredentialsException e) {
            AuthenticatedUser user = userRepository.findByEmail(mail);

            kieSession.insert(user);

            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));

            FactHandle handle = kieSession.insert(user);
            int num = kieSession.fireAllRules();

            kieSession.delete(handle);

            assertEquals(user.getBlocked(), false);
            assertEquals(0, num);
        }
    }

    @Test
    void login_userUnblocked2Times_noBlockUser() {

        String mail = "mail2@gmail.com";
        String badPassword = "321222";

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(mail, badPassword));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // User user = (User) authentication.getPrincipal();
        } catch (BadCredentialsException e) {
            AuthenticatedUser user = userRepository.findByEmail(mail);

            kieSession.insert(user);

            kieSession.insert(new LoginEvent(new Date(), user.getId()));
            kieSession.insert(new LoginEvent(new Date(), user.getId()));

            FactHandle handle = kieSession.insert(user);
            int num = kieSession.fireAllRules();

            kieSession.delete(handle);

            assertEquals(user.getBlocked(), false);
            assertEquals(0, num);
        }
    }

}
