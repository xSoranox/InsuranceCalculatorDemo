package com.insurance.calculator.demo.calculations;

import com.insurance.calculator.demo.domain.Policy;
import com.insurance.calculator.demo.domain.PolicyObject;
import com.insurance.calculator.demo.domain.PolicySubObject;
import com.insurance.calculator.demo.enumeration.RiskType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PremiumCalculator {

    @Autowired
    private Map<RiskType, InsuredSumCalculator> insuredSumCalculators;

    /**
     * @param policy
     * @return end premium (PREMIUM = PREMIUM_FIRE + PREMIUM_THEFT)
     */
    public BigDecimal calculate(Policy policy) {
        return policy.getPolicyObjects().stream()
                .map(PolicyObject::getPolicySubObjects)
                .flatMap(Collection::stream)
                .map(o -> calculateSumInsured(o.getRiskType(), o))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * @param riskType  sub-object type of risk
     * @param subObject policy sub-object
     * @return one sub-object sum insured of corresponding risk calculator
     */
    private BigDecimal calculateSumInsured(RiskType riskType, PolicySubObject subObject) {
        InsuredSumCalculator insuredSumCalculator = insuredSumCalculators.get(riskType);
        return insuredSumCalculator.calculate(subObject);
    }
}
