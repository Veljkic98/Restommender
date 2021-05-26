package pro.restommender.model;

import java.util.List;

public class Rule {

    private String rule;

    private String fact;

    private List<String> rules;

    public Rule() {
    }

    public String getFact() {
        return this.fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getRule() {
        return this.rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public List<String> getRules() {
        return this.rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public void addRules(List<String> rules) {
        this.rules = rules;
    }

}
