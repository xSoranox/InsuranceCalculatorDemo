package com.insurance.calculator.demo.calculations;

import com.insurance.calculator.demo.domain.PolicySubObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TheftInsuredSumCalculator implements InsuredSumCalculator {

    /**
     * PREMIUM_THEFT = SUM_INSURED_THEFT * COEFFICIENT_THEFT
     *
     * @param theftInsuredSubObject sub-object which risk type is THEFT.
     * @return calculated PREMIUM_THEFT amount of one policy sub-object.
     */
    public BigDecimal calculate(PolicySubObject theftInsuredSubObject) {
        BigDecimal sumInsured = Optional.ofNullable(theftInsuredSubObject.getSumInsured())
                .orElseThrow(() -> new RuntimeException("Sub-object doesn't have sum insured"));
        if (sumInsured.doubleValue() >= 15) {
            return sumInsured.multiply(BigDecimal.valueOf(0.05));
        }
        return sumInsured.multiply(BigDecimal.valueOf(0.11));
    }
}
