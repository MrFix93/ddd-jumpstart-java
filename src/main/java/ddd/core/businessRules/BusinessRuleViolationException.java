package ddd.core.businessRules;


import java.util.ArrayList;

public class BusinessRuleViolationException extends RuntimeException {

    private final ArrayList<BusinessRuleViolation> violations;

    public ArrayList<BusinessRuleViolation> getViolations() {
        return violations;
    }

    public BusinessRuleViolationException() {
        violations = new ArrayList<BusinessRuleViolation>();
    }

    public BusinessRuleViolationException(ArrayList<BusinessRuleViolation> violations) {
        super("Rule Violations: " + violations.size() + " violations have been detected.");
        this.violations = violations;
    }
}
