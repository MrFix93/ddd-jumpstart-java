package ddd.core.businessRules;


import java.util.ArrayList;

class AndBusinessRule extends BusinessRule {
    private final BusinessRule firstRule;
    private final BusinessRule secondRule;

    public AndBusinessRule(BusinessRule firstRule, BusinessRule secondRule) {
        this.firstRule = firstRule;
        this.secondRule = secondRule;
    }

    public ArrayList<BusinessRuleViolation> CheckRule() {
        ArrayList<BusinessRuleViolation> result = new ArrayList<BusinessRuleViolation>();
        result.addAll(firstRule.CheckRule());
        result.addAll(secondRule.CheckRule());
        return result;
    }
}