package pro.restommender;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleTests {
  private KieContainer kContainer;

  @BeforeEach
  public void init() {
    KieServices ks = KieServices.Factory.get();
		this.kContainer = ks
				.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
  }

  @Test
  public void testKieSessionShoudReturnTrue() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");
    assertTrue(true);
  }


}
