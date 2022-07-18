package ddd.core.businessRules;

import java.util.ArrayList;

/**
 * Represents a business rule in the domain (DDD).
 */
public abstract class BusinessRule {
    /**
     * Checks the business rule
     * @return >all violations against the rule<
     */
    public abstract ArrayList<BusinessRuleViolation> CheckRule();

    /**
     * Checks the business rule and throws a BusinessRuleViolationException if it is not satified
     * @throws BusinessRuleViolationException if the rule is not satified
     */
    public void throwIfNotSatisfied() throws BusinessRuleViolationException {
        ArrayList<BusinessRuleViolation> violations = CheckRule();

        if (violations != null && !violations.isEmpty())
        {
            throw new BusinessRuleViolationException(violations);
        }
    }

    /**
     * Checks the business rule and throws a BusinessRuleViolationException if it is not satified
     * @param rule if the rule is not satified
     */
    public static void throwIfNotSatisfied(BusinessRule rule) {
        rule.throwIfNotSatisfied();
    }

    /**
     * Combines this rule with another rule. Both rules must be satified to satisfy the combined rule.
     * @param rule another rule
     * @return a combined rule
     */
    public BusinessRule and(BusinessRule rule) {
        return new AndBusinessRule(this, rule);
    }
}
