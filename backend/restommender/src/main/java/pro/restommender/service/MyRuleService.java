package pro.restommender.service;

import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.rule.Rule;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.model.MyRule;
import pro.restommender.repository.MyRuleRepository;


@Service
public class MyRuleService {
  
  @Autowired
  MyRuleRepository ruleRepository;

  @Autowired
  private KieSession kieSession;

  public void add(MyRule rule) throws Exception{
    if(ruleAlreadyExists(rule))  throw new Exception("rule already exists");
    if(ruleHasErrors(rule)) throw new Exception("rule has errors & cannot be created");
    else ruleRepository.add(rule);
  }

  private boolean ruleAlreadyExists(MyRule rule) {
    Rule foundRule = kieSession.getKieBase().getRule("sbnz", rule.getName());
    if(foundRule == null) return false;
    else return true;
  }

  private boolean ruleHasErrors(MyRule rule) throws Exception{
    KieHelper kieHelper = new KieHelper();
    kieHelper.addContent(rule.getContent(), ResourceType.DRL);
    Results results = kieHelper.verify();

    if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) return true;
    else return false;
  }
}
